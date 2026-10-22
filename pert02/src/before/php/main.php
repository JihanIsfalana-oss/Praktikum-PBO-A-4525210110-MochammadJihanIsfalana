<?php
declare(strict_types=1);

require_once __DIR__ . '/Mahasiswa.php';

echo '=== Rekap Nilai ===', PHP_EOL;
$kelas = [
    new Mahasiswa('2024001', 'Ani Lestari',  85, 78, 90),
    new Mahasiswa('2024002', 'Budi Santoso', 60, 55, 62),
    new Mahasiswa('2024003', 'Citra Wijaya', 92, 88, 95),
];
foreach ($kelas as $m) {
    echo '  ', $m, PHP_EOL;
}

echo PHP_EOL, '=== Objek menolak data yang melanggar aturan ===', PHP_EOL;

try {
    new Mahasiswa('2024004', 'Salah Nilai', 150, 80, 80);
    echo '  MASALAH: nilai 150 seharusnya ditolak!', PHP_EOL;
} catch (InvalidArgumentException $e) {
    echo '  Ditolak: ', $e->getMessage(), PHP_EOL;
}

try {
    new Mahasiswa('', 'NIM Kosong', 80, 80, 80);
    echo '  MASALAH: NIM kosong seharusnya ditolak!', PHP_EOL;
} catch (InvalidArgumentException $e) {
    echo '  Ditolak: ', $e->getMessage(), PHP_EOL;
}
