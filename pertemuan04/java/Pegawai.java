/**
 * Sesi 4 — kelas induk.
 * Menampung apa yang BENAR-BENAR SAMA di semua jenis pegawai.
 *
 * Catatan: `abstract` dan `interface` dibahas tuntas di pertemuan 6.
 * Untuk sekarang cukup pahami: kelas abstract tidak bisa di-new langsung,
 * dan method abstract wajib dilengkapi turunannya.
 */
public abstract class Pegawai {

    // protected: turunan boleh membaca, dunia luar tidak.
    protected final String nip;
    protected final String nama;
    protected final double gajiPokok;

    protected Pegawai(String nip, String nama, double gajiPokok) {
        // TODO 1: tolak gaji pokok negatif.

        this.nip = nip;
        this.nama = nama;
        this.gajiPokok = gajiPokok;
    }

    /**
     * TODO 2: perilaku dasar — kembalikan gaji pokok apa adanya.
     *         Turunan akan MENAMBAH, bukan mengganti seluruhnya.
     */
    public double hitungGaji() {
        return 0;   // ganti
    }

    /** Turunan wajib menyebutkan jenisnya sendiri. */
    public abstract String jenis();

    public String getNama() { return nama; }
    public String getNip()  { return nip; }

    @Override
    public String toString() {
        return String.format("%-14s %-9s %-20s Rp%,.2f", nip, jenis(), nama, hitungGaji());
    }
}
