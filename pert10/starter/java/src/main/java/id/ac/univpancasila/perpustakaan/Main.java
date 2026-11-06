package id.ac.univpancasila.perpustakaan;

import java.time.LocalDateTime;

import id.ac.univpancasila.perpustakaan.model.*;
import  id.ac.univpancasila.perpustakaan.service.*;
import net.datafaker.Faker;

public class Main {
    public static void main(String[] args) {

        System.out.println("=== Komposisi: Buku memiliki Bab ===");
        Buku buku = new Buku("PBO-001", "Pemrograman Berorientasi Objek", "A. W. Pribadi", 3);
        buku.tambahBab(1, "Paradigma Objek");
        buku.tambahBab(2, "Enkapsulasi");
        buku.tambahBab(3, "Pewarisan dan Polimorfisme");
        System.out.println("  " + buku);
        buku.getBab().forEach(b -> System.out.println("    " + b));

        System.out.println();
        System.out.println("  Mencoba mengubah koleksi internal dari luar...");
        try {
            buku.getBab().clear();
            System.out.println("    MASALAH: koleksi internal berhasil dikosongkan dari luar!");
            System.out.println("    Berarti getBab() Anda belum mengembalikan salinan.");
        } catch (UnsupportedOperationException e) {
            System.out.println("    Ditolak - koleksi bab tidak bisa diubah dari luar. Benar.");
        }

        System.out.println();
        System.out.println("=== Agregasi: Jurusan menaungi Mahasiswa ===");
        // penggunaan dependency datafaker
        Faker faker = new Faker();

        Jurusan.Mahasiswa ani  = new Jurusan.Mahasiswa("2024001", "Ani Lestari");
        Jurusan.Mahasiswa budi = new Jurusan.Mahasiswa("2024002", "Budi Santoso");
        Jurusan.Mahasiswa mhs2 = new Jurusan.Mahasiswa("2024003", faker.name().fullName());

        Jurusan informatika = new Jurusan("TI", "Teknik Informatika");
        informatika.daftarkan(ani);
        informatika.daftarkan(budi);
        informatika.daftarkan(mhs2);


        System.out.println("  " + informatika);

        informatika.keluarkan(budi);
        System.out.println("  Setelah Budi pindah: " + informatika);
        System.out.println("  Budi tetap ada sebagai objek: " + budi);
        System.out.println();
        System.out.println("  Itulah bedanya: Bab tidak bisa hidup di luar Buku,"); 
        System.out.println("  tetapi Mahasiswa tetap ada di luar Jurusan.");

        System.out.println();
        System.out.println("========== Perpustakaan Teknik =============");
        PerpustakaanService perpus = new PerpustakaanService();
        perpus.tambahKoleksi(new Buku("PBO-001", "Pemrograman Berorientasi Objek", "A. W. Pribadi", 3));
        Buku hasilPencarian = perpus.cariKoleksi("PBO-001");

        System.out.println("Hasil pencarian buku anda: " + hasilPencarian);
        perpus.pinjam("IT-4554", "PBO-001");
        perpus.kembalikan("IT-4554", "PBO-001");

        // keanggotaan perpustakaan
        System.err.println();
        System.out.println("========== Keanggotaan Perpustakaan Teknik =============");
        LocalDateTime dateNow = LocalDateTime.now();
        Anggota anggota1 = new Anggota("IT", informatika.getNama(), "045");
        anggota1.daftarkan(mhs2, dateNow);
        anggota1.getKeanggotaan();
        System.out.println("Status keanggotaan: " + anggota1.checkMasaKeanggotaan());
    }
}
