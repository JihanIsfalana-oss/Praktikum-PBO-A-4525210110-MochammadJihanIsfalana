<?php
namespace Perpustakaan\Service;

use Perpustakaan\Model\Buku;

class PerpustakaanService 
{
    /**
     * @var Buku[]
     */
    private array $buku = [];
    /** @var array<string, array<string, true>> */
    private array $peminjamanAktif = [];

    public function tambahKoleksi(Buku $buku): void
    {
        if ($this->cariKoleksi($buku->getNomorInduk()) !== null) {
            throw new \InvalidArgumentException('Nomor induk buku sudah terdaftar');
        }

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
        $this->validasiIdentitas($nomorAnggota, $nomorInduk);
        $buku = $this->cariKoleksi($nomorInduk);
        if ($buku === null) {
            throw new \InvalidArgumentException('Buku tidak ditemukan');
        }
        if (isset($this->peminjamanAktif[$nomorAnggota][$nomorInduk])) {
            throw new \LogicException('Buku masih dipinjam anggota tersebut');
        }

        $buku->kurangiStok();
        $this->peminjamanAktif[$nomorAnggota][$nomorInduk] = true;
    }

    public function kembalikan(string $nomorAnggota, string $nomorInduk): void
    {
        $this->validasiIdentitas($nomorAnggota, $nomorInduk);
        $buku = $this->cariKoleksi($nomorInduk);
        if ($buku === null) {
            throw new \InvalidArgumentException('Buku tidak ditemukan');
        }
        if (!isset($this->peminjamanAktif[$nomorAnggota][$nomorInduk])) {
            throw new \LogicException('Peminjaman tidak ditemukan');
        }

        unset($this->peminjamanAktif[$nomorAnggota][$nomorInduk]);
        if ($this->peminjamanAktif[$nomorAnggota] === []) {
            unset($this->peminjamanAktif[$nomorAnggota]);
        }
        $buku->tambahStok();
    }

    private function validasiIdentitas(string $nomorAnggota, string $nomorInduk): void
    {
        if (trim($nomorAnggota) === '' || trim($nomorInduk) === '') {
            throw new \InvalidArgumentException('Identitas anggota dan buku tidak boleh kosong');
        }
    }
}