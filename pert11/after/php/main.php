<?php
require __DIR__ . '/vendor/autoload.php';


use Carbon\Carbon;
use Perpustakaan\Model\Anggota;
use Perpustakaan\Model\Buku;
use Perpustakaan\Model\Mahasiswa;
use Perpustakaan\Model\Jurusan;
use Perpustakaan\Service\PerpustakaanService;
use Perpustakaan\Service\LibraryService;
use Perpustakaan\Exception\PerpustakaanException;
use Perpustakaan\Model\KoleksiBuku;


/*
 * DEMO: hanya jalan kalau file ini dijalankan langsung (php LibraryService.php)
 */
$echoSeparator = static function (string $judul): void {
    $lebar = strlen($judul) + 8;
    echo "\n" . str_repeat('=', $lebar) . PHP_EOL;
    echo '  ' . $judul . PHP_EOL;
    echo str_repeat('=', $lebar) . PHP_EOL;
};

$echoSeparator('DEMO COLLECTIONS - PERTEMUAN 11');

$s = new LibraryService();
$s->tambahBuku('111', 'Laskar Pelangi', 1);
$s->tambahBuku('222', 'Bumi Manusia', 5);
$s->daftarkanAnggota('A-001');
$s->daftarkanAnggota('A-002');


$percobaan = [
    ['A-001', '111'],   // berhasil
    ['A-002', '111'],   // buku habi
    ['A-002', '999'],   // buku tidak ada
    ['X-999', '222'],   // anggota tidak terdaftar
    ['A-001', '222'],   // berhasil
    ['A-001', '222'],   // berhasil
    ['A-001', '222'],   // batas pinjam tercapai
];


foreach ($percobaan as [$id, $isbn]) {
    echo "[TRANSAKSI] Anggota {$id} -> ISBN {$isbn}" . PHP_EOL;
    try {
        $s->pinjam($id, $isbn);
        echo "[OK] Peminjaman berhasil." . PHP_EOL;
    } catch (PerpustakaanException $e) {
        echo "[!] Gagal: " . $e->getMessage() . PHP_EOL;
    }
}

$echoSeparator('LAPORAN FUNGSIONAL KOLEKSI');
$koleksi = new KoleksiBuku([
    new Buku('111', 'Laskar Pelangi', 'Andrea Hirata', 1),
    new Buku('222', 'Bumi Manusia', 'Pramoedya Ananta Toer', 5),
]);
$tersedia = $koleksi->filter(static fn(Buku $b): bool => $b->tersedia());
echo '1) Buku tersedia : ' . implode(', ', $tersedia->map(
    static fn(Buku $b): string => $b->getJudul()
)) . PHP_EOL;
echo '2) Judul digabung : ' . implode(' | ', $koleksi->map(
    static fn(Buku $b): string => $b->getJudul()
)) . PHP_EOL;
$perPenulis = $koleksi->kelompokkan(
    static fn(Buku $b): string => $b->getPenulis()
);
echo '3) Jumlah buku per penulis:' . PHP_EOL;
foreach ($perPenulis as $penulis => $daftar) {
    echo "- {$penulis}: " . count($daftar) . PHP_EOL;
}

createReport($s);


function createReport(LibraryService $s): void
{
    echo PHP_EOL . "================================================" . PHP_EOL;
    echo "  EKSPOR LAPORAN" . PHP_EOL;
    echo "================================================" . PHP_EOL;
    $namaFile = 'laporan_buku.txt';
   
    // Membuka file dalam mode write ('w')
    $fileHandle = fopen(__DIR__ . '/' . $namaFile, 'w');


    if (!$fileHandle) {
        echo "[ERROR] Gagal membuka file untuk menulis.\n";
        exit(1);
    }


    try {
        fwrite($fileHandle, "==========================================================================\n");
        fwrite($fileHandle, "                    LAPORAN DAFTAR BUKU PERPUSTAKAAN                      \n");
        fwrite($fileHandle, "==========================================================================\n");
        fwrite($fileHandle, sprintf("%-12s | %-35s | %-8s | %-15s\n", "ISBN", "JUDUL BUKU", "STOK", "STATUS"));
        fwrite($fileHandle, "--------------------------------------------------------------------------\n");


        $daftarJudul = $s->getSemuaJudul();


        foreach ($daftarJudul as $isbn => $judulBuku) {
            $stokSisa = $s->sisaEksemplar($isbn);
            $status = ($stokSisa > 0) ? "Tersedia" : "Tidak Tersedia";
            fwrite($fileHandle, sprintf("%-12s | %-35s | %-8d | %-15s\n", $isbn, $judulBuku, $stokSisa, $status));
        }


        fwrite($fileHandle, "==========================================================================\n");


        // throw new \Exception("Simulasi error tak terduga di tengah penulisan file!");


        echo ">> BERHASIL: Laporan buku beserta statusnya disimpan ke berkas '{$namaFile}'.\n";


    } catch (\Exception $e) {
        echo "[ERROR SISTEM] Gagal menyelesaikan laporan: " . $e->getMessage() . "\n";
    } finally {
        if (is_resource($fileHandle)) {
            fclose($fileHandle);
            echo "[INFO] File '{$namaFile}' telah ditutup dengan aman melalui blok finally.\n";
        }
    }
}
