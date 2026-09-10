<?php
declare (strict_types=1);

    class Mahasiswa {
        public string $nama;
        public string $nim;
        public ?string $programStudi = null;
        public ?string $jadwalMataKuliah = null;
        public ?string $kegiatanMhs = null;
        public function __construct(string $nama, string $nim) {
            $this->nama = $nama;
            $this->nim = $nim;
        }

        public function belajar(): void {
            if($this->jadwalMataKuliah === null) {
                throw new InvalidArgumentException("Maaf, jadwalMataKuliah bernilai NULL!\n");
            }
            echo $this->nama . " sedang belajar " . $this->jadwalMataKuliah . " di kelas.\n";
        }
        public function kegiatan(): void {
            if($this->kegiatanMhs === null) {
                throw new InvalidArgumentException("Maaf, kegiatanMhs bernilai NULL!\n");
            }
            echo "\n" . $this->nama . " sedang " . $this->kegiatanMhs . " bersama mahasiswa himpunan " . $this->programStudi . " lainnya.\n";
        }
    }
?>