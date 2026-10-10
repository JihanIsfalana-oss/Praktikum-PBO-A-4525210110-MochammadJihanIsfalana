public class Sepeda extends Kendaraan implements Movable {

    public Sepeda(String merek, int tahun) {
        super(merek, tahun);
    }

    @Override
    public void bergerak() {
        System.out.println(super.merek + " melaju di jalan raya");
    }

    @Override
    public double kecepatanMaksimum() {
        return 25.0; // kecepatan maksimum sepeda dalam km/jam
    }

    @Override
    public int jumlahRoda() {
        return 2; // sepeda memiliki 2 roda
    }

}
