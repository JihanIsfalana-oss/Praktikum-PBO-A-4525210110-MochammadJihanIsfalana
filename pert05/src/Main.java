/**
 * Program uji.
 * ATURAN LANGKAH 1-2: Anda hanya boleh MENAMBAH baris ke dalam array.
 * Logika perulangan di bawah TIDAK BOLEH diubah sama sekali.
 * Kalau Anda merasa perlu mengubahnya, rancangan Anda belum polimorfik.
 */
public class Main {
    public static void main(String[] args) {

        // Upcasting: variabel bertipe induk, objek bertipe turunan.
        BangunDatar[] daftar = {
            new Lingkaran(7),
            new Persegi(5),
            // TODO Langkah 2: tambahkan new Segitiga(3, 4, 5) setelah kelasnya dibuat.
            new Segitiga(3, 4, 5),
            // TODO Langkah 4: tambahkan Trapesium setelah kelasnya dibuat.
            new Trapesium(3, 4, 5),
        };

        System.out.println("=== Bangun Datar ===");
        for (BangunDatar b : daftar) {
            System.out.println("  " + b);
        }

        double total = 0;
        for (BangunDatar b : daftar) total += b.luas();
        System.out.printf("%n  Total luas: %.2f%n", total);

        System.out.println();
        System.out.println("Periksa: Lingkaran(7) luas = 153,94 ; Persegi(5) luas = 25,00");
        System.out.println("         Segitiga(3,4,5) luas = 6,00");

        System.out.println();
        System.out.println("=== Downcasting hanya bila benar-benar perlu ===");
        for (BangunDatar b : daftar) {
            if (b instanceof Lingkaran l) {
                System.out.printf("  %s punya jari-jari %.1f%n", l.getNama(), l.getJariJari());
            }
        }
    }
}
