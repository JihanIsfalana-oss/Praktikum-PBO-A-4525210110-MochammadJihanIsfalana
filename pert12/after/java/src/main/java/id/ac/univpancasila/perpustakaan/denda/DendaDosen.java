package id.ac.univpancasila.perpustakaan.denda;

public class DendaDosen implements AturanDenda {
    public static final double DENDA_PER_HARI = 500.0;

    @Override
    public double hitung(int hariTerlambat) {
        if (hariTerlambat < 0) {
            return 0;
        }
        return hariTerlambat * 500;
    }

    @Override
    public String nama() {
        return "Dosen (Rp" + DENDA_PER_HARI + "/hari)";
    }
}
