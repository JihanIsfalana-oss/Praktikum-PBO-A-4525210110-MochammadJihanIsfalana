<?php
namespace Perpustakaan\Exception;


use Perpustakaan\Exception\PerpustakaanException;


final class BatasPinjamTerlampauiException extends PerpustakaanException
{
    // TODO 5: membawa $idAnggota dan $batas.
    public function __construct(
        private readonly string $idAnggota,
        private readonly int $batas,
    ) {
        parent::__construct(
            "Anggota {$idAnggota} telah mencapai batas peminjaman ({$batas} buku)."
        );
    }
public function getIdAnggota(): string
    {
        return $this->idAnggota;
    }


    public function getBatas(): int
    {
        return $this->batas;
    }
}
