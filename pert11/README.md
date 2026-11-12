# Praktikum Sesi 11 — Collections, Generics, dan Unit Test Pertama

**Sub-CPMK-P7 — Mahasiswa mampu menerapkan exception handling dan menulis unit test dasar dengan JUnit 5 dan PHPUnit. (P3)**

**Keterkaitan teori:** Pertemuan 11 — Collections, Generics, dan Operasi Fungsional  
**Durasi:** 170 menit (1 SKS praktikum) · **Bobot:** 5% dari nilai praktikum  
**Bahasa:** Java 25 dan PHP 8.4+

> Versi cetak modul ini: [`Modul-Praktikum-11-collections-generics-dan-unit-test-pertama.docx`](Modul-Praktikum-11-collections-generics-dan-unit-test-pertama.docx). Kerangka kode: [`starter/`](starter/).

---

## A. Tujuan sesi

1. Mengganti struktur data manual dengan Collections Framework dan generics.
2. Membuat kelas generik sendiri dan membaca tanda tangan bertipe parameter.
3. Menulis unit test pertama dengan JUnit 5 dan PHPUnit.
4. Menjadikan daftar skenario kegagalan sesi 10 sebagai kasus uji yang benar-benar dijalankan.

## B. Alat dan bahan

- Proyek hasil sesi 9 dan 10.
- JUnit 5 (lewat Maven) dan PHPUnit (lewat Composer).

## C. Berkas starter

Salin ke folder tugas Anda; **jangan** menyunting berkas aslinya. Setiap komentar `TODO` harus Anda lengkapi sendiri — kode yang disalin dari sumber lain akan terlihat pada sesi demo.

| Berkas | Keterangan |
|---|---|
| `starter/java/Repository.java` | Kerangka kelas generik Repository<T> dengan TODO. |
| `starter/java/RepositoryTest.java` | Kerangka uji JUnit 5 dengan TODO pada assertion. |
| `starter/php/KoleksiBuku.php` | Kerangka typed collection dengan TODO. |
| `starter/php/KoleksiBukuTest.php` | Kerangka uji PHPUnit dengan TODO. |
| `starter/pom-tambahan.xml` | Potongan dependensi JUnit 5 dan plugin surefire yang harus disalin ke pom.xml Anda. |

## D. Langkah kerja

Kerjakan berurutan. Jangan melanjutkan ke langkah berikutnya sebelum checkpoint terpenuhi.

### Langkah 1 — Mengganti struktur data manual dengan Collections

- Buka LibraryService hasil sesi 10. Kemungkinan besar Anda masih memakai array biasa atau beberapa array paralel.
- Ganti dengan struktur yang tepat: List untuk daftar berurutan, Map untuk pencarian berdasarkan kunci, Set bila tidak boleh ada duplikat.
- Tuliskan di sesi-11/pilihan-koleksi.md: untuk setiap penggantian, mengapa Anda memilih struktur itu dan bukan yang lain.

> **Checkpoint —** Tidak ada lagi array paralel. Pencarian buku berdasarkan ISBN tidak lagi memakai perulangan linear.

### Langkah 2 — Membuat kelas generik Repository<T>

- Lengkapi TODO pada Repository.java: simpan(T), cariById(String) yang mengembalikan Optional<T>, cariSemua() yang mengembalikan List<T>, dan hapus(String).
- Perhatikan tanda tangannya. Setelah sesi ini, JpaRepository<Buku, Long> pada sesi 14 tidak akan terasa asing.
- Getter cariSemua() harus mengembalikan salinan tak-berubah — pelajaran dari sesi 7 tetap berlaku.

> **Checkpoint —** Repository<Buku> menolak penyimpanan objek selain Buku pada tahap KOMPILASI.

### Langkah 3 — Optional dan padanannya di PHP

- Ganti seluruh method pencarian yang mengembalikan null menjadi Optional<T> di Java.
- Di PHP, gunakan tipe nullable (?Buku) dengan operator null-safe (?->) dan null-coalescing (??).
- Cari di kode Anda: adakah baris yang masih berpotensi menimbulkan NullPointerException? Catat di pilihan-koleksi.md.

> **Checkpoint —** Tidak ada method pencarian yang mengembalikan null secara langsung.

