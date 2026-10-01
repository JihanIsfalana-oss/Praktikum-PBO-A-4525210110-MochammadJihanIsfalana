public class Trapesium extends BangunDatar {

    private final double sisiAtas;
    private final double sisiBawah;
    private final double tinggi;

    public Trapesium(double sisiAtas, double sisiBawah, double tinggi) {
        super("Trapesium");
        // TODO 1: tolak sisi <= 0.
        if (sisiAtas <= 0 || sisiBawah <= 0 || tinggi <= 0) {
            throw new IllegalArgumentException("Sisi dan tinggi harus lebih besar dari 0");
        }
        this.sisiAtas = sisiAtas;
        this.sisiBawah = sisiBawah;
        this.tinggi = tinggi;
    }

    // TODO 2: lengkapi luas() dan keliling().
    @Override
    public double luas() {
        return ((sisiAtas + sisiBawah) / 2) * tinggi;
    }

    @Override
    public double keliling() {
        // Asumsi trapesium sama kaki untuk menghitung keliling
        double sisiMiring = Math.sqrt(Math.pow((sisiBawah - sisiAtas) / 2, 2) + Math.pow(tinggi, 2));
        return sisiAtas + sisiBawah + 2 * sisiMiring;
    }

}
