package com.sewakos.model;

// Materi 2: SewaKos membungkus data sewa dan menyediakan akses lewat getter.
// Materi 6: SewaKos mengimplementasikan kontrak dari interface DapatDibayar.
// Materi 7: relasi objek terjadi karena SewaKos memiliki objek Penyewa dan Kamar.
public class SewaKos implements DapatDibayar {
    // Materi 3: konstanta dan penghitung ID bersifat static pada level kelas.
    public static final double BIAYA_ADMIN = 50_000.0;
    public static final int MAX_LAMA_SEWA_BULAN = 24;
    private static int urutanSewa = 1;

    private final String idSewa;
    private final Penyewa penyewa;
    private final Kamar kamar;
    private final int lamaBulan;
    private StatusPembayaran statusPembayaran;

    // Materi 3: konstruktor memeriksa seluruh nilai sebelum membuat transaksi.
    public SewaKos(Penyewa penyewa, Kamar kamar, int lamaBulan) throws Exception {
        if (penyewa == null) {
            throw new Exception("Data penyewa tidak boleh kosong.");
        }
        if (kamar == null) {
            throw new Exception("Kamar yang disewa tidak boleh kosong.");
        }
        if (lamaBulan < 1 || lamaBulan > MAX_LAMA_SEWA_BULAN) {
            throw new Exception("Lama sewa harus 1 sampai " + MAX_LAMA_SEWA_BULAN + " bulan.");
        }
        if (kamar.getStatus() != StatusKamar.TERSEDIA) {
            throw new Exception("Kamar " + kamar.getNomorKamar() + " tidak tersedia.");
        }

        this.idSewa = String.format("SW-%03d", urutanSewa++);
        this.penyewa = penyewa;
        this.kamar = kamar;
        this.lamaBulan = lamaBulan;
        this.statusPembayaran = StatusPembayaran.BELUM_BAYAR;
        kamar.tandaiTerisi();
    }

    public String getIdSewa() {
        return idSewa;
    }

    public Penyewa getPenyewa() {
        return penyewa;
    }

    public Kamar getKamar() {
        return kamar;
    }

    public int getLamaBulan() {
        return lamaBulan;
    }

    public StatusPembayaran getStatusPembayaran() {
        return statusPembayaran;
    }

    @Override
    public double hitungTotal() {
        return (kamar.getHargaPerBulan() * lamaBulan) + BIAYA_ADMIN;
    }

    // Materi 1/validasi: nilai pembayaran yang tidak valid ditolak dengan Exception.
    @Override
    public void bayar(double nominal) throws Exception {
        if (!Double.isFinite(nominal) || nominal <= 0) {
            throw new Exception("Nominal pembayaran harus lebih besar dari nol dan berupa angka valid.");
        }
        if (statusPembayaran == StatusPembayaran.LUNAS) {
            throw new Exception("Sewa ini sudah lunas.");
        }
        if (nominal < hitungTotal()) {
            throw new Exception("Pembayaran kurang. Total yang harus dibayar: Rp"
                    + String.format("%.0f", hitungTotal()));
        }
        statusPembayaran = StatusPembayaran.LUNAS;
    }

    @Override
    public String toString() {
        return "ID: " + idSewa
                + " | Penyewa: " + penyewa.getNama()
                + " | Kamar: " + kamar.getNomorKamar()
                + " | Durasi: " + lamaBulan + " bulan"
                + " | Total: Rp" + String.format("%.0f", hitungTotal())
                + " | Pembayaran: " + statusPembayaran;
    }
}
