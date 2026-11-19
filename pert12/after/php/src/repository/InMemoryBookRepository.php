<?php
declare(strict_types=1);

namespace Perpustakaan\Repository;

use Perpustakaan\Model\Buku;

final class InMemoryBookRepository implements BookRepository
{
    /** @var array<string, Buku> */
    private array $data = [];

    // TODO 2: implementasikan seluruh method dari BookRepository
    public function simpan(Buku $buku): void
    {
        $this->data[$buku->getNomorInduk()] = $buku;
    }

    public function cariByIsbn(string $isbn): ?Buku
    {
        return $this->data[$isbn] ?? null;
    }

    /** @return Buku[] */
    public function cariSemua(): array
    {
        return array_values($this->data);
    }

    public function kurangiStok(Buku $buku): void
    {
        $buku->kurangiStok();
    }
}