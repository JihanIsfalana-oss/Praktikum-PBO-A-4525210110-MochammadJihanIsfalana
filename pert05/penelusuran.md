# Penelusuran Sesi 5 — Polimorfisme

## Pertanyaan

Bagaimana mungkin metode `toString()` yang berada di kelas induk (`BangunDatar`) dapat memanggil metode `luas()` dan `keliling()` yang implementasinya hanya ada di kelas turunan?

## Jawaban

Hal ini terjadi karena Java menerapkan konsep **Polimorfisme.**Berikut adalah poin penting mengapa hal ini dapat berjalan dengan aman dan valid:

1. **Jaminan Kontrak (Abstract Keyword):**
   Dengan menandai `luas()` dan `keliling()` sebagai metode `abstract`, kelas induk `BangunDatar` menetapkan sebuah kontrak formal.

2. **Resolusi di Runtime (Dynamic Binding):**
   Ketika program berjalan dan metode `toString()` dieksekusi, JVM (Java Virtual Machine) tidak menjalankan metode abstrak milik kelas induk, melainkan mencari implementasi riil yang ada pada **objek konkret** (kelas turunan seperti `Persegi` atau `Segitiga`) yang saat itu sedang memanggil `toString()`.

3. **Kesimpulan:**
   Kelas induk hanya menyediakan cetak biru (_blueprint_) dan struktur pemanggilan format teksnya, sedangkan nilai aktualnya secara dinamis disuplai oleh perilaku (_behavior_) spesifik masing-masing kelas turunan.
