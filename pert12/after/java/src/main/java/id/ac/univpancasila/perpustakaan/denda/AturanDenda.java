package id.ac.univpancasila.perpustakaan.denda;

/**
 * Sesi 12 — STRATEGY PATTERN.
 *
 * Rantai if untuk jenis anggota (mahasiswa, dosen, kehormatan) diganti
 * dengan satu interface dan beberapa implementasi.
 *
 * Akibatnya: menambah jenis anggota baru = menambah SATU kelas,
 * tanpa menyunting satu baris pun kode yang sudah ada.
 * Itulah Open-Closed Principle yang bekerja.
 */
public interface AturanDenda {

    /** Hitung denda untuk sejumlah hari keterlambatan. Hari negatif dianggap nol. */
    double hitung(int hariTerlambat);

    /** Nama aturan, untuk ditampilkan pada laporan. */
    String nama();
}

/*
 * TODO 1: buat tiga implementasi dalam berkas terpisah:
 *
 *   DendaMahasiswa   -> Rp1.000 per hari
 *   DendaDosen       -> Rp500 per hari
 *   DendaBebas       -> selalu 0   (anggota kehormatan)
 *
 * TODO 2: buat AturanDendaFactory dengan method statis:
 *
 *   public static AturanDenda untuk(String jenisAnggota)
 *
 *   Method ini memusatkan keputusan "objek jenis mana yang dibuat".
 *   Tolak jenis yang tidak dikenal dengan IllegalArgumentException.
 *
 * TODO 3 (Langkah 3): buktikan Open-Closed dengan menambahkan DendaAlumni
 *   TANPA menyunting satu baris pun pada LibraryService.
 *   Catat di REFACTORING.md: berapa berkas lama yang isinya berubah?
 */
