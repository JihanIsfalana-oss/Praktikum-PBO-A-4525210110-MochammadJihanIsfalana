package id.ac.univpancasila.perpustakaan.service;

import java.util.ArrayList;

import id.ac.univpancasila.perpustakaan.model.*;

public class PerpustakaanService {

    private ArrayList<Buku> buku = new ArrayList<>();


    public void tambahKoleksi(Buku buku) {
        this.buku.add(buku);
    }

    public Buku cariKoleksi(String nomorInduk) {

        for (Buku buku : this.buku) {
            if (buku.getNomorInduk().equals(nomorInduk)) {
                return buku; 
            }
        }
        return null;
    }

    public void pinjam(String nomorAnggota, String nomorInduk) {
        System.out.println("Anggota " + nomorAnggota + " meminjam buku " + nomorInduk);
    }

    public void kembalikan(String nomorAnggota, String nomorInduk) {
        System.out.println("Anggota: " + nomorAnggota + " mengembalikan buku " + nomorInduk);
    }
}
