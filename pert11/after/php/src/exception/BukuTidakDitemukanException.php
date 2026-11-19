<?php
namespace Perpustakaan\Exception;


use Perpustakaan\Exception\PerpustakaanException;


final class BukuTidakDitemukanException extends PerpustakaanException
{
    /**
     * TODO 1: gunakan constructor property promotion agar $isbn tersimpan
     *         sebagai properti readonly
     */
    public function __construct(private readonly string $isbn)
    {
        parent::__construct("Buku dengan ISBN {$isbn} tidak ditemukan.");
    }


    // TODO 2: sediakan getter getIsbn().
    public function getIsbn(): string
    {
        return $this->isbn;
    }
}
