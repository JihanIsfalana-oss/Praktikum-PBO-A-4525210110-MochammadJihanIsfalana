<?php
declare(strict_types=1);

namespace Perpustakaan\Repository;

use Perpustakaan\Model\Buku;

/**
 * Sesi 12 — REPOSITORY PATTERN (PHP).
 */
interface BookRepository
{
    // TODO 1: rancang tanda tangan method yang dibutuhkan LibraryService
    public function simpan(Buku $buku): void;

    public function cariByIsbn(string $isbn): ?Buku;

    /** @return Buku[] */
    public function cariSemua(): array;

    public function kurangiStok(Buku $buku): void;
}