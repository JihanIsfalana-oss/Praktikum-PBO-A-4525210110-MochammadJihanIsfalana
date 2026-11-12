package id.ac.univpancasila.perpustakaan.exception;

/**
 * EXCEPTION YANG MEMBAWA DATA — bukan sekadar teks.
 *
 * Perhatikan atribut isbn di bawah. Itulah yang membedakan exception ini
 * dari exception biasa: pemanggil bisa MENGAMBIL data penyebabnya kembali,
 * bukan harus mengurai kalimat pesan.
 *
 * Pada sesi 13 dan 14, data inilah yang menjadi isi badan respons HTTP 404.
 */
public class BukuTidakDitemukanException extends PerpustakaanException {

    // TODO 1: deklarasikan atribut privat final untuk isbn.
    private final String isbn;

    public BukuTidakDitemukanException(String isbn) {
        // TODO 2: panggil super(...) dengan pesan yang menyebut ISBN-nya,
        //         misalnya: "Buku dengan ISBN 978-1 tidak ditemukan"
        super("Buku dengan ISBN " + isbn + " tidak ditemukan");

        // TODO 3: simpan isbn ke atribut.
        this.isbn = isbn;
    }

    // TODO 4: sediakan getter getIsbn().
    public String getIsbn(){
        return this.isbn;
    }
}

