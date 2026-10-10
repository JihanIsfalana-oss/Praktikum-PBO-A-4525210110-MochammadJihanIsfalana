<?php
declare(strict_types=1);

require_once __DIR__ . '/Pegawai.php';

// TODO Langkah 4: tambahkan Dosen dan PegawaiHarian setelah kelasnya dibuat.
$daftar = [
    new PegawaiTetap('198701012010', 'Ani Lestari', 6_000_000, 15),
    new PegawaiKontrak('K-2024-007', 'Budi Santoso', 5_000_000, 12),
];

echo '=== Daftar Gaji ===', PHP_EOL;
foreach ($daftar as $p) {
    echo '  ', $p, PHP_EOL;
}

$total = array_sum(array_map(fn (Pegawai $p): float => $p->hitungGaji(), $daftar));
printf('%s  Total beban gaji: Rp%s%s', PHP_EOL, number_format($total, 2, ',', '.'), PHP_EOL);

echo PHP_EOL, 'Periksa: Ani (pokok 6.000.000, masa kerja 15 tahun)', PHP_EOL;
echo '  tunjangan 15 x 2% = 30%, jadi gaji seharusnya Rp7.800.000,00', PHP_EOL;
