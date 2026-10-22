import model.Buku;
import model.Jurusan;

/**
 * Kompilasi dari folder sesi-07/java:
 *     javac -d out $(find . -name "*.java")
 *     java -cp out Main
 */
public class Main {
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
        Jurusan.Mahasiswa ani  = new Jurusan.Mahasiswa("2024001", "Ani Lestari");
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
    }
}
