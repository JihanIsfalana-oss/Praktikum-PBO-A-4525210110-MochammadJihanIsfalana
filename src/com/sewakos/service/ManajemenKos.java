package com.sewakos.service;

import com.sewakos.model.Kamar;
import com.sewakos.model.Pengelola;
import com.sewakos.model.PenggunaKos;
import com.sewakos.model.Penyewa;
import com.sewakos.model.SewaKos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Materi 7: kelas ini mengelola relasi/kumpulan objek kamar, pengguna, dan sewa.
public class ManajemenKos {
    private final List<Kamar> daftarKamar = new ArrayList<Kamar>();
    private final List<SewaKos> daftarSewa = new ArrayList<SewaKos>();
    private final List<PenggunaKos> daftarPengguna = new ArrayList<PenggunaKos>();

    public ManajemenKos() throws Exception {
        daftarKamar.add(new Kamar("K001", 750_000));
        daftarKamar.add(new Kamar("K002", 900_000));
        daftarKamar.add(new Kamar("K003", 1_200_000));
        daftarPengguna.add(new Pengelola("Admin Kos", "081234567890", "PG-001"));
    }

    public List<Kamar> getDaftarKamar() {
        return Collections.unmodifiableList(daftarKamar);
    }

    public List<SewaKos> getDaftarSewa() {
        return Collections.unmodifiableList(daftarSewa);
    }

    public void tampilkanKamar() {
        System.out.println("\n=== DAFTAR KAMAR ===");
        for (Kamar kamar : daftarKamar) {
            System.out.println(kamar);
        }
        System.out.println("Jumlah kamar terdaftar: " + Kamar.getJumlahKamar());
    }

    public SewaKos buatSewa(String nama, String telepon, String identitas,
                            String nomorKamar, int lamaBulan) throws Exception {
        if (nomorKamar == null || nomorKamar.trim().isEmpty()) {
            throw new Exception("Nomor kamar tidak boleh kosong.");
        }

        Kamar kamarDipilih = null;
        for (Kamar kamar : daftarKamar) {
            if (kamar.getNomorKamar().equalsIgnoreCase(nomorKamar.trim())) {
                kamarDipilih = kamar;
                break;
            }
        }
        if (kamarDipilih == null) {
            throw new Exception("Nomor kamar tidak ditemukan.");
        }

        Penyewa penyewa = new Penyewa(nama, telepon, identitas);
        SewaKos sewa = new SewaKos(penyewa, kamarDipilih, lamaBulan);
        daftarSewa.add(sewa);
        daftarPengguna.add(penyewa);
        return sewa;
    }

    public SewaKos cariSewa(String idSewa) throws Exception {
        if (idSewa == null || idSewa.trim().isEmpty()) {
            throw new Exception("ID sewa tidak boleh kosong.");
        }
        for (SewaKos sewa : daftarSewa) {
            if (sewa.getIdSewa().equalsIgnoreCase(idSewa.trim())) {
                return sewa;
            }
        }
        throw new Exception("ID sewa tidak ditemukan.");
    }

    public void tampilkanSewa() {
        System.out.println("\n=== DAFTAR SEWA ===");
        if (daftarSewa.isEmpty()) {
            System.out.println("Belum ada transaksi sewa.");
            return;
        }
        for (SewaKos sewa : daftarSewa) {
            System.out.println(sewa);
        }
    }

    public void tampilkanPengguna() {
        System.out.println("\n=== DAFTAR PENGGUNA ===");
        // Materi 5: polymorphism — tipe referensi PenggunaKos memanggil override subclass.
        for (PenggunaKos pengguna : daftarPengguna) {
            pengguna.tampilkanInfo();
        }
    }
}
