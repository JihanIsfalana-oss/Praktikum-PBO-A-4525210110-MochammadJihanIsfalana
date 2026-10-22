public class Lingkaran extends BangunDatar {

    private final double jariJari;

    public Lingkaran(double jariJari) {
        super("Lingkaran");
        // TODO 1: tolak jari-jari <= 0.
        this.jariJari = jariJari;
    }

    // TODO 2: lengkapi luas() dan keliling().
    //         Gunakan Math.PI, bukan angka 3.14.
    @Override public double luas()     { return 0; }
    @Override public double keliling() { return 0; }

    public double getJariJari() { return jariJari; }
}
