package id.ac.univpancasila.perpustakaan.exception;

/**
 * TODO: exception ini membawa idAnggota dan batas maksimum.
 *       Menyertakan batas di dalam exception memudahkan pemanggil
 *       menyusun pesan tanpa harus mengetahui aturannya sendiri.
 */
public class BatasPinjamTerlampauiException extends PerpustakaanException {
    private  final String idAnggota;
    private final int batas;

    public BatasPinjamTerlampauiException(String idAnggota, int batas) {
        super("Anggota dengan ID " + idAnggota + " telah mencapai batas maksimum peminjaman");
        this.idAnggota = idAnggota;
        this.batas = batas;
    }

    public String getIdAnggota(){
        return this.idAnggota;
    }

    public int getBatas(){
        return this.batas;
    }
}

