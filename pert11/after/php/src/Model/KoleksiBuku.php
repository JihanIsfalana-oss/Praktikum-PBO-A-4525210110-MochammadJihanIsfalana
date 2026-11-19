<?php
declare(strict_types=1);

namespace Perpustakaan\Model;

use ArrayIterator;
use Countable;
use IteratorAggregate;

final class KoleksiBuku implements IteratorAggregate, Countable
{
    /** @var array<string,Buku> */
    private array $items = [];

    /** @param Buku[] $buku */
    public function __construct(array $buku = [])
    {
        foreach ($buku as $b) {
            $this->simpan($b);
        }
    }

    public function simpan(Buku $b): void
    {
        $this->items[$b->getNomorInduk()] = $b;
    }

    public function cariByIsbn(string $isbn): ?Buku
    {
        return $this->items[$isbn] ?? null;
    }

    /** @return Buku[] */
    public function semua(): array
    {
        return array_values($this->items);
    }

    public function filter(callable $predikat): self
    {
        $hasil = array_filter($this->items, $predikat);
        return new self(array_values($hasil));
    }

    /** @return mixed[] */
    public function map(callable $f): array
    {
        return array_map($f, array_values($this->items));
    }

    public function jumlahkan(callable $f): float
    {
        return (float) array_reduce(
            array_values($this->items),
            static fn(float $total, Buku $b): float => $total + (float) $f($b),
            0.0
        );
    }

    /** @return array<string,Buku[]> */
    public function kelompokkan(callable $kunci): array
    {
        $kelompok = [];
        foreach ($this->items as $b) {
            $nama = (string) $kunci($b);
            $kelompok[$nama][] = $b;
        }
        return $kelompok;
    }

    public function getIterator(): ArrayIterator
    {
        return new ArrayIterator(array_values($this->items));
    }

    public function count(): int
    {
        return count($this->items);
    }
}
