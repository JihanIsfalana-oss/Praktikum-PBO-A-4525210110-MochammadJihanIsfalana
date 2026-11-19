<?php
namespace Perpustakaan\Exception;


use Perpustakaan\Exception\PerpustakaanException;


final class BukuSedangDipinjamException extends PerpustakaanException
{
    // TODO 4: membawa $isbn DAN $judul.
    public function __construct(
        private readonly string $isbn,
        private readonly string $judul,
    ) {
        parent::__construct("Buku \"{$judul}\" (ISBN {$isbn}) sedang dipinjam.");
    }


    public function getIsbn(): string
    {
        return $this->isbn;
    }


    public function getJudul(): string
    {
        return $this->judul;
    }
}
