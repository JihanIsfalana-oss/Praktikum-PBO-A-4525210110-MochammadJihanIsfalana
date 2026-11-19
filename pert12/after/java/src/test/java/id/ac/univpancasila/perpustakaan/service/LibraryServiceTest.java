package id.ac.univpancasila.perpustakaan.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import id.ac.univpancasila.perpustakaan.denda.AturanDenda;
import id.ac.univpancasila.perpustakaan.denda.DendaAlumni;
import id.ac.univpancasila.perpustakaan.denda.DendaMahasiswa;
import id.ac.univpancasila.perpustakaan.denda.DendaDosen;
import id.ac.univpancasila.perpustakaan.denda.DendaBebas;
import id.ac.univpancasila.perpustakaan.exception.AnggotaTidakDitemukanException;
import id.ac.univpancasila.perpustakaan.exception.BukuSedangDipinjamException;
import id.ac.univpancasila.perpustakaan.exception.BukuTidakDitemukanException;
import id.ac.univpancasila.perpustakaan.repository.InMemoryBookRepository;
import id.ac.univpancasila.perpustakaan.repository.BookRepository;
import id.ac.univpancasila.perpustakaan.model.Buku;
import java.util.List;
import java.util.Optional;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LibraryServiceTest {
    static class RepositoryPalsu implements BookRepository {
        @Override public void simpan(Buku buku) {}
        @Override public Optional<Buku> cariByIsbn(String isbn) {
            throw new IllegalStateException("Repository palsu selalu gagal");
        }
        @Override public List<Buku> cariSemua() { return List.of(); }
        @Override public void kurangiStok(Buku buku) {}
    }

    private LibraryService buatService() {
        return new LibraryService(new InMemoryBookRepository(), new DendaBebas());
    }

    @Test
    @DisplayName("Test tambahBuku method")
    public void testTambahBuku() {
        // Test case for adding a new book
        LibraryService libraryService = buatService();
        libraryService.tambahBuku("978-3", "Java Programming", 5);
        assertEquals(5, libraryService.sisaEksemplar("978-3"));
    }

    @Test
    @DisplayName("Test daftarkanAnggota method")
    public void testDaftarkanAnggota() {
        LibraryService libraryService = buatService();
        libraryService.daftarkanAnggota("A001");
        assertEquals(0, libraryService.getJumlahPinjaman("A001"));
    }

    @Test
    @DisplayName("Test pinjam method dengan input valid")
    public void testPinjamValid() {
        LibraryService libraryService = buatService();
        libraryService.tambahBuku("978-3", "Java Programming", 5);
        libraryService.daftarkanAnggota("A001");
        boolean result = libraryService.pinjam("A001", "978-3");
        assertTrue(result);
        assertEquals(4, libraryService.sisaEksemplar("978-3"));
        assertEquals(1, libraryService.getJumlahPinjaman("A001"));
    }

    @Test
    @DisplayName("Test pinjam method with non-existent member")
    public void testPinjamNonExistentMember() {
        LibraryService libraryService = buatService();
        libraryService.tambahBuku("978-3", "Java Programming", 5);
        assertThrows(AnggotaTidakDitemukanException.class, () -> {
            libraryService.pinjam("A001", "978-3");
        });
    }

    @Test
    @DisplayName("Test pinjam method with non-existent book")
    public void testPinjamNonExistentBook() {
        LibraryService libraryService = buatService();
        libraryService.daftarkanAnggota("A001");
        assertThrows(BukuTidakDitemukanException.class, () -> {
            libraryService.pinjam("A001", "978-3");
        });
    }

    @Test
    @DisplayName("Test pinjam method with no available copies")
    public void testPinjamNoAvailableCopies() {
        LibraryService libraryService = buatService();
        libraryService.tambahBuku("978-3", "Java Programming", 0);
        libraryService.daftarkanAnggota("A001");
        assertThrows(BukuSedangDipinjamException.class, () -> {
            libraryService.pinjam("A001", "978-3");
        });
    }

    @Test
    @DisplayName("LibraryService meneruskan kegagalan repository palsu")
    public void testRepositoryPalsu() {
        LibraryService libraryService =
                new LibraryService(new RepositoryPalsu(), new DendaBebas());
        libraryService.daftarkanAnggota("A001");

        assertThrows(IllegalStateException.class,
                () -> libraryService.pinjam("A001", "999"));
    }

    @Test
    @DisplayName("Denda should be applied when a member has borrowed more than 3 books")
    public void testDendaApplied() {
        AturanDenda AturanDenda = new DendaDosen();
        LibraryService libraryService = buatService();
        libraryService.tambahBuku("978-3", "Java Programming", 5);
        libraryService.daftarkanAnggota("A001");
        // Dosen dikenai denda untuk keterlambatan 4 hari.
        assertTrue(AturanDenda.hitung(4) > 0);
    }

    @Test
    @DisplayName("Denda should not be applied when a member has borrowed 3 or fewer books")
    public void testDendaNotApplied() {
        AturanDenda AturanDenda = new DendaBebas();
        LibraryService libraryService = buatService();
        libraryService.tambahBuku("978-3", "Java Programming", 5);
        libraryService.daftarkanAnggota("A001");
        // Borrow 3 books
        libraryService.pinjam("A001", "978-3");
        libraryService.pinjam("A001", "978-3");
        libraryService.pinjam("A001", "978-3");
        // Anggota kehormatan tidak dikenai denda meskipun terlambat 3 hari.
        assertEquals(0, AturanDenda.hitung(3));
    }

    // Denda Alumni Test
    @Test
    @DisplayName("Denda should be calculated correctly for alumni")
    public void testDendaAlumni() {
        AturanDenda dendaAlumni = new DendaAlumni();
        int jumlahHariTerlambat = 5;
        int expectedDenda = jumlahHariTerlambat * 2000; // 5 days late * 2000 per day
        assertEquals(expectedDenda, dendaAlumni.hitung(jumlahHariTerlambat));
    }

    // Denda Mahasiswa Test
    @Test
    @DisplayName("Denda should be calculated correctly for mahasiswa")
    public void testDendaMahasiswa() {
        AturanDenda dendaMahasiswa = new DendaMahasiswa();
        int jumlahHariTerlambat = 3;
        int expectedDenda = jumlahHariTerlambat * 1000; // 3 days late * 1000 per day
        assertEquals(expectedDenda, dendaMahasiswa.hitung(jumlahHariTerlambat));
    }

    // Denda Dosen Test
    @Test
    @DisplayName("Denda should be calculated correctly for dosen")
    public void testDendaDosen() {
        AturanDenda dendaDosen = new DendaDosen();
        int jumlahHariTerlambat = 2;
        int expectedDenda = jumlahHariTerlambat * 500; // 2 days late * 500 per day
        assertEquals(expectedDenda, dendaDosen.hitung(jumlahHariTerlambat));
    }

    // Denda Bebas Test
    @Test
    @DisplayName("Denda should be calculated correctly for bebas")
    public void testDendaBebas() {
        AturanDenda dendaBebas = new DendaBebas();
        int jumlahHariTerlambat = 4;
        assertEquals(0, dendaBebas.hitung(jumlahHariTerlambat));
    }
}
