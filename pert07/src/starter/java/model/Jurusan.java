package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Sesi 7 — AGREGASI, sebagai pembanding komposisi pada Buku.
 *
 * Perhatikan bedanya: di sini objek bagian DATANG DARI LUAR.
 * Mahasiswa tetap ada meski Jurusan dibubarkan.
 */
public class Jurusan {

    private final String kode;
    private final String nama;
    private final List<Mahasiswa> mahasiswa = new ArrayList<>();

    public Jurusan(String kode, String nama) {
        this.kode = kode;
        this.nama = nama;
    }

    /**
     * TODO 1: terima objek Mahasiswa dari luar — inilah tanda agregasi.
     *         Tolak null, dan jangan menambahkan yang sudah terdaftar.
     */
    public void daftarkan(Mahasiswa m) {
    }

    /** TODO 2: lepaskan dari jurusan ini. Objeknya TETAP HIDUP di luar sana. */
    public void keluarkan(Mahasiswa m) {
    }

    /** TODO 3: kembalikan salinan tak-berubah. */
    public List<Mahasiswa> getMahasiswa() {
        return mahasiswa;   // ganti
    }

    public String getNama() { return nama; }

    @Override
    public String toString() {
        return String.format("%s - %s (%d mahasiswa)", kode, nama, mahasiswa.size());
    }

    /** Constructor PUBLIK — bisa dibuat di luar. Bandingkan dengan Buku.Bab. */
    public static final class Mahasiswa {
        private final String nim;
        private final String nama;

        public Mahasiswa(String nim, String nama) {
            this.nim = nim;
            this.nama = nama;
        }

        public String getNim()  { return nim; }
        public String getNama() { return nama; }

        @Override public String toString() { return nim + " " + nama; }
    }
}
