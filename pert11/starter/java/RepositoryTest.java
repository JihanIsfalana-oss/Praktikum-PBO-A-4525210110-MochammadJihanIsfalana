package id.ac.univpancasila.perpustakaan;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Sesi 11 — unit test pertama Anda.
 *
 * Letakkan berkas ini di src/test/java/id/ac/univpancasila/perpustakaan/
 * Jalankan dengan: mvn test
 *
 * Konvensi Maven: hanya berkas di src/test/java yang bernama *Test
 * yang akan dijalankan sebagai pengujian.
 */
class RepositoryTest {

    /** Objek sederhana untuk diuji. */
    record Buku(String isbn, String judul) {}

    private Repository<Buku> repo;

    @BeforeEach
    void siapkan() {
        // Dijalankan ulang sebelum SETIAP pengujian, sehingga tiap uji
        // berangkat dari keadaan yang bersih dan tidak saling memengaruhi.
        repo = new Repository<>(Buku::isbn);
    }

    @Test
    @DisplayName("Item yang disimpan dapat ditemukan kembali")
    void simpanLaluTemukan() {
        repo.simpan(new Buku("978-1", "PBO"));

        // TODO 1: pastikan cariById("978-1") berisi nilai,
        //         dan judulnya "PBO".
        //         Petunjuk: assertTrue(...isPresent()) dan assertEquals(...)
        fail("TODO 1 belum dikerjakan");
    }

    @Test
    @DisplayName("Mencari id yang tidak ada mengembalikan Optional kosong")
    void cariYangTidakAda() {
        // TODO 2
        fail("TODO 2 belum dikerjakan");
    }

    @Test
    @DisplayName("Item yang dihapus tidak dapat ditemukan lagi")
    void hapusItem() {
        // TODO 3
        fail("TODO 3 belum dikerjakan");
    }

    @Test
    @DisplayName("Repository baru berisi nol item")
    void repositoryKosong() {
        // TODO 4
        fail("TODO 4 belum dikerjakan");
    }

    @Test
    @DisplayName("Koleksi yang dikembalikan cariSemua tidak dapat diubah dari luar")
    void koleksiTidakBocor() {
        repo.simpan(new Buku("978-1", "PBO"));

        // TODO 5: pastikan memanggil .add(...) pada hasil cariSemua()
        //         melemparkan UnsupportedOperationException.
        //         Petunjuk: assertThrows(UnsupportedOperationException.class, () -> ...)
        fail("TODO 5 belum dikerjakan");
    }

    // TODO 6: tambahkan minimal TIGA pengujian lagi dari daftar skenario
    //         yang Anda susun pada sesi 10, termasuk satu yang memastikan
    //         exception yang benar dilemparkan.
}
