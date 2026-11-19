package id.ac.univpancasila.perpustakaan.denda;

public class DendaAlumni implements AturanDenda {
    private static final int DENDA_PER_HARI = 2000; // Denda per hari untuk alumni

    @Override 
    public String nama() {
        return "Alumni";
    }

    @Override
    public double hitung(int hariTerlambat) {
        if (hariTerlambat <= 0) {
            return 0;
        }
        return hariTerlambat * DENDA_PER_HARI;
    }
    
}
