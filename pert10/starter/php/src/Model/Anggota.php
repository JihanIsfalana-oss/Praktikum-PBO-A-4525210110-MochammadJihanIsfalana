<?php

namespace Perpustakaan\Model;

use Carbon\Carbon;
use InvalidArgumentException;
use Perpustakaan\Model\Jurusan;
use Perpustakaan\Model\Mahasiswa;

class Anggota extends Jurusan
{
    private string $kodeAnggota;
    private Carbon $anggotaSejak;
    private Mahasiswa $anggotaPerpus;

    public function __construct(string $kodeJurusan, string $namaJurusan, string $kodeAnggota)
    {
        parent::__construct($kodeJurusan, $namaJurusan);
        $this->kodeAnggota = $kodeAnggota;
    }

    public function daftarkanAnggota(Mahasiswa $m, Carbon $masaKeanggotaan): void
    {
        if ($m === null) {
            throw new InvalidArgumentException("Mahasiswa tidak boleh kosong");
        }

        $this->anggotaPerpus = $m;
        $this->anggotaSejak = $masaKeanggotaan;
    }

    public function checkMasaKeanggotaan(Carbon $dateNow): string
    {
        if ($this->anggotaSejak === null) {
            return "Keanggotaan belum didaftarkan";
        }


        // Mengecek apakah waktu sekarang melebihi 3 tahun dari masa pendaftaran
        if ($dateNow->isAfter($this->anggotaSejak->copy()->addYears(3))) {
            return "Keanggotaan tidak valid (lebih dari 3 tahun)";
        }

        return "Keanggotaan valid";
    }

    public function getKeanggotaan(): void
    {
        echo "Nama Anggota: " . ($this->anggotaPerpus) . "\n";
        echo "Jurusan: " . parent::getNama() . "\n";
        echo "Kode Anggota: " . $this->kodeAnggota . "\n";
        echo "Anggota Perpustakaan Sejak: " . ($this->anggotaSejak ? $this->formatDateMasaAnggota($this->anggotaSejak) : "-") . "\n";
    }

    private function formatDateMasaAnggota(Carbon $date): string
    {
        return $date->format('d-m-Y');
    }
}