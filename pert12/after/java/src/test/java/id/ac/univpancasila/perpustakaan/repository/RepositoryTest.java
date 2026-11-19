package id.ac.univpancasila.perpustakaan.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import id.ac.univpancasila.perpustakaan.exception.BukuTidakDitemukanException;
import id.ac.univpancasila.perpustakaan.service.LibraryService;
import id.ac.univpancasila.perpustakaan.denda.DendaBebas;
import id.ac.univpancasila.perpustakaan.repository.InMemoryBookRepository;

class RepositoryTest {
    record Buku(String isbn, String judul) {}

    private Repository<Buku> repo;

    @BeforeEach
    void siapkan() {
        repo = new Repository<>(Buku::isbn);
    }

    @Test
    @DisplayName("Item yang disimpan dapat ditemukan kembali")
    void simpanLaluTemukan() {
        repo.simpan(new Buku("978-1", "PBO"));
        assertTrue(repo.cariById("978-1").isPresent());
        assertEquals("PBO", repo.cariById("978-1").orElseThrow().judul());
    }

    @Test
    @DisplayName("Mencari id yang tidak ada mengembalikan Optional kosong")
    void cariYangTidakAda() {
        assertTrue(repo.cariById("tidak-ada").isEmpty());
    }

    @Test
    @DisplayName("Item yang dihapus tidak dapat ditemukan lagi")
    void hapusItem() {
        repo.simpan(new Buku("978-1", "PBO"));
        assertTrue(repo.hapus("978-1"));
        assertFalse(repo.cariById("978-1").isPresent());
    }

    @Test
    @DisplayName("Repository baru berisi nol item")
    void repositoryKosong() {
        assertEquals(0, repo.jumlah());
        assertTrue(repo.cariSemua().isEmpty());
    }

    @Test
    @DisplayName("Koleksi yang dikembalikan tidak dapat diubah dari luar")
    void koleksiTidakBocor() {
        repo.simpan(new Buku("978-1", "PBO"));
        assertThrows(UnsupportedOperationException.class,
                () -> repo.cariSemua().add(new Buku("978-2", "Java")));
    }

    @Test
    @DisplayName("Kunci yang sama menimpa item lama")
    void kunciSamaMenimpaItem() {
        repo.simpan(new Buku("978-1", "Judul Lama"));
        repo.simpan(new Buku("978-1", "Judul Baru"));
        assertEquals(1, repo.jumlah());
        assertEquals("Judul Baru", repo.cariById("978-1").orElseThrow().judul());
    }

    @Test
    @DisplayName("Menyimpan item atau kunci kosong ditolak")
    void validasiInput() {
        assertThrows(IllegalArgumentException.class, () -> repo.simpan(null));
        assertThrows(IllegalArgumentException.class,
                () -> repo.simpan(new Buku(" ", "Tanpa ISBN")));
    }

    @Test
    @DisplayName("Pinjam ISBN yang tidak terdaftar melempar exception domain")
    void pinjamBukuTidakDitemukan() {
        LibraryService service = new LibraryService(new InMemoryBookRepository(), new DendaBebas());
        service.daftarkanAnggota("A-001");
        assertThrows(BukuTidakDitemukanException.class,
                () -> service.pinjam("A-001", "999"));
    }
}
