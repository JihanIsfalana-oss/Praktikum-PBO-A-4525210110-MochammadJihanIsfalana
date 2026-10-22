package com.sewakos.app;

import com.sewakos.model.SewaKos;
import com.sewakos.service.ManajemenKos;

import java.util.Scanner;

// Materi GitHub: semua file Java ini dikelola sebagai satu proyek/repository.
// Commit dan push dilakukan melalui Git/GitHub; GitHub bukan sintaks bahasa Java.
// Materi 2: Main membuat objek ManajemenKos lalu berinteraksi dengan objek tersebut.
public class Main {
    public static void main(String[] args) {
        try {
            ManajemenKos manajemen = new ManajemenKos();
            Scanner scanner = new Scanner(System.in);
            jalankanMenu(scanner, manajemen);
            scanner.close();
        } catch (Exception e) {
            System.out.println("Program tidak dapat dijalankan: " + e.getMessage());
        }
    }

    private static void jalankanMenu(Scanner scanner, ManajemenKos manajemen) {
        boolean berjalan = true;
        while (berjalan) {
            System.out.println("\n========= APLIKASI SEWA KOS =========");
            System.out.println("1. Lihat kamar");
            System.out.println("2. Sewa kamar");
            System.out.println("3. Bayar sewa");
            System.out.println("4. Lihat daftar sewa");
            System.out.println("5. Lihat pengguna (polimorfisme)");
            System.out.println("0. Keluar");
            System.out.print("Pilih menu: ");
            String pilihan = scanner.nextLine().trim();

            try {
                switch (pilihan) {
                    case "1":
                        manajemen.tampilkanKamar();
                        break;
                    case "2":
                        prosesSewa(scanner, manajemen);
                        break;
                    case "3":
                        prosesPembayaran(scanner, manajemen);
                        break;
                    case "4":
                        manajemen.tampilkanSewa();
                        break;
                    case "5":
                        manajemen.tampilkanPengguna();
                        break;
                    case "0":
                        berjalan = false;
                        System.out.println("Program selesai.");
                        break;
                    default:
                        throw new Exception("Pilihan menu tidak tersedia.");
                }
            } catch (Exception e) {
                System.out.println("Gagal: " + e.getMessage());
            }
        }
    }

    private static void prosesSewa(Scanner scanner, ManajemenKos manajemen) throws Exception {
        System.out.println("\n=== INPUT SEWA KAMAR ===");
        System.out.print("Nama penyewa: ");
        String nama = scanner.nextLine();
        System.out.print("Nomor telepon (contoh 081234567890): ");
        String telepon = scanner.nextLine().trim();
        System.out.print("Nomor identitas (minimal 5 karakter): ");
        String identitas = scanner.nextLine().trim();
        System.out.print("Nomor kamar (contoh K001): ");
        String nomorKamar = scanner.nextLine().trim();
        int lamaBulan = bacaInteger(scanner, "Lama sewa dalam bulan (1-24): ");

        SewaKos sewa = manajemen.buatSewa(nama, telepon, identitas, nomorKamar, lamaBulan);
        System.out.println("Sewa berhasil dibuat.");
        System.out.println("ID sewa: " + sewa.getIdSewa());
        System.out.printf("Total tagihan (termasuk biaya admin Rp%.0f): Rp%.0f%n",
                SewaKos.BIAYA_ADMIN, sewa.hitungTotal());
        System.out.println("Status pembayaran: " + sewa.getStatusPembayaran());
    }

    private static void prosesPembayaran(Scanner scanner, ManajemenKos manajemen) throws Exception {
        manajemen.tampilkanSewa();
        System.out.print("Masukkan ID sewa: ");
        String idSewa = scanner.nextLine().trim();
        SewaKos sewa = manajemen.cariSewa(idSewa);
        System.out.printf("Total tagihan: Rp%.0f%n", sewa.hitungTotal());
        double nominal = bacaDouble(scanner, "Nominal pembayaran: Rp");
        sewa.bayar(nominal);
        System.out.println("Pembayaran berhasil. Status: " + sewa.getStatusPembayaran());
    }

    // Input angka tidak valid diubah menjadi Exception agar ditangani oleh menu utama.
    private static int bacaInteger(Scanner scanner, String pesan) throws Exception {
        System.out.print(pesan);
        String nilai = scanner.nextLine().trim();
        try {
            return Integer.parseInt(nilai);
        } catch (NumberFormatException e) {
            throw new Exception("Masukkan bilangan bulat yang valid.");
        }
    }

    private static double bacaDouble(Scanner scanner, String pesan) throws Exception {
        System.out.print(pesan);
        String nilai = scanner.nextLine().trim().replace(',', '.');
        try {
            double hasil = Double.parseDouble(nilai);
            if (!Double.isFinite(hasil)) {
                throw new NumberFormatException();
            }
            return hasil;
        } catch (NumberFormatException e) {
            throw new Exception("Masukkan nominal angka yang valid, misalnya 800000.");
        }
    }
}
