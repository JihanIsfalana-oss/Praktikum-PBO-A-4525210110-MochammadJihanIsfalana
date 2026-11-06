<?php
namespace Perpustakaan\Model;

use Error;

final class Bab
{
    /**
     * TODO 1: buat constructor readonly untuk $nomor dan $judul.
     *         Perhatikan: agar benar-benar komposisi, kelas ini hanya
     *         boleh dibuat dari dalam Buku. PHP tidak punya nested class
     *         seperti Java, jadi disiplin ini dijaga lewat konvensi —
     *         tuliskan catatannya di bukti-relasi.md.
     */
    public function __construct(
        private readonly int $nomor,
        private readonly string $judul
    )
    {}

    public function getNomor(): int{
        return $this->nomor;
    }

    public function getJudul(): string{
        return $this->judul;
    }

    public function __toString(): string
    {
        return 'Bab '. $this->getNomor() . ":". $this->getJudul();
    }
}

final class Buku
{
    /** @var Bab[] */
    private array $bab = [];

    public function __construct(
        private readonly string $nomorInduk,
        private readonly string $judul,
        private readonly string $penulis,
        private int $jumlahEksemplar,
    ) {
        // TODO 2: tolak jumlah eksemplar negatif.

        if ($jumlahEksemplar < 0){
            throw new Error("Jumlah eksampelar tidak boleh negatif!");
        }
        
    }

    /** TODO 3: satu-satunya cara Bab lahir. Parameternya nomor dan judul, bukan objek Bab. */
    public function tambahBab(int $nomor, string $judulBab): void
    {
        array_push($this->bab, new Bab($nomor, $judulBab));
    }

    /**
     * PHP mengembalikan array secara by-value, sehingga pemanggil hanya
     * memperoleh salinan larik-nya. Namun objek DI DALAMNYA tetap berbagi
     * referensi — itulah sebabnya Bab dibuat readonly.
     * Tuliskan penjelasan ini di bukti-relasi.md.
     *
     * @return Bab[]
     */
    public function getBab(): array { return $this->bab; }

    public function tersedia(): bool { return $this->jumlahEksemplar > 0; }

    /** TODO 4 */
    public function kurangiStok(): void {
        if ($this->jumlahEksemplar < 0){
            throw new Error("Jumlah Eksemplar = 0");
        }

        $this->jumlahEksemplar--;
    }

    public function tambahStok(): void { $this->jumlahEksemplar++; }

    public function getNomorInduk(): string { return $this->nomorInduk; }
    public function getJudul(): string { return $this->judul; }

    public function __toString(): string
    {
        return sprintf('%-32s %-16s [%d eks, %d bab]',
            $this->judul, $this->penulis, $this->jumlahEksemplar, count($this->bab));
    }
}
