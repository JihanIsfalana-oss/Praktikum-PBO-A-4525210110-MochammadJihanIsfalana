<?php
declare(strict_types=1);

namespace Perpustakaan\Denda;

// Langkah 3 (OCP): tambah DendaAlumni tanpa menyunting LibraryService
final class DendaAlumni implements AturanDenda
{
    private const float TARIF_PER_HARI = 750.0;

    public function hitung(int $hariTerlambat): float
    {
        return max(0, $hariTerlambat) * self::TARIF_PER_HARI;
    }

    public function nama(): string
    {
        return 'Alumni (Rp750/hari)';
    }
}