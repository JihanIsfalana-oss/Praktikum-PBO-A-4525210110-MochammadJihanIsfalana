public class Persegi extends BangunDatar {

    private final double sisi;

    public Persegi(double sisi) {
        super("Persegi");
        // TODO 1: tolak sisi <= 0.
        if (sisi <= 0) {
            throw new IllegalArgumentException("Sisi harus lebih besar dari 0");
        }
        this.sisi = sisi;
    }

    // TODO 2: lengkapi luas() dan keliling().
    @Override public double luas()     { 
        return sisi * sisi; }
    @Override public double keliling() { return 4 * sisi; }
}
