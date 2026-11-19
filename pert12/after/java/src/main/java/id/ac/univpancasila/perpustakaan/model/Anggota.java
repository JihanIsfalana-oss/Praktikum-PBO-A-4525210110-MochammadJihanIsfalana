package id.ac.univpancasila.perpustakaan.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Anggota extends Jurusan{
    private String kodeAnggota;
    private LocalDateTime anggotaSejak;
    private Mahasiswa anggotaPerpus;

    public Anggota(String kodeJurusan, String namaJurusan, String kodeAnggota){
        super(kodeJurusan, namaJurusan);
        this.kodeAnggota = kodeAnggota;
    }

    public void daftarkan(Mahasiswa m, LocalDateTime masaKeanggotaan){
        if (m == null){
            throw new IllegalAccessError("Mahasiswa tidak boleh kosong");
        }

        this.anggotaPerpus = m;
        this.anggotaSejak = masaKeanggotaan;
    }

    public String checkMasaKeanggotaan(){
        LocalDateTime dateNow = LocalDateTime.now();
        String status;
        if (dateNow.isAfter(this.anggotaSejak.plusYears((3))) ){
           status = "Keanggotaan tidak valid (lebih dari 3 tahun)";
        } else {
            status = "Keanggotaan valid";
        }

        return status;
    }

    public void getKeanggotaan(){
        System.out.println("Nama Anggota: " + anggotaPerpus.getNama());
        System.out.println("Jurusan: " + super.getNama());
        System.out.println("Kode Anggota: " + this.kodeAnggota);
        System.out.println("Anggota Perpustakaan Sejak: "+ this.formatDateMasaAnggota(this.anggotaSejak));
    }

    private String formatDateMasaAnggota(LocalDateTime date){
        DateTimeFormatter formatMasaAnggota = DateTimeFormatter.ofPattern("dd-MM-yyy");
        String formatedDate = formatMasaAnggota.format(date);

        return formatedDate;
    }
}