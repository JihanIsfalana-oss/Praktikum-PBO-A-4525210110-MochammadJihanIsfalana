<?php

declare(strict_types=1);

namespace Perpustakaan\Util;

use Carbon\CarbonInterface;

/**
 * Kumpulan fungsi bantu untuk memformat tanggal dan teks laporan.
 *
 * Kelas ini tidak perlu dibuat menjadi objek karena seluruh metodenya static.
 */
final class Format
{
    /**
     * Mencegah kelas utilitas dibuat menjadi objek.
     */
    private function __construct() {}

    /**
     * Mengubah tanggal menjadi teks.
     *
     * @param CarbonInterface|null $tanggal Tanggal yang akan diformat.
     * @param string $pola Pola format tanggal, mengikuti format PHP.
     */
    public static function tanggal(
        ?CarbonInterface $tanggal,
        string $pola = 'd-m-Y'
    ): string {
        return $tanggal?->format($pola) ?? '-';
    }

    /**
     * Membuat satu baris tabel dengan lebar kolom tertentu.
     *
     * Contoh:
     * Format::barisTabel(['111', 'Laskar Pelangi', 3], [12, 35, 8]);
     *
     * @param array<int, mixed> $kolom Nilai setiap kolom.
     * @param array<int, int> $lebar Lebar karakter untuk setiap kolom.
     */
    public static function barisTabel(
        array $kolom,
        array $lebar,
        string $pemisah = ' | '
    ): string {
        self::validasiLebarKolom($lebar);

        if (count($kolom) !== count($lebar)) {
            throw new \InvalidArgumentException(
                'Jumlah data kolom harus sama dengan jumlah ukuran kolom.'
            );
        }

        $kolom = array_values($kolom);
        $lebar = array_values($lebar);
        $hasil = [];

        foreach ($kolom as $indeks => $nilai) {
            $hasil[] = sprintf('%-' . $lebar[$indeks] . 's', (string) $nilai);
        }

        return implode($pemisah, $hasil);
    }

    /**
     * Membuat garis pemisah tabel berdasarkan total lebar kolom.
     *
     * @param array<int, int> $lebar Lebar karakter untuk setiap kolom.
     */
    public static function garis(
        array $lebar,
        string $karakter = '=',
        string $pemisah = ' | '
    ): string {
        self::validasiLebarKolom($lebar);

        if (strlen($karakter) !== 1) {
            throw new \InvalidArgumentException(
                'Karakter garis harus terdiri dari tepat satu karakter.'
            );
        }

        $totalLebarKolom = array_sum($lebar);
        $totalLebarPemisah = (count($lebar) - 1) * strlen($pemisah);

        return str_repeat($karakter, $totalLebarKolom + $totalLebarPemisah);
    }

    /**
     * Memastikan setiap ukuran kolom berupa bilangan bulat positif.
     *
     * @param array<int, int> $lebar
     */
    private static function validasiLebarKolom(array $lebar): void
    {
        if ($lebar === []) {
            throw new \InvalidArgumentException(
                'Ukuran kolom tidak boleh kosong.'
            );
        }

        foreach ($lebar as $ukuran) {
            if (!is_int($ukuran) || $ukuran < 1) {
                throw new \InvalidArgumentException(
                    'Lebar setiap kolom harus berupa bilangan bulat positif.'
                );
            }
        }
    }
}
