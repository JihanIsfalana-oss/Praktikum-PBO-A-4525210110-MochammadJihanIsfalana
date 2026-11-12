<?php

namespace Perpustakaan\Model;

use Carbon\Carbon;

final class Anggota
{
    private ?Carbon $anggotaSejak = null;
    private ?Mahasiswa $anggotaPerpus = null;

    public function __construct(
        private readonly string $kodeJurusan,
        private readonly string $namaJurusan,
        private readonly string $kodeAnggota
    ) {}

    public function daftarkanAnggota(Mahasiswa $m, Carbon $masaKeanggotaan): void
    {
        $this->anggotaPerpus = $m;
        $this->anggotaSejak = $masaKeanggotaan;
    }

    public function checkMasaKeanggotaan(Carbon $dateNow): string
    {
        if ($this->anggotaSejak === null || $this->anggotaPerpus === null) {
            return 'Keanggotaan belum didaftarkan';
        }

        if ($dateNow->isAfter($this->anggotaSejak->copy()->addYears(3))) {
            return 'Keanggotaan tidak valid (lebih dari 3 tahun)';
        }

        return 'Keanggotaan valid';
    }

    public function getKeanggotaan(): void
    {
        echo 'Nama Anggota: ' . ($this->anggotaPerpus ?? 'Belum didaftarkan') . PHP_EOL;
        echo 'Jurusan: ' . $this->namaJurusan . PHP_EOL;
        echo 'Kode Anggota: ' . $this->kodeAnggota . PHP_EOL;
        echo 'Anggota Perpustakaan Sejak: '
            . ($this->anggotaSejak === null ? '-' : $this->formatDateMasaAnggota($this->anggotaSejak))
            . PHP_EOL;
    }

    private function formatDateMasaAnggota(Carbon $date): string
    {
        return $date->format('d-m-Y');
    }
}