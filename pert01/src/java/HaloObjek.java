/**
 * Sesi 1 — objek pertama Anda.
 *
 * Kerangka ini SUDAH BISA DIKOMPILASI apa adanya, tetapi keluarannya belum
 * benar sampai seluruh TODO dilengkapi.
 *
 * Kompilasi dan jalankan:
 *     javac -d out HaloObjek.java
 *     java -cp out HaloObjek
 */
public class HaloObjek {

    // TODO 1: kedua atribut di bawah ini belum punya access modifier,
    //         sehingga saat ini dapat diakses dari seluruh package.
    //         Tambahkan kata kunci `private` pada keduanya.
    //         Alasannya dibahas tuntas pada sesi 2 — untuk sekarang cukup
    //         pahami: data objek tidak seharusnya terbuka bagi siapa pun.
    String nama;
    String nim;

    public HaloObjek(String nama, String nim) {
        // TODO 2: tugaskan kedua parameter ke atribut objek.
        //         Perhatikan bahwa nama parameter SAMA dengan nama atribut —
        //         gunakan kata kunci `this` untuk membedakannya.
    }

    /**
     * TODO 3: kembalikan satu baris string berisi nama dan NIM Anda,
     *         misalnya: "Halo, saya Ani Lestari (2024001)".
     *
     *         Gunakan ATRIBUT objek — jangan menulis nama Anda langsung
     *         di dalam method ini. Kalau Anda menulisnya langsung, objeknya
     *         tidak berguna: semua objek akan menyapa dengan nama yang sama.
     */
    public String sapa() {
        return "(TODO 3 belum dikerjakan)";
    }

    public static void main(String[] args) {
        // Ganti dua nilai di bawah dengan nama dan NIM Anda sendiri.
        HaloObjek saya = new HaloObjek("Nama Anda", "NIM Anda");
        System.out.println(saya.sapa());

        // Bukti bahwa objek menyimpan datanya masing-masing:
        HaloObjek temanSekelas = new HaloObjek("Budi Santoso", "2024002");
        System.out.println(temanSekelas.sapa());

        System.out.println();
        System.out.println("Pertanyaan untuk direnungkan:");
        System.out.println("  Baris mana di atas yang MEMBUAT objek?");
        System.out.println("  Baris mana yang hanya MENDEKLARASIKAN kelas?");
        System.out.println("  Mengapa kedua objek di atas bisa menyapa dengan nama berbeda,");
        System.out.println("  padahal method sapa() hanya ditulis satu kali?");
    }
}
