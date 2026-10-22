public class Main {
    public static void main(String[] args) {
        System.out.println("Jumlah rekening di awal: " + RekeningBank.getJumlahRekening());

        RekeningBank a = new RekeningBank("111", "Ani", 1_000_000);
        RekeningBank b = new RekeningBank("222", "Budi");        // constructor ringkas
        RekeningBank c = new RekeningBank("333", "Citra", 250_000);

        System.out.println(a);
        System.out.println(b);
        System.out.println(c);

        System.out.println("Jumlah rekening sekarang: " + RekeningBank.getJumlahRekening()
                           + "   (seharusnya 3, bukan 4)");

        System.out.println();
        System.out.println("=== Operasi ===");
        a.setor(500_000);
        System.out.println("Setelah setor 500.000  -> " + a);

        try {
            a.tarik(9_999_999);
            System.out.println("  MASALAH: penarikan melebihi batas seharusnya ditolak!");
        } catch (RuntimeException e) {
            System.out.println("  Ditolak: " + e.getMessage());
        }

        b.potongBiayaAdmin();
        System.out.println("Budi setelah potong admin: " + b + "   (saldo tidak boleh negatif)");

        System.out.printf("Bunga setahun dari saldo Ani: Rp%,.2f%n",
                RekeningBank.bungaSetahun(a.getSaldo()));
    }
}
