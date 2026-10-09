<?php
declare(strict_types=1);

/**
 * Sesi 1 — objek pertama Anda (PHP).
 *
 * Bandingkan baris demi baris dengan java/HaloObjek.java.
 * Konsepnya sama persis; hanya sintaksnya yang berbeda.
 *
 * Jalankan:
 *     php halo_objek.php
 */
class HaloObjek
{
    // TODO 1: kedua properti di bawah belum punya access modifier.
    //         Tambahkan kata kunci `private` pada keduanya.
    public string $nama = '';
    public string $nim = '';

    public function __construct(string $nama, string $nim)
    {
        // TODO 2: tugaskan kedua parameter ke properti objek.
        //         Padanan `this` pada Java adalah `$this` di PHP,
        //         dan aksesnya memakai `->` bukan titik.
    }

    /**
     * TODO 3: kembalikan satu baris string berisi nama dan NIM Anda.
     *         Gunakan PROPERTI objek — jangan menulis nama Anda langsung.
     */
    public function sapa(): string
    {
        return '(TODO 3 belum dikerjakan)';
    }
}

// Ganti dua nilai di bawah dengan nama dan NIM Anda sendiri.
$saya = new HaloObjek('Nama Anda', 'NIM Anda');
echo $saya->sapa(), PHP_EOL;

$temanSekelas = new HaloObjek('Budi Santoso', '2024002');
echo $temanSekelas->sapa(), PHP_EOL;

echo PHP_EOL;
echo 'Pertanyaan untuk direnungkan:', PHP_EOL;
echo '  Apa padanan kata kunci "this" Java di baris-baris di atas?', PHP_EOL;
echo '  Mengapa PHP memakai -> sedangkan Java memakai titik?', PHP_EOL;
echo '  Mengapa kedua objek di atas bisa menyapa dengan nama berbeda,', PHP_EOL;
echo '  padahal method sapa() hanya ditulis satu kali?', PHP_EOL;