### Langkah 4 — Operasi fungsional atas koleksi

- Tulis tiga laporan menggunakan operasi fungsional, tanpa perulangan manual:
  1. Daftar buku yang tersedia (filter).
  2. Daftar judul saja, digabung dengan pemisah (map + join).
  3. Jumlah buku per penulis (groupingBy di Java; pengelompokan manual atau Collection di PHP).
- PERHATIAN PHP: array_map(callable, array) tetapi array_filter(array, callable) — urutan argumennya berbeda. Ini sumber kesalahan yang sangat sering.

> **Checkpoint —** Ketiga laporan berjalan dan hasilnya sama antara versi Java dan PHP.

### Langkah 5 — Unit test pertama dengan JUnit 5

- Salin potongan dependensi dari pom-tambahan.xml ke pom.xml Anda.
- Buat src/test/java/ dengan struktur package yang sama seperti src/main/java.
- Lengkapi RepositoryTest.java. Minimal lima kasus uji: menyimpan lalu menemukan, mencari yang tidak ada, menghapus, koleksi kosong, dan penyimpanan dengan kunci yang sama (menimpa atau menolak — sesuai rancangan Anda).
- Tambahkan pula uji untuk exception: pastikan pinjam() melempar BukuTidakDitemukanException untuk ISBN yang tidak terdaftar.

**Terminal**

```bash
mvn test
```

> **Checkpoint —** mvn test menampilkan Tests run: minimal 8, Failures: 0, Errors: 0.

### Langkah 6 — Unit test dengan PHPUnit

- Tambahkan PHPUnit sebagai dependensi pengembangan, dan tambahkan pemetaan autoload-dev untuk namespace pengujian.
- Lengkapi KoleksiBukuTest.php dengan kasus uji yang setara.
- Terjemahkan minimal LIMA skenario dari skenario-uji.md sesi 10 menjadi kasus uji yang benar-benar dijalankan.

**Terminal**

```bash
composer require --dev phpunit/phpunit
./vendor/bin/phpunit tests
```

> **Checkpoint —** PHPUnit menampilkan OK dengan minimal 8 pengujian dan nol kegagalan.

### Langkah 7 — Membuat satu uji gagal dengan sengaja

- Ubah satu baris kode sumber sehingga salah satu uji gagal. Jalankan pengujian.
- Salin pesan kegagalannya ke sesi-11/pilihan-koleksi.md dan jelaskan bagaimana pesan itu membantu menemukan letak masalah.
- Kembalikan kodenya, pastikan seluruh uji hijau kembali.

> **Checkpoint —** pilihan-koleksi.md memuat satu pesan kegagalan uji beserta penjelasannya.

## E. Latihan mandiri di lab

_Dikerjakan bila langkah kerja selesai lebih awal. Tidak wajib, tetapi menambah nilai pada aspek penerapan konsep._

1. Ganti HashMap menjadi TreeMap pada salah satu struktur Anda. Jalankan laporan. Jelaskan perubahan urutan keluarannya.
2. Tulis method generik dengan bounded type: <N extends Number> double jumlahkan(List<N> angka). Coba panggil dengan List<String> dan catat pesan kompilatornya.
3. Di PHP, jalankan array_filter tanpa array_values lalu json_encode hasilnya. Amati bahwa keluarannya menjadi objek, bukan larik. Jelaskan mengapa.

## F. Tugas rumah

1. Perluas cakupan pengujian sampai seluruh 10 skenario dari sesi 10 terwakili.
2. Tulis sesi-11/catatan-uji.md: menurut Anda, kasus uji apa yang PALING sulit ditulis, dan mengapa?

## G. Luaran yang dikumpulkan

Seluruh luaran diserahkan melalui repositori Git pribadi Anda, dengan riwayat commit yang menunjukkan proses pengerjaan — bukan satu commit tunggal di akhir.

| Berkas / folder | Keterangan |
|---|---|
| `src/main/.../Repository.java` | Kelas generik dengan Optional. |
| `src/test/java/` | Kelas uji JUnit 5 yang seluruhnya lulus. |
| `tests/` | Kelas uji PHPUnit yang seluruhnya lulus. |
| `sesi-11/pilihan-koleksi.md` | Alasan pemilihan struktur koleksi, catatan null-safety, dan satu pesan kegagalan uji. |
| `sesi-11/catatan-uji.md` | Refleksi tentang kasus uji tersulit. |

