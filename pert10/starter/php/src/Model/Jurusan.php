<?php
namespace Perpustakaan\Model;

use Perpustakaan\Model\Mahasiswa;

class Jurusan
{
    /** @var Mahasiswa[] */
    private array $mahasiswa = [];

    public function __construct(
        private readonly string $kode,
        private readonly string $nama,
    ) {}

    /** TODO 5: terima objek dari luar (agregasi). Jangan menambahkan duplikat. */
    public function daftarkan(Mahasiswa $m): void {
        array_push($this->mahasiswa, $m);
    }

    /** TODO 6: lepaskan dari jurusan. Objeknya tetap hidup. */
    public function keluarkan(Mahasiswa $m): void {
        $key = array_search($m, $this->mahasiswa, true);
        if ($key !== false) {
            unset($this->mahasiswa[$key]);
            $this->mahasiswa = array_values($this->mahasiswa);
        }
    }

    public function getNama(): string{
        return $this->nama;
    }

    public function getKode(): string{
        return $this->kode;
    }

    /** @return Mahasiswa[] */
    public function getMahasiswa(): array { return $this->mahasiswa; }

    public function __toString(): string
    {
        return sprintf('%s - %s (%d mahasiswa)', $this->kode, $this->nama, count($this->mahasiswa));
    }
}