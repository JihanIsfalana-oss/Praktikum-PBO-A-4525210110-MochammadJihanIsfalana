package id.ac.univpancasila.perpustakaan;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Scanner;
import id.ac.univpancasila.perpustakaan.service.*;
import id.ac.univpancasila.perpustakaan.model.Buku;
import id.ac.univpancasila.perpustakaan.repository.Repository;
import id.ac.univpancasila.perpustakaan.denda.AturanDenda;
import id.ac.univpancasila.perpustakaan.denda.AturanDendaFactory;
import id.ac.univpancasila.perpustakaan.repository.InMemoryBookRepository;


public class Main {
    public static void main(String[] args) {
        demoCollections();
        AturanDenda aturanDenda = AturanDendaFactory.untuk("mahasiswa");
        LibraryService libService = new LibraryService(new InMemoryBookRepository(), aturanDenda);
        Scanner scanner = new Scanner(System.in);
        boolean berjalan = true;

        cetakJudul("SISTEM PERPUSTAKAAN - PERTEMUAN 12");

        while (berjalan) {
            System.out.println();
            System.out.println("[ MENU UTAMA ]");
            System.out.println("1. Tambah Buku");
            System.out.println("2. Tambah Anggota");
            System.out.println("3. Pinjam Buku");
            System.out.println("0. Keluar");
            System.out.println("--------------------------------");
            System.out.println("Masukkan pilihan Anda [0-3], lalu tekan Enter.");
            System.out.print("> ");

            String pilihan = scanner.nextLine().trim();

            switch (pilihan) {
                case "1":
                    cetakJudul("1. TAMBAH BUKU");
                    PerpustakaanService.tambahBuku(libService, scanner);
                    break;
                case "2":
                    cetakJudul("2. TAMBAH ANGGOTA");
                    PerpustakaanService.tambahAnggota(libService, scanner);
                    break;
                case "3":
                    cetakJudul("3. PEMINJAMAN BUKU");
                    PerpustakaanService.pinjamBuku(libService, scanner);
                    break;
                case "0":
                    System.out.println("\nTerima kasih. Semua proses selesai.");
                    berjalan = false;
                    break;
                default:
                    System.out.println("\n[!] Pilihan \"" + pilihan + "\" tidak dikenal.");
                    System.out.println("[i] Masukkan angka 0, 1, 2, atau 3.");
            }
        }
        
        cetakJudul("EKSPOR LAPORAN");
        eksporLaporan(libService);

        scanner.close();
    }

    private static void demoCollections() {
        Repository<Buku> repository = new Repository<>(Buku::getNomorInduk);
        repository.simpan(new Buku("111", "Laskar Pelangi", "Andrea Hirata", 1));
        repository.simpan(new Buku("222", "Bumi Manusia", "Pramoedya Ananta Toer", 5));

        cetakJudul("DEMO SOLID & REPOSITORY - PERTEMUAN 12");
        System.out.println("1) Buku tersedia : " + repository.filter(Buku::tersedia).stream()
                .map(Buku::getJudul).toList());
        System.out.println("2) Judul digabung : " + String.join(" | ",
                repository.map(Buku::getJudul)));
        System.out.println("3) Jumlah per penulis : " +
                repository.kelompokkan(Buku::getPenulis));
    }

    private static void cetakJudul(String judul) {
        String garis = "=".repeat(judul.length() + 8);
        System.out.println("\n" + garis);
        System.out.println("  " + judul);
        System.out.println(garis);
    }

    private static void eksporLaporan(LibraryService service) {
        System.out.println("\n--- Ekspor Laporan Buku ---");
        String namaFile = "laporan_buku.txt";

        try (PrintWriter writer = new PrintWriter(new FileWriter(namaFile))) {
            writer.println("==========================================================================");
            writer.println("                    LAPORAN DAFTAR BUKU PERPUSTAKAAN                      ");
            writer.println("==========================================================================");
            writer.printf("%-12s | %-35s | %-8s | %-15s%n", "ISBN", "JUDUL BUKU", "STOK", "STATUS");
            writer.println("--------------------------------------------------------------------------");

            Map<String, String> daftarJudul = service.getSemuaJudul();

            if (daftarJudul.isEmpty()) {
                writer.println("Belum ada data buku dalam sistem.");
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
            System.out.println("[ERROR FILE] Gagal menulis laporan ke berkas: " + e.getMessage());
        }
    }
}

