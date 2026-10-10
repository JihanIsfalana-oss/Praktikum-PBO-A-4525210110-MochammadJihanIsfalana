/**
 * Sesi 5 — polimorfisme.
 * Kelas induk menetapkan KONTRAK; turunan mengisi caranya.
 */
public abstract class BangunDatar {

    private final String nama;

    protected BangunDatar(String nama) { this.nama = nama; }

    /** Kontrak: setiap bangun datar wajib bisa menghitung luas dan kelilingnya. */
    public abstract double luas();
    public abstract double keliling();

    public String getNama() { return nama; }

    /**
     * Perhatikan baris ini baik-baik.
     * toString() ada di kelas INDUK, tetapi memanggil luas() yang isinya
     * hanya ada di TURUNAN. Bagaimana itu mungkin?
     * Jawabannya Anda tulis di penelusuran.md.
     */
    @Override
    public String toString() {
        return String.format("%-12s luas=%10.2f  keliling=%10.2f", nama, luas(), keliling());
    }
}
