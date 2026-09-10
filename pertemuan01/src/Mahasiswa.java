/**
 * Sesi 2 — enkapsulasi yang menjaga invariant.
 *
 * Deskripsi masalah:
 *   "Sistem akademik mencatat mahasiswa dengan NIM, nama, dan tiga komponen
 *    nilai: tugas, UTS, dan UAS. NIM tidak pernah berubah setelah mahasiswa
 *    terdaftar. Setiap komponen nilai berada dalam rentang 0 sampai 100.
 *    Nilai akhir dihitung 30% tugas, 30% UTS, 40% UAS."
 *
 * Tuliskan lebih dulu daftar invariant-nya di analisis.md,
 * baru lengkapi TODO di bawah ini.
 */
public class Mahasiswa {

    // Konstanta bobot — jangan menulis angka 0.30 dan 0.40 di dalam method.
    public static final double BOBOT_TUGAS = 0.30;
    public static final double BOBOT_UTS   = 0.30;
    public static final double BOBOT_UAS   = 0.40;

    private static final double NILAI_MIN = 0;
    private static final double NILAI_MAX = 100;

    // TODO 1: deklarasikan atribut. Perhatikan mana yang boleh berubah
    //         dan mana yang tidak. Gunakan final untuk yang tidak boleh berubah.
    private final String nim;
    private final String nama;
    private String hurufMutu;
    private double nilaiTugas;
    private double nilaiUts;
    private double nilaiUas;
    private double nilaiAkhir;

    public Mahasiswa(String nim, String nama, double nilaiTugas, double nilaiUts, double nilaiUas) {
        // TODO 2: tolak NIM yang kosong atau null.
        //         Lemparkan IllegalArgumentException dengan pesan yang menyebut
        //         APA yang salah — bukan sekadar "Error".
        if(nim == null) {
            throw new IllegalArgumentException("NIM Mahasiswa tidak boleh NULL, harus Mempunyai Nilai !");
        }
        // TODO 3: tolak setiap komponen nilai yang di luar rentang 0-100.
        //         Petunjuk: buat satu method privat pembantu agar tidak menulis
        //         pemeriksaan yang sama tiga kali.
        if(nilaiAkhir < 0) {
            pastikanNilaiSah("Nilai tidak boleh kurang dari 0 !", nilaiAkhir);
        } else if(nilaiAkhir > 100) {
            pastikanNilaiSah("Nilai tidak boleh lebih dari 0 !", nilaiAkhir);
        }

        this.nim = nim;
        this.nama = nama;
        this.nilaiTugas = nilaiTugas;
        this.nilaiUts = nilaiUts;
        this.nilaiUas = nilaiUas;
    }

    // TODO 4: buat method privat pembantu untuk memvalidasi satu komponen nilai.
    //         Tanda tangan yang disarankan:
    //         private static void pastikanNilaiSah(String namaKomponen, double nilai)
    private  static void pastikanNilaiSah(String namaKomponen, double nilai) {
        if(nilai < NILAI_MIN) {
            throw new IllegalArgumentException(namaKomponen);
        } else if(nilai > NILAI_MAX) {
            throw new IllegalArgumentException(namaKomponen);
        }
    }


    /**
     * TODO 5: hitung nilai akhir memakai konstanta bobot di atas.
     */
    private double nilaiAkhir() {
        nilaiTugas = nilaiTugas * BOBOT_TUGAS;
        nilaiUas = nilaiUas * BOBOT_UAS;
        nilaiUts = nilaiUts * BOBOT_UTS;
        nilaiAkhir = (nilaiTugas + nilaiUas + nilaiUts);
        return nilaiAkhir;   // ganti
    }

    /**
     * TODO 6: kembalikan huruf mutu berdasarkan nilai akhir.
     *   >= 80 -> "A"   >= 70 -> "B"   >= 60 -> "C"   >= 50 -> "D"   selain itu "E"
     */
    public String hurufMutu() {
        if(nilaiAkhir >= 80) {
            hurufMutu = "A";
        } else if(nilaiAkhir >= 70) {
            hurufMutu = "B";
        } else if(nilaiAkhir >= 60) {
            hurufMutu = "C";
        } else if(nilaiAkhir >= 50) {
            hurufMutu = "D";
        } else {
            hurufMutu = "E";
        }

        return hurufMutu;   // ganti
    }

    // ── Getter ────────────────────────────────────────────────
    // TODO 7: sediakan getter untuk nim, nama, dan nilaiAkhir.
    //         JANGAN membuat setNim(). Baca ulang invariant Anda kalau tergoda.

    public String getNim()  { return this.nim; }
    public String getNama() { return this.nama; }
    public double getNilaiAkhir() {return nilaiAkhir();}

    @Override
    public String toString() {
        return String.format("%-10s %-18s akhir=%6.2f  mutu=%s",
                nim, nama, nilaiAkhir(), hurufMutu());
    }
}
