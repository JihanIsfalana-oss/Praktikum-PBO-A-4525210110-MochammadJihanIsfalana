package id.ac.univpancasila.perpustakaan.denda;

public class DendaMahasiswa implements AturanDenda {
    public static final double DENDA_PER_HARI = 1000.0;

    @Override 
    public double hitung(int hariTerlambat) {
        return Math.max(0, hariTerlambat) * DENDA_PER_HARI;
    }

    @Override 
    public String nama() {
        return "Mahasiswa (Rp" + DENDA_PER_HARI + "/hari)";
    }
}
