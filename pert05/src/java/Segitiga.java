public class Segitiga extends BangunDatar {

    private final double sisiA;
    private final double sisiB;
    private final double sisiC;

    public Segitiga(double sisiA, double sisiB, double sisiC) {
        super("Segitiga");
        // TODO 1: tolak sisi <= 0.
        if (sisiA <= 0 || sisiB <= 0 || sisiC <= 0) {
            throw new IllegalArgumentException("Sisi harus lebih besar dari 0");
        }
        this.sisiA = sisiA;
        this.sisiB = sisiB;
        this.sisiC = sisiC;
    }

    // TODO 2: lengkapi luas() dan keliling().
    @Override
    public double luas() {
        double s = (sisiA + sisiB + sisiC) / 2; // semi-perimeter
        return Math.sqrt(s * (s - sisiA) * (s - sisiB) * (s - sisiC)); // Heron's formula
    }

    @Override
    public double keliling() {
        return sisiA + sisiB + sisiC;
    }

}
