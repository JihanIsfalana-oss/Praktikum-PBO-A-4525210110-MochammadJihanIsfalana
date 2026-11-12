<?php

namespace Perpustakaan\Model;

final class Bab
{
    public function __construct(
        private readonly int $nomor,
        private readonly string $judul
    ) {
        if ($nomor < 1) {
            throw new \InvalidArgumentException('Nomor bab harus lebih dari nol');
        }
        if (trim($judul) === '') {
            throw new \InvalidArgumentException('Judul bab tidak boleh kosong');
        }
    }

    public function getNomor(): int
    {
        return $this->nomor;
    }

    public function getJudul(): string
    {
        return $this->judul;
    }

    public function __toString(): string
    {
        return 'Bab ' . $this->nomor . ': ' . $this->judul;
    }
}
