<?php
require __DIR__ . '/vendor/autoload.php';

use Carbon\Carbon;
use Perpustakaan\Model\Anggota;
use Perpustakaan\Model\Buku;
use Perpustakaan\Model\Mahasiswa;
use Perpustakaan\Model\Jurusan;
use Perpustakaan\Service\PerpustakaanService;

echo '=== Komposisi: Buku memiliki Bab ===', PHP_EOL;
$buku = new Buku('PBO-001', 'Pemrograman Berorientasi Objek', 'A. W. Pribadi', 3);
$buku->tambahBab(1, 'Paradigma Objek');
$buku->tambahBab(2, 'Enkapsulasi');
$buku->tambahBab(3, 'Pewarisan dan Polimorfisme');
echo '  ', $buku, PHP_EOL;
foreach ($buku->getBab() as $b) {
    echo '    ', $b, PHP_EOL;
}

echo PHP_EOL, '=== Agregasi: Jurusan menaungi Mahasiswa ===', PHP_EOL;
$ani  = new Mahasiswa('2024001', 'Ani Lestari');
$budi = new Mahasiswa('2024002', 'Budi Santoso');

$informatika = new Jurusan('TI', 'Teknik Informatika');
$informatika->daftarkan($ani);
$informatika->daftarkan($budi);
echo '  ', $informatika, PHP_EOL;

$informatika->keluarkan($budi);
echo '  Setelah Budi pindah: ', $informatika, PHP_EOL;
echo '  Budi tetap ada sebagai objek: ', $budi, PHP_EOL;

echo PHP_EOL;
echo "========== Perpustakaan Teknik =============", PHP_EOL;
$perpus = new PerpustakaanService();
$perpus->tambahKoleksi(new Buku("PBO-001", "Pemrograman Berorientasi Objek", "A. W. Pribadi", 3));

$hasilPencarian = $perpus->cariKoleksi("PBO-001");
echo "Hasil Pencarian buku anda: ", $hasilPencarian, PHP_EOL;
$perpus->pinjam("IT-4554", "PBO-001");
$perpus->kembalikan("IT-4554", "PBO-001");

echo PHP_EOL;
echo "========== Keanggotaan Perpustakaan Teknik =============", PHP_EOL;
$date = Carbon::now();
$anggota1 = new Anggota("IT", $informatika->getNama(), "045");
$anggota1->daftarkanAnggota($budi, $date);
$anggota1->getKeanggotaan();
echo "Status Keanggotaan: ", $anggota1->checkMasaKeanggotaan($date), PHP_EOL;