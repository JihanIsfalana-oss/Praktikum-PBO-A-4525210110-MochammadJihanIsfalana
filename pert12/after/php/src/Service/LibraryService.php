<?php
namespace Perpustakaan\Service;


use Perpustakaan\Exception\AnggotaTidakDitemukanException;
use Perpustakaan\Exception\BukuTidakDitemukanException;
use Perpustakaan\Exception\BukuSedangDipinjamException;
use Perpustakaan\Exception\BatasPinjamTerlampauiException;
use Perpustakaan\Repository\BookRepository;
use Perpustakaan\Denda\AturanDenda;


final class LibraryService
{
    public const BATAS_PINJAM = 3;

    /** @var array<string,int> */
    private array $pinjaman = [];

    public function __construct(
        private readonly BookRepository $repository,
        private readonly AturanDenda $aturanDenda,
    ) {}

    public function tambahBuku(string $isbn, string $judulBuku, int $eksemplar): void
    {
        $buku = $this->repository->cariByIsbn($isbn);
        if ($buku === null) {
            $this->repository->simpan(new \Perpustakaan\Model\Buku(
                $isbn, $judulBuku, '', $eksemplar
            ));
        } else {
            $buku->tambahStok();
        }
    }


    public function daftarkanAnggota(string $idAnggota): void
    {
        $this->pinjaman[$idAnggota] ??= 0;
    }


    /**
     * @throws AnggotaTidakDitemukanException jika anggota belum terdaftar
     * @throws BukuTidakDitemukanException    jika ISBN tidak ada di katalog
     * @throws BukuSedangDipinjamException    jika tidak ada eksemplar tersisa
     * @throws BatasPinjamTerlampauiException jika anggota sudah mencapai batas
     */
    public function pinjam(string $idAnggota, string $isbn): void
    {
        if (!array_key_exists($idAnggota, $this->pinjaman)) {
            throw new AnggotaTidakDitemukanException($idAnggota);
        }


        $buku = $this->repository->cariByIsbn($isbn);
        if ($buku === null) {
            throw new BukuTidakDitemukanException($isbn);
        }


        if (!$buku->tersedia()) {
            throw new BukuSedangDipinjamException($isbn, $buku->getJudul());
        }


        if ($this->pinjaman[$idAnggota] >= self::BATAS_PINJAM) {
            throw new BatasPinjamTerlampauiException($idAnggota, self::BATAS_PINJAM);
        }


        $this->repository->kurangiStok($buku);
        $this->pinjaman[$idAnggota]++;
    }


    public function sisaEksemplar(string $isbn): int
    {
        return $this->repository->cariByIsbn($isbn)?->getJumlahEksemplar() ?? 0;
    }


    public function getSemuaJudul(): array
    {
        $judul = [];
        foreach ($this->repository->cariSemua() as $buku) {
            $judul[$buku->getNomorInduk()] = $buku->getJudul();
        }
        return $judul;
    }

    public function hitungDenda(int $hariTerlambat): float
    {
        return $this->aturanDenda->hitung($hariTerlambat);
    }
}
