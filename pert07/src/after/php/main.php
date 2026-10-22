<?php
declare(strict_types=1);

require_once __DIR__ . '/relasi.php';

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