## H. Rubrik penilaian sesi ini

| Aspek | Bobot | Kriteria |
|---|---|---|
| Kebenaran fungsional | 35% | Seluruh perintah pada langkah kerja berjalan dan menghasilkan keluaran yang diminta. |
| Penerapan konsep sesi ini | 30% | Konsep yang menjadi Sub-CPMK sesi ini diterapkan dengan tepat, bukan sekadar membuat program berjalan. |
| Keterbacaan dan konvensi | 10% | Penamaan bermakna, format konsisten, komentar seperlunya, riwayat commit wajar. |
| Demo dan pertanyaan lisan | 25% | Mampu menjelaskan setiap baris kode sendiri dan menjawab pertanyaan demo. MENGGUGURKAN: tanpa demo, tugas tidak dinilai. |

## I. Pertanyaan demo

> Pertanyaan berikut akan diajukan saat Anda mendemokan pekerjaan sesi ini. Daftar ini sengaja dibuka agar Anda mempersiapkan **pemahaman**, bukan hafalan. Anda boleh memakai alat bantu apa pun saat mengerjakan — tetapi kode yang tidak dapat Anda jelaskan sendiri tidak dinilai.

1. Mengapa Anda memilih Map dan bukan List untuk menyimpan buku? Apa yang berubah dari sisi kecepatan pencarian?
2. Bacakan tanda tangan Repository<T> Anda. Apa arti T di situ? Apa yang terjadi kalau saya mencoba menyimpan objek Anggota ke Repository<Buku>?
3. Mengapa cariById mengembalikan Optional dan bukan null? Tunjukkan satu tempat di kode Anda yang menjadi lebih aman karenanya.
4. Jalankan mvn test sekarang. Berapa uji yang berjalan? Tunjuk satu uji dan jelaskan apa yang sedang dibuktikannya.
5. Saya ubah satu baris di LibraryService sekarang — uji mana yang akan gagal? Mari kita buktikan.
6. Tunjukkan satu skenario dari daftar sesi 10 yang sudah Anda ubah menjadi unit test. Bagaimana bentuknya berubah?
7. Pada kode PHP Anda, apa beda array_map dan array_filter dari sisi urutan argumen? Mengapa itu sering menimbulkan kesalahan?

## J. Kesalahan yang sering terjadi

| Gejala | Penyebab yang lazim | Cara memperbaiki |
|---|---|---|
| mvn test tidak menemukan satu pun uji | Berkas uji tidak berada di src/test/java, atau namanya tidak berakhiran Test. | Ikuti konvensi Maven: src/test/java dan nama kelas berakhiran Test. |
| PHPUnit: No tests executed | Namespace pengujian belum terdaftar di autoload-dev, atau folder tests salah. | Tambahkan autoload-dev pada composer.json lalu jalankan composer dump-autoload. |
| ConcurrentModificationException | Koleksi diubah saat sedang di-iterasi. | Gunakan Iterator.remove(), removeIf(), atau kumpulkan dulu ke koleksi baru. |
| json_encode menghasilkan objek, bukan larik | array_filter meninggalkan indeks berlubang. | Bungkus dengan array_values() setelah array_filter. |
| Uji lulus padahal kode salah | Assertion terlalu longgar, mis. hanya memeriksa bahwa hasilnya tidak null. | Periksa nilai yang sesungguhnya diharapkan. Uji yang tidak pernah bisa gagal tidak membuktikan apa pun. |

## Lembar verifikasi demo

Diisi oleh dosen atau asisten pada saat demo. Tugas tanpa lembar terverifikasi tidak dinilai.

|  |  |
|---|---|
| Nama / NIM |  |
| Tanggal demo |  |
| Nilai sesi ini |  |
| Catatan penguji |  |
| Paraf penguji |  |

---

_Modul ini disusun 10 September 2026. Versi teknologi yang dirujuk (Java 25 LTS, PHP 8.4/8.5, Laravel 13, Spring Boot 4.1) diverifikasi pada tanggal tersebut dan perlu diperiksa ulang sebelum semester berjalan._
