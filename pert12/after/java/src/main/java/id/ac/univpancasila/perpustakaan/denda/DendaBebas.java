package id.ac.univpancasila.perpustakaan.denda;

public class DendaBebas implements AturanDenda {


    @Override
    public double hitung(int hariTerlambat) {
        return 0;
    }

    @Override
    public String nama() {
        return "Freedom (Bebas Denda)";
    }
}
