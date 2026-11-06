package id.ac.univpancasila.perpustakaan;

import id.ac.univpancasila.perpustakaan.model.*;
import  id.ac.univpancasila.perpustakaan.service.*;
import net.datafaker.Faker;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        LibraryService libService = new LibraryService();
        Scanner inputUser = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n=== SISTEM PERPUSTAKAAN ===");
            System.out.println("1. Tambah Buku");
            System.out.println("2. Tambah Anggota");
            System.out.println("3. Pinjam Buku");
            System.out.println("0. Keluar");
            System.out.print("Pilih menu (0-3): ");

            String pilihan = inputUser.nextLine();

            switch (pilihan) {
                case "1":
                    PerpustakaanService.tambahBuku(libService, inputUser);
                    break;
                case "2":
                    PerpustakaanService.tambahAnggota(libService, inputUser);
                    break;
                case "3":
                    PerpustakaanService.pinjamBuku(libService, inputUser);
                    break;
                case "0":
                    System.out.println("Terima kasih telah menggunakan sistem perpustakaan.");
                    running = false;
                    break;
                default:
                    System.out.println("[WARN] Pilihan menu tidak valid! Silakan masukkan angka 0 - 3.");
            }
        }
        
        System.out.println("\nMengekspor daftar transaksi ke berkas...");
        eksporLaporan(libService);

        inputUser.close();
    }

    private static void eksporLaporan(LibraryService service) {
        System.out.println("\n--- Ekspor Laporan Buku ---");
        String namaFile = "laporan_buku.txt";

        try (PrintWriter writer = new PrintWriter(new FileWriter(namaFile))) {
            writer.println("==========================================================================");
            writer.println("                        DAFTAR BUKU PERPUSTAKAAN                          ");
            writer.println("==========================================================================");
            writer.printf("%-12s | %-35s | %-8s | %-15s%n", "ISBN", "JUDUL BUKU", "STOK", "STATUS");
            writer.println("--------------------------------------------------------------------------");

            Map<String, String> daftarJudul = service.getSemuaJudul();

            if (daftarJudul.isEmpty()) {
                writer.println("Data Buku dalam buku Kosong !.\n");
            } else {
                for (Map.Entry<String, String> entry : daftarJudul.entrySet()) {
                    String isbn = entry.getKey();
                    String judul = entry.getValue();
                    int stokSisa = service.sisaEksemplar(isbn);

                    String status = (stokSisa > 0) ? "Tersedia" : "Tidak Tersedia";

                    writer.printf("%-12s | %-35s | %-8d | %-15s%n", isbn, judul, stokSisa, status);
                }
            }

            writer.println("==========================================================================");
            System.out.println(">> BERHASIL: Laporan buku beserta statusnya disimpan ke berkas '" + namaFile + "'.");

        } catch (IOException e) {
            System.out.println("[ERROR] Gagal menulis laporan ke berkas: " + e.getMessage());
        }
    }
}

