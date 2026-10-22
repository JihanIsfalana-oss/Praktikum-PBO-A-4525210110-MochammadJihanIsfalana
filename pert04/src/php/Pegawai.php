<?php
declare(strict_types=1);

/**
 * Sesi 4 — hierarki pegawai (PHP).
 * Seluruh hierarki ditaruh dalam satu berkas agar mudah dibaca berdampingan
 * dengan versi Java. Mulai sesi 9, satu kelas = satu berkas.
 */
abstract class Pegawai
{
    public function __construct(
        protected readonly string $nip,
        protected readonly string $nama,
        protected readonly float  $gajiPokok,
    ) {
        // TODO 1: tolak gaji pokok negatif.
        if ($gajiPokok < 0) {
            throw new InvalidArgumentException('Gaji pokok tidak boleh negatif');
        }
    }

    /** TODO 2: kembalikan gaji pokok apa adanya. */
    public function hitungGaji(): float
    {
        return $this->gajiPokok;   // ganti
    }

    abstract public function jenis(): string;

    public function getNama(): string { return $this->nama; }
    public function getNip(): string  { return $this->nip; }

    public function __toString(): string
    {
        return sprintf('%-14s %-9s %-20s Rp%s',
            $this->nip, $this->jenis(), $this->nama,
            number_format($this->hitungGaji(), 2, ',', '.'));
    }
}

class PegawaiTetap extends Pegawai
{
    protected const TUNJANGAN_PER_TAHUN = 0.02;
    protected const TUNJANGAN_MAKSIMUM  = 0.40;

    public function __construct(
        string $nip, string $nama, float $gajiPokok,
        protected readonly int $masaKerjaTahun,
    ) {
        // WAJIB. TODO 3 (Langkah 3): hapus sementara baris ini,
        //         jalankan, salin pesan kesalahannya, lalu kembalikan.
        parent::__construct($nip, $nama, $gajiPokok);
    }

    /**
     * TODO 4: gaji dasar induk + tunjangan masa kerja.
     *         Gunakan parent::hitungGaji(), jangan menyalin rumusnya.
     */
    public function hitungGaji(): float
    {
        return parent::hitungGaji() * (1 + min($this->masaKerjaTahun * self::TUNJANGAN_PER_TAHUN, self::TUNJANGAN_MAKSIMUM));
    }

    public function jenis(): string { return 'TETAP'; }
}

class PegawaiKontrak extends Pegawai
{
    public function __construct(
        string $nip, string $nama, float $gajiPokok,
        private readonly int $bulanKontrak,
    ) {
        parent::__construct($nip, $nama, $gajiPokok);
    }

    public function jenis(): string { return 'KONTRAK'; }

    public function getBulanKontrak(): int { return $this->bulanKontrak; }
}

// TODO Langkah 4: buat kelas Dosen (turunan PegawaiTetap, punya tunjangan fungsional)
//                 dan PegawaiHarian (gaji per hari kerja) di bawah ini.
class Dosen extends PegawaiTetap
{
    private const TUNJANGAN_FUNGSIONAL = 1_000_000;

    public function __construct(
        string $nip, string $nama, float $gajiPokok,
        int $masaKerjaTahun,
        private readonly bool $punyaTunjanganFungsional,
    ) {
        parent::__construct($nip, $nama, $gajiPokok, $masaKerjaTahun);
    }

    public function hitungGaji(): float
    {
        $gaji = parent::hitungGaji();
        if ($this->punyaTunjanganFungsional) {
            $gaji += self::TUNJANGAN_FUNGSIONAL;
        }
        return $gaji;
    }

    public function jenis(): string { return 'DOSEN'; }
}

class PegawaiHarian extends Pegawai
{
    public function __construct(
        string $nip, string $nama, float $gajiPokok,
        private readonly int $hariKerja,
    ) {
        parent::__construct($nip, $nama, $gajiPokok);
    }

    public function hitungGaji(): float
    {
        return $this->gajiPokok * $this->hariKerja;
    }

    public function jenis(): string { return 'HARIAN'; }
}
