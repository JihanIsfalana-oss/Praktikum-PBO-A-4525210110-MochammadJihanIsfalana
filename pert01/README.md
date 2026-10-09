# Praktikum Sesi 1 — Penyiapan Lingkungan Pengembangan

**Sub-CPMK-P1 — Mahasiswa mampu menyiapkan dan memverifikasi lingkungan pengembangan Java 25 dan PHP 8.4+ beserta build tool, version control, dan IDE. (P2)**

**Keterkaitan teori:** Pertemuan 1 — Paradigma OOP dan Lingkungan Pengembangan  
**Durasi:** 170 menit (1 SKS praktikum) · **Bobot:** 3% dari nilai praktikum  
**Bahasa:** Java 25 dan PHP 8.4+

> Versi cetak modul ini: [`Modul-Praktikum-01-penyiapan-lingkungan-pengembangan.docx`](Modul-Praktikum-01-penyiapan-lingkungan-pengembangan.docx). Kerangka kode: [`starter/`](starter/).

---

## A. Tujuan sesi

1. Memasang dan memverifikasi JDK 25 (LTS), Apache Maven, PHP 8.4 atau lebih baru, dan Composer.
2. Mengonfigurasi JAVA_HOME dan PATH sehingga perintah dapat dipanggil dari direktori mana pun.
3. Membuat repositori Git pribadi dengan .gitignore yang benar untuk proyek Java dan PHP.
4. Menjalankan program berorientasi objek pertama di kedua bahasa.

## B. Alat dan bahan

- Komputer pribadi (RAM minimum 8 GB) atau komputer laboratorium.
- Akses internet untuk mengunduh JDK, Composer, dan dependensi.
- Akun GitHub atau GitLab pribadi.
- Editor: IntelliJ IDEA Community, VS Code, atau editor lain pilihan Anda.

## C. Berkas starter

Salin ke folder tugas Anda; **jangan** menyunting berkas aslinya. Setiap komentar `TODO` harus Anda lengkapi sendiri — kode yang disalin dari sumber lain akan terlihat pada sesi demo.

| Berkas | Keterangan |
|---|---|
| `starter/.gitignore` | Berkas .gitignore siap pakai untuk proyek Java dan PHP. |
| `starter/java/HaloObjek.java` | Kerangka kelas dengan TODO yang harus dilengkapi. |
| `starter/php/halo_objek.php` | Kerangka kelas PHP dengan TODO yang harus dilengkapi. |

## D. Langkah kerja

Kerjakan berurutan. Jangan melanjutkan ke langkah berikutnya sebelum checkpoint terpenuhi.

### Langkah 1 — Memasang JDK 25 dan memverifikasinya

- Unduh JDK 25 (LTS) dari salah satu distribusi: Eclipse Temurin, Amazon Corretto, atau Oracle JDK. Pilih Java 25, BUKAN Java 26 — Java 25 adalah rilis LTS dengan dukungan panjang, sedangkan rilis non-LTS hanya didukung enam bulan.
- Setelah pemasangan selesai, buka terminal baru dan jalankan perintah verifikasi.

**Terminal**

```bash
java -version
javac -version
```

> **Checkpoint —** Keluaran menyebut versi 25.x.x. Jika perintah tidak dikenali, JAVA_HOME dan PATH belum benar — lihat bagian J.

### Langkah 2 — Memasang Apache Maven

- Unduh Maven 3.9 atau lebih baru, ekstrak, lalu tambahkan direktori bin-nya ke PATH.
- Maven adalah build tool yang akan dipakai mulai sesi 9 dan menjadi dasar proyek Spring Boot pada sesi 14. Memasangnya sekarang menghemat waktu nanti.

**Terminal**

```bash
mvn -v
```

> **Checkpoint —** Keluaran menyebut versi Maven dan versi Java yang dipakainya. Pastikan Java yang disebut adalah versi 25.

### Langkah 3 — Memasang PHP 8.4+ dan Composer

- Di macOS: Laravel Herd menyediakan PHP dan Composer sekaligus, cara tercepat.
- Di Windows: gunakan Laragon atau XAMPP versi terbaru; pastikan versi PHP-nya 8.4 ke atas.
- Di Linux: gunakan paket resmi distribusi, atau repositori ondrej/php untuk versi terbaru.

