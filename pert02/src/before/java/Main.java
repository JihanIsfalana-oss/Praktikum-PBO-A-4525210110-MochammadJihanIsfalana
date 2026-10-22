/**
 * Program uji — JANGAN DIUBAH pada Langkah 1 sampai 4.
 * Kalau kode Anda benar, seluruh keluaran di bawah akan masuk akal.
 */
public class Main {
    public static void main(String[] args) {

        System.out.println("=== Rekap Nilai ===");
        Mahasiswa[] kelas = {
            new Mahasiswa("2024001", "Ani Lestari",  85, 78, 90),
            new Mahasiswa("2024002", "Budi Santoso", 60, 55, 62),
            new Mahasiswa("2024003", "Citra Wijaya", 92, 88, 95)
        };
        for (Mahasiswa m : kelas) {
            System.out.println("  " + m);
        }

        System.out.println();
        System.out.println("=== Objek menolak data yang melanggar aturan ===");

        try {
            new Mahasiswa("2024004", "Salah Nilai", 150, 80, 80);
            System.out.println("  MASALAH: nilai 150 seharusnya ditolak!");
        } catch (IllegalArgumentException e) {
            System.out.println("  Ditolak: " + e.getMessage());
        }

        try {
            new Mahasiswa("", "NIM Kosong", 80, 80, 80);
            System.out.println("  MASALAH: NIM kosong seharusnya ditolak!");
        } catch (IllegalArgumentException e) {
            System.out.println("  Ditolak: " + e.getMessage());
        }
    }
}
