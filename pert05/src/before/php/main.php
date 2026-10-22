<?php
declare(strict_types=1);

require_once __DIR__ . '/BangunDatar.php';

/** @var BangunDatar[] $daftar */
$daftar = [
    new Lingkaran(7),
    new Persegi(5),
    // TODO Langkah 2: tambahkan new Segitiga(3, 4, 5)
];

echo '=== Bangun Datar ===', PHP_EOL;
foreach ($daftar as $b) {
    echo '  ', $b, PHP_EOL;
}

$total = array_sum(array_map(fn (BangunDatar $b): float => $b->luas(), $daftar));
printf('%s  Total luas: %.2f%s', PHP_EOL, $total, PHP_EOL);

echo PHP_EOL, 'Periksa: Lingkaran(7) luas = 153,94 ; Persegi(5) luas = 25,00', PHP_EOL;
