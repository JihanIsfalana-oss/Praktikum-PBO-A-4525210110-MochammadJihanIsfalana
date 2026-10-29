package id.ac.univpancasila.perpustakaan.main;

import id.ac.univpancasila.perpustakaan.model.Buku;
import id.ac.univpancasila.perpustakaan.model.Jurusan;

import java.time.LocalDateTime;

import id.ac.univpancasila.perpustakaan.model.Anggota;
import id.ac.univpancasila.perpustakaan.service.PerpustakaanService;

/**
 * Kompilasi dari folder sesi-07/java:
 * javac -d out $(find . -name "*.java")
 * java -cp out Main
 */
public class App {
    public static void main(String[] args) {

        System.out.println("=== Komposisi: Buku memiliki Bab ===");
        Buku buku = new Buku("PBO-001", "Pemrograman Berorientasi Objek", "A. W. Pribadi", 3);
        buku.tambahBab(1, "Paradigma Objek");
        buku.tambahBab(2, "Enkapsulasi");
        buku.tambahBab(3, "Pewarisan dan Polimorfisme");
        System.out.println("  " + buku);
        buku.getBab().forEach(b -> System.out.println("    " + b));

        // Percobaan bukti-relasi.md nomor 1:
        System.out.println();
        System.out.println("  Mencoba mengubah koleksi internal dari luar...");
        try {
            buku.getBab().clear();
            System.out.println("    MASALAH: koleksi internal berhasil dikosongkan dari luar!");
            System.out.println("    Berarti getBab() Anda belum mengembalikan salinan.");
        } catch (UnsupportedOperationException e) {
            System.out.println("    Ditolak — koleksi bab tidak bisa diubah dari luar. Benar.");
        }

        System.out.println();
        System.out.println("=== Agregasi: Jurusan menaungi Mahasiswa ===");
        Jurusan.Mahasiswa ani = new Jurusan.Mahasiswa("2024001", "Ani Lestari");
        Jurusan.Mahasiswa budi = new Jurusan.Mahasiswa("2024002", "Budi Santoso");

        Jurusan informatika = new Jurusan("TI", "Teknik Informatika");
        informatika.daftarkan(ani);
        informatika.daftarkan(budi);
        System.out.println("  " + informatika);

        informatika.keluarkan(budi);
        System.out.println("  Setelah Budi pindah: " + informatika);
        System.out.println("  Budi tetap ada sebagai objek: " + budi);
        System.out.println();
        System.out.println("  Itulah bedanya: Bab tidak bisa hidup di luar Buku,");
        System.out.println("  tetapi Mahasiswa tetap ada di luar Jurusan.");

        System.out.println();
        System.out.println("=== Agregasi: Perpustakaan ===");
        PerpustakaanService perpustakaan = new PerpustakaanService();
        perpustakaan.tambahKoleksi(new Buku("PBO-001", "Pemrograman Berorientasi Objek", "A. W. Pribadi", 3));
        perpustakaan.tambahKoleksi(new Buku("PBO-002", "Pemrograman Berorientasi Objek Lanjut", "A. W. Pribadi", 4));
        perpustakaan.tambahKoleksi(new Buku("DB-001", "Basis Data", "Moch. Jihan Isfalana", 2));

        System.out.println();
        System.out.println("  Meminjam buku PBO-001...");
        perpustakaan.pinjam("2024001", "PBO-001");
        System.out.println("  Stok buku PBO-001 sekarang: " + perpustakaan.cariKoleksi("PBO-001").getJumlahEksemplar());

        System.out.println();
        System.out.println("  Mengembalikan buku PBO-001...");
        perpustakaan.kembalikan("2024001", "PBO-001");
        System.out.println("  Stok buku PBO-001 sekarang: " + perpustakaan.cariKoleksi("PBO-001").getJumlahEksemplar());

        System.out.println();
        System.out.println("=== Agregasi: Anggota Jurusan ===");
        Anggota anggota1 = new Anggota("TI", "Informatika", "2024001");
        Anggota anggota2 = new Anggota("TI", "Informatika", "2024002");
        anggota1.daftarkan(budi, LocalDateTime.now().minusYears(4).minusMonths(2));
        anggota2.daftarkan(ani, LocalDateTime.now().minusDays(10));
        System.out.println();
        System.out.println("  Setelah pendaftaran anggota:");
        anggota1.getKeanggotaan();
        anggota2.getKeanggotaan();
        System.out.println();
        System.out.println("  Masa keanggotaan:");
        System.out.println(anggota1.checkMasaKeanggotaan());
        System.out.println();
        System.out.println("  Masa keanggotaan:");
        System.out.println(anggota2.checkMasaKeanggotaan());
    }
}

