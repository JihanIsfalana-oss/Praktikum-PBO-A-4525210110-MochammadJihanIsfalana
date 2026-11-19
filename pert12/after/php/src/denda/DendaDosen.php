<?php
declare(strict_types=1);

namespace Perpustakaan\Denda;

// TODO 3: buat DendaDosen (Rp500/hari)
final class DendaDosen implements AturanDenda
{
    private const float TARIF_PER_HARI = 500.0;

    public function hitung(int $hariTerlambat): float
    {
        return max(0, $hariTerlambat) * self::TARIF_PER_HARI;
    }

    public function nama(): string
    {
        return 'Dosen (Rp500/hari)';
    }
}