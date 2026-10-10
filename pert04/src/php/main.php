<?php
declare(strict_types=1);

require_once __DIR__ . '/Pegawai.php';

// TODO Langkah 4: tambahkan Dosen dan PegawaiHarian setelah kelasnya dibuat.
$daftar = [
    new PegawaiTetap('198701012010', 'Ani Lestari', 6_000_000, 15),
    new PegawaiKontrak('K-2024-007', 'Budi Santoso', 5_000_000, 12),
    new Dosen('198901012015', 'Dr. Ir. Jihan Isfalana, S.T., M.Kom.', 7_000_000, 10, true),
    new PegawaiHarian('H-2024-003', 'Dedi Prasetyo', 100_000, 20),
];

echo '=== Daftar Gaji ===', PHP_EOL;
foreach ($daftar as $p) {
    echo '  ', $p, PHP_EOL;
}

$total = array_sum(array_map(fn (Pegawai $p): float => $p->hitungGaji(), $daftar));
printf('%s  Total beban gaji: Rp%s%s', PHP_EOL, number_format($total, 2, ',', '.'), PHP_EOL);

echo PHP_EOL, 'Periksa: Ani (pokok 6.000.000, masa kerja 15 tahun)', PHP_EOL;
echo '  tunjangan 15 x 2% = 30%, jadi gaji seharusnya Rp7.800.000,00', PHP_EOL;

echo PHP_EOL, 'Periksa: Budi (pokok 5.000.000, masa kerja 12 bulan)', PHP_EOL;
echo '  tunjangan 12 x 1% = 12%, jadi gaji seharusnya Rp5.600.000,00', PHP_EOL;

echo PHP_EOL, 'Periksa: Citra (pokok 7.000.000, masa kerja 10 tahun, punya tunjangan fungsional)', PHP_EOL;
echo '  tunjangan 10 x 2% = 20%, jadi gaji seharusnya Rp8.400.000,00', PHP_EOL;

echo PHP_EOL, 'Periksa: Dedi (pokok 100.000, jam kerja 20)', PHP_EOL;
echo '  tunjangan 20 x 5.000 = 100.000, jadi gaji seharusnya Rp200.000,00', PHP_EOL;
