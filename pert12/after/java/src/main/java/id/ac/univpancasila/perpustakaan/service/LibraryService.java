package id.ac.univpancasila.perpustakaan.service;
/**
 * Sesi 10 Langkah 2 — BAHAN REFACTORING.
 *
 * Method pinjam() di bawah bersarang EMPAT tingkat. Bacalah, lalu rasakan
 * sendiri betapa sulitnya mengikuti alurnya.
 *
 * Tugas Anda: refaktor menjadi guard clause, simpan sebagai LibraryService.java.
 * JANGAN hapus berkas ini — keduanya diperlukan saat demo.
 *
 * Pertanyaan pemandu:
 *   1. Di mana letak logika utamanya? Berapa tingkat menjorok ke dalam?
 *   2. Apa yang terjadi kalau salah satu kondisi tidak terpenuhi?
 *      Apakah pemanggil bisa tahu ALASANNYA?
 *   3. Bagaimana Anda menambahkan satu aturan baru di tengah-tengah ini?
 */
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import id.ac.univpancasila.perpustakaan.exception.AnggotaTidakDitemukanException;
import id.ac.univpancasila.perpustakaan.exception.BatasPinjamTerlampauiException;
import id.ac.univpancasila.perpustakaan.exception.BukuSedangDipinjamException;
import id.ac.univpancasila.perpustakaan.exception.BukuTidakDitemukanException;
import id.ac.univpancasila.perpustakaan.denda.AturanDenda;
import id.ac.univpancasila.perpustakaan.model.Buku;
import id.ac.univpancasila.perpustakaan.repository.BookRepository;

public class LibraryService {

    public static final int BATAS_PINJAM = 3;

    private final BookRepository repository;
    private final AturanDenda aturanDenda;
    private final Map<String, Integer> pinjaman = new HashMap<>();

    public LibraryService(BookRepository repository, AturanDenda aturanDenda) {
        if (repository == null || aturanDenda == null) {
            throw new IllegalArgumentException("Dependensi LibraryService tidak boleh null");
        }
        this.repository = repository;
        this.aturanDenda = aturanDenda;
    }

    public void tambahBuku(String isbn, String judulBuku, int eksemplar) {
        repository.simpan(new Buku(isbn, judulBuku, "", eksemplar));
    }

    public void daftarkanAnggota(String idAnggota) {
        pinjaman.putIfAbsent(idAnggota, 0);
    }

    /** INILAH yang harus Anda refaktor. */
    public boolean pinjam(String idAnggota, String isbn) {
        if (!pinjaman.containsKey(idAnggota)) {
            throw new AnggotaTidakDitemukanException(idAnggota);
        }

        Optional<Buku> buku = repository.cariByIsbn(isbn);
        if (buku.isEmpty()) throw new BukuTidakDitemukanException(isbn);
        if (!buku.get().tersedia()) throw new BukuSedangDipinjamException(isbn, buku.get().getJudul());

        if (pinjaman.get(idAnggota) >= BATAS_PINJAM){
            throw new BatasPinjamTerlampauiException(idAnggota, BATAS_PINJAM);
        }

        repository.kurangiStok(buku.get());
        pinjaman.merge(idAnggota, 1, Integer::sum);
        return true;
    }

    public int sisaEksemplar(String isbn) {
        return repository.cariByIsbn(isbn).map(Buku::getJumlahEksemplar).orElse(0);
    }

    public String judulDari(String isbn) {
        return repository.cariByIsbn(isbn).map(Buku::getJudul).orElse(null);
    }

    public Map<String, String> getSemuaJudul() {
        Map<String, String> hasil = new HashMap<>();
        repository.cariSemua().forEach(b -> hasil.put(b.getNomorInduk(), b.getJudul()));
        return Map.copyOf(hasil);
    }

    public Map<String, Integer> getPinjaman() {
        return Map.copyOf(this.pinjaman);
    }

    public int getJumlahPinjaman(String idAnggota) {
        return pinjaman.getOrDefault(idAnggota, 0);
    }

    public double hitungDenda(int hariTerlambat) {
        return aturanDenda.hitung(hariTerlambat);
    }
}

