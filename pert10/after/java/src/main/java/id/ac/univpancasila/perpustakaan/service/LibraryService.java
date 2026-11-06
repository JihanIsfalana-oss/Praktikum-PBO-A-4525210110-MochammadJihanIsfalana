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

import id.ac.univpancasila.perpustakaan.exception.*;

public class LibraryService {

    public static final int BATAS_PINJAM = 2;

    private final Map<String, Integer> stokBuku = new HashMap<>();
    private final Map<String, String> judulBuku = new HashMap<>();
    private final Map<String, Integer> pinjaman = new HashMap<>();

    public void tambahBuku(String isbn, String judulBuku, int eksemplar) {
        stokBuku.merge(isbn, eksemplar, Integer::sum);
        this.judulBuku.put(isbn, judulBuku);
    }

    public void daftarkanAnggota(String idAnggota) {
        pinjaman.putIfAbsent(idAnggota, 0);
    }

    /** INILAH yang harus Anda refaktor. */
    public boolean pinjam(String idAnggota, String isbn) {
        if (!pinjaman.containsKey(idAnggota)) {
            throw new AnggotaTidakDitemukanException(idAnggota);
        }

        if (!stokBuku.containsKey(isbn)){
            throw new BukuTidakDitemukanException(isbn);
        }

        if (stokBuku.get(isbn) <= 0){
            throw new BukuSedangDipinjamException(isbn, judulBuku.get(isbn));
        }

        if (pinjaman.get(idAnggota) >= BATAS_PINJAM){
            throw new BatasPinjamTerlampauiException(idAnggota, BATAS_PINJAM);
        }

        stokBuku.merge(isbn, -1, Integer::sum);
        pinjaman.merge(idAnggota, 1, Integer::sum);
        return true;
    }

    public int sisaEksemplar(String isbn) {
        return stokBuku.getOrDefault(isbn, 0);
    }

    public String judulDari(String isbn) {
        return judulBuku.get(isbn);
    }

    public Map<String, String> getSemuaJudul() {
        return this.judulBuku;
    }
}
