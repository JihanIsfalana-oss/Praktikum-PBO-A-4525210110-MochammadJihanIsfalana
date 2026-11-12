package id.ac.univpancasila.perpustakaan.exception;

/**
 * TODO: lengkapi mengikuti pola BukuTidakDitemukanException.
 *       Exception ini membawa idAnggota.
 */
public class AnggotaTidakDitemukanException extends PerpustakaanException {
    private final String idAnggota;
    public AnggotaTidakDitemukanException(String idAnggota) {
        super("Anggota dengan ID " + idAnggota + " tidak ditemukan");
        this.idAnggota = idAnggota;
    }

    public String getIdAnggota(){
        return this.idAnggota;
    }
}

