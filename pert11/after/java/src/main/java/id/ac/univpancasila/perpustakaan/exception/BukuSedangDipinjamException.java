package id.ac.univpancasila.perpustakaan.exception;

/**
 * TODO: exception ini membawa DUA data — isbn dan judul.
 *       Judul disertakan agar pesan untuk pengguna lebih berarti;
 *       isbn disertakan agar sistem dapat mengolahnya.
 */
public class BukuSedangDipinjamException extends PerpustakaanException {
    private final String isbn;
    private  final String judul;

    public BukuSedangDipinjamException(String isbn, String judul) {
        super("Buku " + judul + " dengan ISBN  " + isbn + " sedang dipinjam");
        this.isbn = isbn;
        this.judul = judul;
    }

    public String getIsbn(){
        return this.isbn;
    }

    public String getJudul(){
        return this.judul;
    }
}

