<?php
declare(strict_types=1);

namespace Perpustakaan\Denda;

/**
 * Sesi 12 — STRATEGY PATTERN (PHP).
 */
interface AturanDenda
{
    public function hitung(int $hariTerlambat): float;

    public function nama(): string;
}