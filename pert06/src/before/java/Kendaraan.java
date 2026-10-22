/**
 * Abstract class: menampung kode yang BENAR-BENAR SAMA di semua kendaraan.
 * Bandingkan perannya dengan interface Movable dan Fuelable.
 */
public abstract class Kendaraan {

    protected final String merek;
    protected final int tahun;

    protected Kendaraan(String merek, int tahun) {
        this.merek = merek;
        this.tahun = tahun;
    }

    /** TODO 1: kembalikan umur kendaraan, tidak boleh negatif. */
    public int umur(int tahunSekarang) {
        return 0;   // ganti
    }

    public abstract int jumlahRoda();

    @Override
    public String toString() {
        return String.format("%s (%d, %d roda)", merek, tahun, jumlahRoda());
    }
}
