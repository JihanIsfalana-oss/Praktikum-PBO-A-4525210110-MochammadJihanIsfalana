package id.ac.univpancasila.perpustakaan.exception;

/**
 * Sesi 10 — induk seluruh exception domain perpustakaan.
 *
 * Manfaat punya induk bersama: pemanggil bisa menangkap SELURUH kegagalan
 * domain sekaligus dengan satu blok catch, tanpa ikut menelan kesalahan
 * pemrograman yang lain.
 *
 * TODO 1: putuskan — turunkan dari RuntimeException (unchecked) atau dari
 *         Exception (checked)? Tuliskan alasannya di skenario-uji.md.
 *         Petunjuk: kalau kegagalannya menandakan permintaan yang tidak sah
 *         dan bukan keadaan luar yang wajar, unchecked biasanya lebih tepat.
 */
public abstract class PerpustakaanException extends RuntimeException {

    protected PerpustakaanException(String pesan) {
        super(pesan);
    }

    protected PerpustakaanException(String pesan, Throwable penyebab) {
        super(pesan, penyebab);
    }
}
