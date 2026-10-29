package service;

import model.Buku;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Kerangka lapisan layanan.
 * Pada sesi 7 cukup struktur dan tanda tangan method; isinya boleh minimal.
 * Lapisan ini akan diperdalam pada sesi 10 (exception) dan 12 (SOLID).
 *
 * TODO: sesuaikan seluruh isi berkas ini dengan class diagram Anda sendiri.
 *       Kelas dan method di sini hanya contoh awal.
 */
public class PerpustakaanService {

    // TODO 1: tentukan bagaimana layanan ini menyimpan koleksi dan anggota.
    //         Agregasi atau komposisi? Tuliskan alasannya di analisis.md,
    //         lalu wujudkan keputusan itu di sini.
    private final List<Buku> koleksi = new ArrayList<>();
    private final Set<String> peminjamanAktif = new HashSet<>();

    public void tambahKoleksi(Buku buku) {
        if (buku == null) {
            throw new IllegalArgumentException("buku tidak boleh null");
        }
        if (cariKoleksi(buku.getNomorInduk()) != null) {
            throw new IllegalArgumentException("nomor induk buku sudah terdaftar");
        }
        koleksi.add(buku);
    }

    public List<Buku> getKoleksi() {
        return List.copyOf(koleksi);
    }

    public Buku cariKoleksi(String nomorInduk) {
        // TODO 3
        for (Buku b : koleksi) {
            if (b.getNomorInduk().equals(nomorInduk)) {
                return b;
            }
        }
        return null;
    }

    public void pinjam(String nomorAnggota, String nomorInduk) {
        validasiIdentitas(nomorAnggota, nomorInduk);
        Buku buku = cariKoleksi(nomorInduk);
        if (buku == null) {
            throw new IllegalArgumentException("buku tidak ditemukan");
        }
        String kunci = kunciPeminjaman(nomorAnggota, nomorInduk);
        if (peminjamanAktif.contains(kunci)) {
            throw new IllegalStateException("buku masih dipinjam anggota tersebut");
        }
        buku.kurangiStok();
        peminjamanAktif.add(kunci);
    }

    public void kembalikan(String nomorAnggota, String nomorInduk) {
        validasiIdentitas(nomorAnggota, nomorInduk);
        Buku buku = cariKoleksi(nomorInduk);
        if (buku == null) {
            throw new IllegalArgumentException("buku tidak ditemukan");
        }
        String kunci = kunciPeminjaman(nomorAnggota, nomorInduk);
        if (!peminjamanAktif.remove(kunci)) {
            throw new IllegalStateException("peminjaman tidak ditemukan");
        }
        buku.tambahStok();
    }

    private void validasiIdentitas(String nomorAnggota, String nomorInduk) {
        if (nomorAnggota == null || nomorAnggota.isBlank()) {
            throw new IllegalArgumentException("nomor anggota tidak boleh kosong");
        }
        if (nomorInduk == null || nomorInduk.isBlank()) {
            throw new IllegalArgumentException("nomor induk tidak boleh kosong");
        }
    }

    private String kunciPeminjaman(String nomorAnggota, String nomorInduk) {
        return nomorAnggota + "\u0000" + nomorInduk;
    }
}
