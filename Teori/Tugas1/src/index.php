<?php  
// Cara penulisan di miripkan dengan penulisan Java
declare(strict_types=1);
require_once 'Mahasiswa.php';
    function main(): void {     
        $orang1 = new Mahasiswa("Mochammad Jihan Isfalana", "4525210110");
        $orang2 = new Mahasiswa("Muhammad Rasha Zein", "452521000");

        echo "\n------>> Objek orang1 <<------\n";
        echo "Nama objek orang1\t: " . $orang1->nama . "\n";
        echo "NIM objek orang1\t: " . $orang1->nim . "\n\n";

        $orang1->jadwalMataKuliah = "Object-Oriented Programming";
        $orang1->programStudi = "Sastra Mesin";
        $orang1->kegiatanMhs = "Oprek Mesin Bubut";

        $orang1->belajar();
        $orang1->kegiatan();

        echo "\n------>> Objek orang2 <<------\n";
        echo "Nama objek orang2\t: " . $orang2->nama . "\n" ;
        echo "NIM objek orang2\t: " . $orang2->nim . "\n\n";

        $orang2->jadwalMataKuliah = null;
        $orang2->programStudi = "Teknik Informatika";
        $orang2->kegiatanMhs = "rapat";

        $orang2->belajar();
        $orang2->kegiatan();
    }

    main();
?>