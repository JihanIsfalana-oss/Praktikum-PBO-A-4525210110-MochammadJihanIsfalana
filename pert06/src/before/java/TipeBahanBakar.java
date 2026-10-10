/**
 * Enum: hanya nilai yang terdaftar di sini yang mungkin ada.
 * Bandingkan dengan `public static final int BENSIN = 1;`
 * yang membiarkan angka 99 lolos begitu saja.
 */
public enum TipeBahanBakar {

    // TODO 1: lengkapi konstanta enum beserta label dan harga per satuannya.
    //         BENSIN  -> "Bensin",  12000
    //         SOLAR   -> "Solar",   10500
    //         LISTRIK -> "Listrik",  2500   (per kWh)
    BENSIN("Bensin", 0),
    SOLAR("Solar", 0);
    // TODO 2 (Langkah 2): tambahkan LISTRIK.

    private final String label;
    private final double hargaPerSatuan;

    TipeBahanBakar(String label, double hargaPerSatuan) {
        this.label = label;
        this.hargaPerSatuan = hargaPerSatuan;
    }

    public String getLabel() { return label; }

    /** TODO 3: enum boleh punya method — ini yang tidak bisa dilakukan konstanta int. */
    public double biayaPengisian(double jumlah) {
        return 0;   // ganti
    }

    /** TODO 4: kembalikan true hanya untuk LISTRIK. Petunjuk: this == LISTRIK */
    public boolean ramahLingkungan() {
        return false;   // ganti
    }
}
