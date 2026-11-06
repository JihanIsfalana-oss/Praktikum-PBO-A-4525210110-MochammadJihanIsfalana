<?php
namespace Perpustakaan\Service;

use Perpustakaan\Model\Buku;

class PerpustakaanService 
{
    /**
     * @var Buku[]
     */
    private array $buku = [];

    public function tambahKoleksi(Buku $buku): void 
    {
        $this->buku[] = $buku;
    }

    public function cariKoleksi(string $nomorInduk): ?Buku 
    {
        foreach ($this->buku as $item) {
            if ($item->getNomorInduk() === $nomorInduk) {
                return $item;
            }
        }
        return null;
    }

    public function pinjam(string $nomorAnggota, string $nomorInduk): void 
    {
        echo "Anggota {$nomorAnggota} meminjam buku {$nomorInduk}\n";
    }

    public function kembalikan(string $nomorAnggota, string $nomorInduk): void 
    {
        echo "Anggota: {$nomorAnggota} mengembalikan buku {$nomorInduk}\n";
    }
}