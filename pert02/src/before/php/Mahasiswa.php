<?php
declare(strict_types=1);

/**
 * Sesi 2 — enkapsulasi yang menjaga invariant (PHP).
 * Bandingkan baris demi baris dengan java/Mahasiswa.java.
 */
class Mahasiswa
{
    public const BOBOT_TUGAS = 0.30;
    public const BOBOT_UTS   = 0.30;
    public const BOBOT_UAS   = 0.40;

    private const NILAI_MIN = 0;
    private const NILAI_MAX = 100;

    /**
     * Constructor property promotion (PHP 8):
     * readonly adalah padanan `final` pada atribut Java.
     *
     * TODO 1: lengkapi daftar parameter — tentukan mana yang readonly.
     */
    public function __construct(
        private readonly string $nim,
        private readonly string $nama,
        private float $nilaiTugas,
        private float $nilaiUts,
        private float $nilaiUas,
    ) {
        // TODO 2: tolak NIM yang kosong (setelah di-trim).
        //         Lemparkan InvalidArgumentException dengan pesan yang jelas.

        // TODO 3: tolak setiap komponen nilai di luar rentang 0-100
        //         menggunakan method pembantu di bawah.
    }

    /**
     * TODO 4: lengkapi validasi satu komponen nilai.
     */
    private static function pastikanNilaiSah(string $namaKomponen, float $nilai): void
    {
        // TODO
    }

    /** TODO 5: hitung nilai akhir memakai konstanta bobot. */
    public function nilaiAkhir(): float
    {
        return 0;   // ganti
    }

    /** TODO 6: kembalikan huruf mutu. Petunjuk: match (true) { ... } */
    public function hurufMutu(): string
    {
        return '?';   // ganti
    }

    // TODO 7: sediakan getter seperlunya. JANGAN membuat setNim().
    public function getNim(): string  { return $this->nim; }
    public function getNama(): string { return $this->nama; }

    public function __toString(): string
    {
        return sprintf('%-10s %-18s akhir=%6.2f  mutu=%s',
            $this->nim, $this->nama, $this->nilaiAkhir(), $this->hurufMutu());
    }
}
