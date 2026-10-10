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
     * TODO 1: lengkapi daftar parameter — tentukan mana yang readonly.
     * NIM dan Nama bersifat readonly (immutable), sedangkan komponen nilai 
     * bisa diubah jika ada perbaikan/remedi.
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
        if (trim($this->nim) === '') {
            throw new InvalidArgumentException("NIM tidak boleh kosong atau hanya berisi spasi.");
        }

        if (trim($this->nama) === '') {
            throw new InvalidArgumentException("Nama tidak boleh kosong atau hanya berisi spasi.");
        }

        // TODO 3: tolak setiap komponen nilai di luar rentang 0-100
        //         menggunakan method pembantu di bawah.
        self::pastikanNilaiSah('nilaiTugas', $this->nilaiTugas);
        self::pastikanNilaiSah('nilaiUts', $this->nilaiUts);
        self::pastikanNilaiSah('nilaiUas', $this->nilaiUas);
    }

    /**
     * TODO 4: lengkapi validasi satu komponen nilai.
     */
    private static function pastikanNilaiSah(string $namaKomponen, float $nilai): void
    {
        if ($nilai < self::NILAI_MIN || $nilai > self::NILAI_MAX) {
            throw new InvalidArgumentException(
                sprintf("Komponen [%s] tidak sah! Nilai harus berada di rentang %.2f sampai %.2f.", 
                $namaKomponen, self::NILAI_MIN, self::NILAI_MAX)
            );
        }
    }

    /** TODO 5: hitung nilai akhir memakai konstanta bobot. */
    public function nilaiAkhir(): float
    {
        return ($this->nilaiTugas * self::BOBOT_TUGAS) +
               ($this->nilaiUts * self::BOBOT_UTS) +
               ($this->nilaiUas * self::BOBOT_UAS);
    }

    /** TODO 6: kembalikan huruf mutu. Petunjuk: match (true) { ... } */
    public function hurufMutu(): string
    {
        $na = $this->nilaiAkhir();
        
        return match (true) {
            $na >= 80.0 => 'A',
            $na >= 70.0 => 'B',
            $na >= 60.0 => 'C',
            $na >= 50.0 => 'D',
            default     => 'E',
        };
    }

    // TODO 7: sediakan getter seperlunya. JANGAN membuat setNim().
    public function getNim(): string   { return $this->nim; }
    public function getNama(): string  { return $this->nama; }
    public function getNilaiTugas(): float { return $this->nilaiTugas; }
    public function getNilaiUts(): float   { return $this->nilaiUts; }
    public function getNilaiUas(): float   { return $this->nilaiUas; }

    // Setter untuk nilai yang validasinya tetap menjaga Invariant (0-100)
    public function setNilaiTugas(float $nilaiTugas): void 
    {
        self::pastikanNilaiSah('nilaiTugas', $nilaiTugas);
        $this->nilaiTugas = $nilaiTugas;
    }

    public function setNilaiUts(float $nilaiUts): void 
    {
        self::pastikanNilaiSah('nilaiUts', $nilaiUts);
        $this->nilaiUts = $nilaiUts;
    }

    public function setNilaiUas(float $nilaiUas): void 
    {
        self::pastikanNilaiSah('nilaiUas', $nilaiUas);
        $this->nilaiUas = $nilaiUas;
    }

    public function __toString(): string
    {
        return sprintf('%-10s %-18s akhir=%6.2f  mutu=%s',
            $this->nim, $this->nama, $this->nilaiAkhir(), $this->hurufMutu());
    }
}
