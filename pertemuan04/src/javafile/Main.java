package javafile;

public class Main {
    public static void main(String[] args) {

        // TODO Langkah 4: tambahkan Dosen dan PegawaiHarian ke daftar ini
        //                 setelah Anda membuat kelasnya.
        Pegawai[] daftar = {
            new PegawaiTetap("198701012010", "Ani Lestari",  6_000_000, 15),
            new PegawaiKontrak("K-2024-007",  "Budi Santoso", 5_000_000, 12),
            new Dosen("198501012010", "Dr. Ir. Jihan Isfalana, S.T., M.Kom.", 8_000_000, 10, "Matematika"),
            new PegawaiHarian("H-2024-001", "Dedi Pratama", 50_000, 160)
        };

        System.out.println("=== Daftar Gaji ===");
        for (Pegawai p : daftar) {
            System.out.println("  " + p);
        }

        double total = 0;
        for (Pegawai p : daftar) total += p.hitungGaji();
        System.out.printf("%n  Total beban gaji: Rp%,.2f%n", total);

        System.out.println();
        System.out.println("Periksa: Ani (pokok 6.000.000, masa kerja 15 tahun)");
        System.out.println("  tunjangan 15 x 2% = 30%, jadi gaji seharusnya Rp7.800.000,00");
        System.out.println("  jenis: " + daftar[0].jenis());

        System.out.println();
        System.out.println("Periksa: Budi (gaji 5.000.000, masa kerja 12 bulan)");
        System.out.println("  gaji seharusnya Rp5.000.000,00");
        System.out.println("  jenis: " + daftar[1].jenis());

        System.out.println();
        System.out.println("Periksa: Jihan (pokok 8.000.000, masa kerja 10 tahun)");
        System.out.println("  tunjangan 10 x 2% = 20%, jadi gaji seharusnya Rp9.600.000,00");
        System.out.println("  jenis: " + daftar[2].jenis());

        System.out.println();
        System.out.println("Periksa: Dedi (upah 50.000, jam kerja 160)");
        System.out.println("  gaji seharusnya Rp8.000.000,00");
        System.out.println("  jenis: " + daftar[3].jenis());

        // Percobaan Langkah 1: hapus komentar baris berikut, kompilasi, catat pesannya.
        Pegawai langsung = new Pegawai("X", "Y", 1000) { public String jenis() { return "?"; } };
        System.out.println("Periksa: langsung (gaji 1.000)");
        System.out.println("  gaji seharusnya Rp1.000,00");
        System.out.println("  jenis: " + langsung.jenis());
    }
}
