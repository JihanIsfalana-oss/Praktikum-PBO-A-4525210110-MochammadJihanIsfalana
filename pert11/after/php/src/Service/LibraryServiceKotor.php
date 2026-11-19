<?php
declare(strict_types=1);


/**
 * BAHAN REFACTORING (PHP) — bandingkan dengan versi Java.
 */
final class LibraryServiceKotor
{
public const int BATAS_PINJAM = 3;


    /** @var array<string,int> */
    private array $stok = [];
    /** @var array<string,string> */
    private array $judul = [];
    /** @var array<string,int> */
    private array $pinjaman = [];


    public function tambahBuku(string $isbn, string $judulBuku, int $eksemplar): void
    {
        $this->stok[$isbn] = ($this->stok[$isbn] ?? 0) + $eksemplar;
        $this->judul[$isbn] = $judulBuku;
    }


    public function daftarkanAnggota(string $idAnggota): void
    {
        $this->pinjaman[$idAnggota] ??= 0;
    }


    /** INILAH yang harus Anda refaktor. */
    public function pinjam(string $idAnggota, string $isbn): bool
    {
        if (array_key_exists($idAnggota, $this->pinjaman)) {
            if (array_key_exists($isbn, $this->stok)) {
                if ($this->stok[$isbn] > 0) {
                    if ($this->pinjaman[$idAnggota] < self::BATAS_PINJAM) {
                        $this->stok[$isbn]--;
                        $this->pinjaman[$idAnggota]++;
                        return true;
                    } else {
                        return false;   // kenapa gagal? pemanggil tidak tahu
                    }
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }


    public function sisaEksemplar(string $isbn): int
    {
        return $this->stok[$isbn] ?? 0;
    }
}
