package com.sewakos.model;

import java.util.Locale;

// Materi 2: kelas Kamar membungkus data kamar dengan enkapsulasi.
public class Kamar {
    // Materi 3: konstanta dan atribut static dimiliki oleh kelas, bukan per objek.
    public static final double MIN_HARGA_PER_BULAN = 100_000.0;
    private static int jumlahKamar = 0;

    private final String nomorKamar;
    private final double hargaPerBulan;
    private StatusKamar status;

    // Materi 3: konstruktor menetapkan keadaan awal objek Kamar.
    public Kamar(String nomorKamar, double hargaPerBulan) throws Exception {
        if (nomorKamar == null || !nomorKamar.matches("[A-Z]\\d{3}")) {
            throw new Exception("Nomor kamar harus berformat huruf kapital diikuti 3 angka, misalnya K001.");
        }
        if (!Double.isFinite(hargaPerBulan) || hargaPerBulan < MIN_HARGA_PER_BULAN) {
            throw new Exception("Harga kamar minimal Rp100.000 per bulan dan harus berupa angka valid.");
        }
        this.nomorKamar = nomorKamar;
        this.hargaPerBulan = hargaPerBulan;
        this.status = StatusKamar.TERSEDIA;
        jumlahKamar++;
    }

    public String getNomorKamar() {
        return nomorKamar;
    }

    public double getHargaPerBulan() {
        return hargaPerBulan;
    }

    public StatusKamar getStatus() {
        return status;
    }

    // Status hanya diubah melalui operasi yang memvalidasi keadaan kamar.
    public void tandaiTerisi() throws Exception {
        if (status != StatusKamar.TERSEDIA) {
            throw new Exception("Kamar " + nomorKamar + " sudah terisi.");
        }
        status = StatusKamar.TERISI;
    }

    // Materi 3: static method dapat dipanggil tanpa membuat objek Kamar baru.
    public static int getJumlahKamar() {
        return jumlahKamar;
    }

    @Override
    public String toString() {
        String harga = String.format(Locale.forLanguageTag("id-ID"), "%,.0f", hargaPerBulan);
        return nomorKamar + " | Rp" + harga + "/bulan | Status: " + status;
    }
}
