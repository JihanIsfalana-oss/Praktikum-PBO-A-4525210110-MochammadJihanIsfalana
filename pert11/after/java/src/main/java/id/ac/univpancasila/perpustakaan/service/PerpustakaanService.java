package id.ac.univpancasila.perpustakaan.service;


import java.util.Scanner;


import id.ac.univpancasila.perpustakaan.exception.AnggotaTidakDitemukanException;
import id.ac.univpancasila.perpustakaan.exception.BatasPinjamTerlampauiException;
import id.ac.univpancasila.perpustakaan.exception.BukuSedangDipinjamException;
import id.ac.univpancasila.perpustakaan.exception.BukuTidakDitemukanException;
import id.ac.univpancasila.perpustakaan.exception.PerpustakaanException;


public class PerpustakaanService {
    public static void tambahAnggota(LibraryService libService, Scanner scanner) {
        System.out.println("[ FORM ANGGOTA ]");
        System.out.println("Langkah 1 dari 1");
        System.out.print("1. Masukkan ID Anggota, lalu tekan Enter.");
        System.out.println();
        System.out.print("> ");
        String idAnggota = scanner.nextLine().trim();


        if (idAnggota.isEmpty()) {
            System.out.println("[!] Dibatalkan: ID Anggota tidak boleh kosong.");
            return;
        }


        libService.daftarkanAnggota(idAnggota);
        System.out.println("[OK] Anggota '" + idAnggota + "' berhasil terdaftar.");
    }


   public static void tambahBuku(LibraryService libService, Scanner scanner) {
        System.out.println("[ FORM BUKU ]");
        System.out.println("Lengkapi 3 data berikut secara berurutan.");
        System.out.print("1. ISBN Buku, lalu tekan Enter.");
        System.out.println();
        System.out.print("> ");
        String isbn = scanner.nextLine().trim();


        if (isbn.isEmpty()) {
            System.out.println("[!] Dibatalkan: ISBN tidak boleh kosong.");
            return;
        }


        System.out.print("2. Judul Buku, lalu tekan Enter.");
        System.out.println();
        System.out.print("> ");
        String judul = scanner.nextLine().trim();


        if (judul.isEmpty()) {
            System.out.println("[!] Dibatalkan: Judul buku tidak boleh kosong.");
            return;
        }

        System.out.print("3. Jumlah Eksemplar (angka), lalu tekan Enter.");
        System.out.println();
        System.out.print("> ");
        int eksemplar = 0;
        try {
            eksemplar = Integer.parseInt(scanner.nextLine().trim());
            if (eksemplar <= 0) {
                System.out.println("[!] Dibatalkan: eksemplar harus lebih dari 0.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("[!] Dibatalkan: eksemplar harus berupa angka.");
            return;
        }


        libService.tambahBuku(isbn, judul, eksemplar);
        System.out.println("[OK] Buku '" + judul + "' (" + eksemplar + " eksemplar) berhasil ditambahkan.");
    }


    public  static void pinjamBuku(LibraryService libService, Scanner scanner){
        while (true) {
            try {
                System.out.println("[ FORM PEMINJAMAN ]");
                System.out.println("Masukkan satu data setiap baris.");
                System.out.println("1. ID Anggota (ketik exit untuk kembali).");
                System.out.print("> ");
                String idAnggota = scanner.nextLine().trim();
               
                if (idAnggota.equalsIgnoreCase("exit")){
                    break;
                }

                if (idAnggota.isEmpty()){
                    System.out.println("[!] ID Anggota tidak boleh kosong.");
                    continue;
                }

                System.out.println("2. ISBN Buku.");
                System.out.print("> ");
                String isbn = scanner.nextLine().trim();
                if (isbn.isEmpty()) {
                    System.out.println("[!] ISBN Buku tidak boleh kosong.");
                    continue;
                }

                libService.pinjam(idAnggota, isbn);
                System.out.println("[OK] Peminjaman berhasil: " + idAnggota + " -> " + isbn);
            } catch (PerpustakaanException e) {
                System.out.println("[Error Domain] " + e.getMessage());


                if (e instanceof BukuTidakDitemukanException ex){
                    System.out.println("ISBN bermasalah: " + ex.getIsbn());
                }else if(e instanceof AnggotaTidakDitemukanException ex){
                    System.out.println("Id Anggota bermasalah: " + ex.getIdAnggota());
                } else if(e instanceof BukuSedangDipinjamException ex){
                    System.out.println("  -> Judul Buku: " + ex.getJudul() + " (ISBN: " + ex.getIsbn() + ")");
                }else if(e instanceof BatasPinjamTerlampauiException ex) {
                    System.out.println("  -> Batas Maksimum: " + ex.getBatas() + " buku");
                }
            }catch(Exception e){
                System.out.println("[Error Sistem] Terjadi kesalahan input " + e.getMessage());
            }
        }
    }
}
