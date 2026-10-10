<?php
declare(strict_types=1);

require_once __DIR__ . '/RekeningBank.php';

echo 'Jumlah rekening di awal: ', RekeningBank::getJumlahRekening(), PHP_EOL;

$a = new RekeningBank('111', 'Ani', 1_000_000);
$b = RekeningBank::rekeningPelajar('222', 'Budi');   // named constructor
$c = new RekeningBank('333', 'Citra', 250_000);

echo $a, PHP_EOL, $b, PHP_EOL, $c, PHP_EOL;
echo 'Jumlah rekening sekarang: ', RekeningBank::getJumlahRekening(), '   (seharusnya 3)', PHP_EOL;

echo PHP_EOL, '=== Operasi ===', PHP_EOL;
$a->setor(500_000);
echo 'Setelah setor 500.000  -> ', $a, PHP_EOL;

try {
    $a->tarik(9_999_999);
    echo '  MASALAH: penarikan melebihi batas seharusnya ditolak!', PHP_EOL;
} catch (RuntimeException | InvalidArgumentException $e) {
    echo '  Ditolak: ', $e->getMessage(), PHP_EOL;
}

$b->potongBiayaAdmin();
echo 'Budi setelah potong admin: ', $b, '   (saldo tidak boleh negatif)', PHP_EOL;

printf('Bunga setahun dari saldo Ani: Rp%s%s',
    number_format(RekeningBank::bungaSetahun($a->getSaldo()), 2, ',', '.'), PHP_EOL);