**Terminal**

```bash
php -v
composer -V
```

> **Checkpoint —** PHP menyebut versi 8.4.x atau 8.5.x. Composer menyebut versi 2.x.

### Langkah 4 — Membuat repositori Git untuk seluruh tugas semester

- Buat satu repositori PRIBADI bernama pbo-<NIM Anda>. Repositori ini akan menampung seluruh tugas praktikum selama satu semester.
- Salin berkas .gitignore dari folder starter ke akar repositori Anda. Bacalah isinya — Anda akan diminta menjelaskannya saat demo.
- Buat commit pertama.

**Terminal**

```bash
mkdir pbo-2024001 && cd pbo-2024001
git init
cp <lokasi-starter>/.gitignore .
git add .gitignore
git commit -m "Inisialisasi repositori praktikum PBO"
git remote add origin <URL repositori Anda>
git push -u origin main
```

> **Checkpoint —** Repositori sudah ada di GitHub/GitLab dan berisi tepat satu berkas: .gitignore.

### Langkah 5 — Melengkapi dan menjalankan program Java pertama

- Buat folder sesi-01/java di dalam repositori Anda, lalu salin HaloObjek.java dari starter.
- Buka berkasnya. Ada tiga komentar TODO yang harus Anda lengkapi. KETIK SENDIRI — jangan menyalin dari sumber lain. Anda akan diminta menjelaskan setiap baris saat demo.
- Kompilasi dan jalankan.

**Terminal**

```bash
cd sesi-01/java
javac -d out HaloObjek.java
java -cp out HaloObjek
```

> **Checkpoint —** Program mencetak nama dan NIM Anda melalui sebuah OBJEK, bukan langsung dari method main.

### Langkah 6 — Melengkapi dan menjalankan program PHP pertama

- Buat folder sesi-01/php, salin halo_objek.php dari starter, lengkapi TODO-nya.
- Perhatikan bahwa strukturnya sama persis dengan versi Java — hanya sintaksnya yang berbeda. Inilah pola yang akan berulang sepanjang semester.

**Terminal**

```bash
cd ../php
php halo_objek.php
```

> **Checkpoint —** Keluaran PHP identik dengan keluaran Java.

### Langkah 7 — Memeriksa bahwa artefak build tidak ikut ter-commit

- Jalankan git status. Folder out/ dan berkas *.class TIDAK boleh muncul dalam daftar. Jika muncul, .gitignore Anda belum benar.

**Terminal**

```bash
git status
git add .
git commit -m "Sesi 1: Halo Objek Java dan PHP"
git push
```

> **Checkpoint —** git status bersih, dan di repositori daring tidak ada satu pun berkas .class.

## E. Latihan mandiri di lab

_Dikerjakan bila langkah kerja selesai lebih awal. Tidak wajib, tetapi menambah nilai pada aspek penerapan konsep._

1. Ubah HaloObjek agar menerima nama dan NIM lewat constructor, bukan lewat setter. Jelaskan mengapa cara ini lebih aman.
2. Tambahkan method ringkas() yang mengembalikan satu baris string berisi nama dan NIM. Panggil dari main.

## F. Tugas rumah

1. Tulis 200–300 kata: satu contoh nyata dari pengalaman Anda sendiri (tugas kuliah, proyek pribadi, apa pun) di mana kode menjadi sulit diubah karena datanya tercerai-berai. Jelaskan bagaimana objek akan memperbaikinya. Simpan sebagai sesi-01/refleksi.md.
2. Pastikan seluruh luaran sudah ter-push sebelum sesi 2 dimulai.

## G. Luaran yang dikumpulkan

Seluruh luaran diserahkan melalui repositori Git pribadi Anda, dengan riwayat commit yang menunjukkan proses pengerjaan — bukan satu commit tunggal di akhir.

| Berkas / folder | Keterangan |
|---|---|
| `sesi-01/verifikasi.md` | Tangkapan layar hasil java -version, mvn -v, php -v, composer -V, git --version. |
| `sesi-01/java/HaloObjek.java` | Program Java yang berjalan. |
| `sesi-01/php/halo_objek.php` | Program PHP yang berjalan. |
| `.gitignore` | Di akar repositori, berisi aturan untuk Java, PHP, dan IDE. |
| `sesi-01/refleksi.md` | Tugas rumah 200–300 kata. |

## H. Rubrik penilaian sesi ini

| Aspek | Bobot | Kriteria |
|---|---|---|
| Kebenaran fungsional | 35% | Seluruh perintah pada langkah kerja berjalan dan menghasilkan keluaran yang diminta. |
| Penerapan konsep sesi ini | 30% | Konsep yang menjadi Sub-CPMK sesi ini diterapkan dengan tepat, bukan sekadar membuat program berjalan. |
| Keterbacaan dan konvensi | 10% | Penamaan bermakna, format konsisten, komentar seperlunya, riwayat commit wajar. |
| Demo dan pertanyaan lisan | 25% | Mampu menjelaskan setiap baris kode sendiri dan menjawab pertanyaan demo. MENGGUGURKAN: tanpa demo, tugas tidak dinilai. |

## I. Pertanyaan demo

> Pertanyaan berikut akan diajukan saat Anda mendemokan pekerjaan sesi ini. Daftar ini sengaja dibuka agar Anda mempersiapkan **pemahaman**, bukan hafalan. Anda boleh memakai alat bantu apa pun saat mengerjakan — tetapi kode yang tidak dapat Anda jelaskan sendiri tidak dinilai.

1. Apa beda JDK, JRE, dan JVM? Yang mana yang Anda pasang, dan mengapa?
2. Mengapa kita memilih Java 25 dan bukan Java 26 yang lebih baru?
3. Tunjukkan baris mana di .gitignore Anda yang mencegah berkas .class ter-commit. Mengapa berkas itu tidak boleh masuk repositori?
4. Pada program Java Anda, tunjuk baris yang membuat objek. Apa bedanya dengan baris yang mendeklarasikan kelas?
5. Apa yang terjadi jika Anda menghapus kata kunci "new" pada baris pembuatan objek? Coba sekarang.
6. Di versi PHP, apa padanan dari kata kunci "this" pada Java? Tunjukkan barisnya.
7. Mengapa Maven perlu dipasang sekarang, padahal baru dipakai pada sesi 9?

## J. Kesalahan yang sering terjadi

| Gejala | Penyebab yang lazim | Cara memperbaiki |
|---|---|---|
| "java" tidak dikenali sebagai perintah | Direktori bin JDK belum masuk PATH. | Tambahkan <folder JDK>/bin ke PATH, lalu BUKA TERMINAL BARU. Terminal lama tidak membaca PATH yang baru. |
| java -version menyebut versi lain (mis. 11 atau 17) | Ada JDK lain yang terpasang lebih dulu dan urutannya lebih awal di PATH. | Setel JAVA_HOME ke JDK 25 dan letakkan %JAVA_HOME%\bin (Windows) atau $JAVA_HOME/bin (macOS/Linux) di posisi PALING AWAL pada PATH. |
| mvn -v menyebut Java 17 padahal java -version menyebut 25 | Maven membaca JAVA_HOME, bukan PATH. | Perbaiki variabel JAVA_HOME, lalu buka terminal baru. |
| php -v menyebut versi 8.1 atau lebih lama | PHP bawaan sistem yang terbaca, bukan yang baru dipasang. | Periksa urutan PATH; di macOS, PHP bawaan sistem berada di /usr/bin/php dan sering menang. Pastikan PHP dari Herd/Homebrew lebih awal. |
| Berkas .class ikut muncul di git status | .gitignore belum disalin, atau berkas sudah terlanjur di-commit sebelumnya. | Salin .gitignore, lalu jalankan: git rm -r --cached . && git add . && git commit -m "Terapkan gitignore". |
| error: class HaloObjek is public, should be declared in a file named HaloObjek.java | Nama berkas tidak sama persis dengan nama kelas publik. | Ganti nama berkas agar sama persis, termasuk huruf besar-kecilnya. |

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
