# Praktikum Sesi 9 — Package, Namespace, dan Build Tool

**Sub-CPMK-P6 — Mahasiswa mampu menstrukturkan proyek dengan package/namespace serta mengelola dependensi menggunakan Maven dan Composer. (P3)**

**Keterkaitan teori:** Pertemuan 9 — Package, Namespace, dan Build Tool  
**Durasi:** 170 menit (1 SKS praktikum) · **Bobot:** 5% dari nilai praktikum  
**Bahasa:** Java 25 dan PHP 8.4+

> Versi cetak modul ini: [`Modul-Praktikum-09-package-namespace-dan-build-tool.docx`](Modul-Praktikum-09-package-namespace-dan-build-tool.docx). Kerangka kode: [`starter/`](starter/).

---

## A. Tujuan sesi

1. Merestrukturisasi proyek datar menjadi proyek Maven dan proyek Composer yang benar.
2. Menerapkan penamaan package domain terbalik dan autoload PSR-4.
3. Menambahkan dan memakai satu dependensi eksternal pada masing-masing proyek.
4. Memastikan tidak ada satu pun artefak build yang ter-commit.

## B. Alat dan bahan

- Maven 3.9+ dan Composer 2.x (hasil sesi 1).
- Kode Sistem Perpustakaan dari sesi 7 — inilah yang akan direstrukturisasi.
- Akses internet untuk mengunduh dependensi. Jika lab tidak punya akses, gunakan cermin lokal yang disediakan asisten.

## C. Berkas starter

Salin ke folder tugas Anda; **jangan** menyunting berkas aslinya. Setiap komentar `TODO` harus Anda lengkapi sendiri — kode yang disalin dari sumber lain akan terlihat pada sesi demo.

| Berkas | Keterangan |
|---|---|
| `starter/java/pom.xml` | pom.xml dengan TODO pada groupId, artifactId, dan dependensi. |
| `starter/java/struktur.txt` | Peta struktur folder Maven yang harus Anda bentuk. |
| `starter/php/composer.json` | composer.json dengan TODO pada autoload PSR-4. |
| `starter/.gitignore` | Versi lengkap termasuk target/ dan vendor/. |

## D. Langkah kerja

Kerjakan berurutan. Jangan melanjutkan ke langkah berikutnya sebelum checkpoint terpenuhi.

### Langkah 1 — Membentuk struktur folder Maven

- Buat folder sesi-09/java/ dan di dalamnya bentuk struktur baku Maven. Hafalkan struktur ini — Spring Boot pada sesi 14 memakai yang sama persis.
- Pindahkan kode Perpustakaan dari sesi 7 ke dalam src/main/java/, dengan package id.ac.univpancasila.perpustakaan dan sub-package model, service, exception.
- Nama package HARUS sama persis dengan jalur foldernya. Ini bukan konvensi, melainkan aturan bahasa.

**Struktur folder**

```
sesi-09/java/
├── pom.xml
└── src/main/java/id/ac/univpancasila/perpustakaan/
    ├── App.java
    ├── model/     (Buku.java, Anggota.java, Peminjaman.java)
    └── service/   (LibraryService.java)
```

> **Checkpoint —** Setiap berkas .java diawali baris package yang cocok dengan lokasinya.

### Langkah 2 — Melengkapi pom.xml dan membangun proyek

- Lengkapi TODO pada pom.xml: groupId, artifactId, versi, dan maven.compiler.release.
- Bangun proyek dan jalankan.

**Terminal**

```bash
cd sesi-09/java
mvn clean compile
mvn exec:java -Dexec.mainClass=id.ac.univpancasila.perpustakaan.App
```

> **Checkpoint —** BUILD SUCCESS, dan program berjalan dari folder target/classes.

### Langkah 3 — Menambahkan dependensi eksternal (Java)

- Tambahkan satu dependensi yang benar-benar Anda pakai. Saran: sebuah pustaka kecil untuk format tabel di terminal, atau pustaka tanggal.
- Setelah menambah dependensi, jalankan mvn dependency:tree dan simpan keluarannya ke sesi-09/dependency-tree.txt.
- Pakai minimal satu fungsi dari pustaka itu di dalam App.java. Dependensi yang ditambahkan tetapi tidak dipakai tidak dinilai.

**Terminal**

```bash
mvn dependency:tree > ../dependency-tree.txt
mvn clean compile
```

> **Checkpoint —** dependency-tree.txt menampilkan pustaka yang Anda tambahkan, dan App.java benar-benar memanggilnya.

### Langkah 4 — Membentuk proyek Composer dengan autoload PSR-4

- Buat sesi-09/php/ dengan struktur src/Model/, src/Service/, src/Exception/.
- Lengkapi TODO pada composer.json: pemetaan namespace Perpustakaan\ ke src/.
- Hapus SELURUH require_once dari kode Anda. Setelah PSR-4 aktif, satu baris require vendor/autoload.php di titik masuk sudah cukup.

**Terminal**

```bash
cd ../php
composer install
php main.php
```

> **Checkpoint —** Program berjalan tanpa satu pun require_once selain vendor/autoload.php.

### Langkah 5 — Menambahkan dependensi eksternal (PHP)

- Tambahkan satu dependensi dan pakai fungsinya. Saran: nesbot/carbon untuk pengelolaan tanggal peminjaman dan jatuh tempo.
- Perhatikan berkas composer.lock yang terbentuk. Bacalah beberapa barisnya.

**Terminal**

```bash
composer require nesbot/carbon
php main.php
```

> **Checkpoint —** Tanggal jatuh tempo dihitung memakai pustaka eksternal, bukan aritmetika manual.

### Langkah 6 — Percobaan: merusak pemetaan namespace

- Buat sesi-09/percobaan.md.
- Ubah namespace satu kelas dari Perpustakaan\Model menjadi Perpustakaan\Domain TANPA memindahkan berkasnya. Jalankan. Catat pesan kesalahannya.
- Lalu perbaiki dengan memindahkan berkas ke folder yang sesuai. Catat langkah perbaikannya.
- Lakukan hal serupa pada Java: ubah baris package satu kelas tanpa memindahkan berkas. Catat pesan kompilatornya.

> **Checkpoint —** percobaan.md memuat dua pesan kesalahan asli beserta langkah perbaikannya.

### Langkah 7 — Memastikan repositori bersih

- Perbarui .gitignore dengan versi lengkap dari starter.
- Jalankan git status. Folder target/ dan vendor/ TIDAK boleh muncul.
- Jika sebelumnya sudah terlanjur ter-commit, bersihkan dengan git rm -r --cached.
- composer.lock WAJIB di-commit. Jelaskan alasannya di percobaan.md.

**Terminal**

```bash
git status
git add .
git commit -m "Sesi 9: restrukturisasi Maven dan Composer"
git push
```

> **Checkpoint —** Repositori daring tidak memuat target/ maupun vendor/, tetapi memuat composer.lock.

## E. Latihan mandiri di lab

_Dikerjakan bila langkah kerja selesai lebih awal. Tidak wajib, tetapi menambah nilai pada aspek penerapan konsep._

1. Ubah versi dependensi PHP Anda dari ^2.0 menjadi ~2.0. Jalankan composer update dan amati perbedaannya di composer.lock.
2. Buat sub-package util berisi satu kelas utilitas format rupiah, lalu pakai dari App. Perhatikan baris import/use yang muncul.

## F. Tugas rumah

1. Restrukturisasi SELURUH kode Anda dari sesi 2 sampai 7 ke dalam kedua proyek ini. Setelah sesi 9, hanya struktur inilah yang dipakai sampai akhir semester.
2. Tulis sesi-09/README.md berisi cara membangun dan menjalankan kedua proyek, seolah-olah ditujukan untuk orang yang belum pernah melihat proyek Anda.

## G. Luaran yang dikumpulkan

Seluruh luaran diserahkan melalui repositori Git pribadi Anda, dengan riwayat commit yang menunjukkan proses pengerjaan — bukan satu commit tunggal di akhir.

| Berkas / folder | Keterangan |
|---|---|
| `sesi-09/java/` | Proyek Maven lengkap dengan pom.xml dan struktur src/main/java. |
| `sesi-09/php/` | Proyek Composer dengan composer.json, composer.lock, dan autoload PSR-4. |
| `sesi-09/dependency-tree.txt` | Keluaran mvn dependency:tree. |
| `sesi-09/percobaan.md` | Dua percobaan merusak pemetaan namespace/package beserta perbaikannya. |
| `sesi-09/README.md` | Petunjuk membangun dan menjalankan. |

## H. Rubrik penilaian sesi ini

| Aspek | Bobot | Kriteria |
|---|---|---|
| Kebenaran fungsional | 35% | Seluruh perintah pada langkah kerja berjalan dan menghasilkan keluaran yang diminta. |
| Penerapan konsep sesi ini | 30% | Konsep yang menjadi Sub-CPMK sesi ini diterapkan dengan tepat, bukan sekadar membuat program berjalan. |
| Keterbacaan dan konvensi | 10% | Penamaan bermakna, format konsisten, komentar seperlunya, riwayat commit wajar. |
| Demo dan pertanyaan lisan | 25% | Mampu menjelaskan setiap baris kode sendiri dan menjawab pertanyaan demo. MENGGUGURKAN: tanpa demo, tugas tidak dinilai. |

## I. Pertanyaan demo

> Pertanyaan berikut akan diajukan saat Anda mendemokan pekerjaan sesi ini. Daftar ini sengaja dibuka agar Anda mempersiapkan **pemahaman**, bukan hafalan. Anda boleh memakai alat bantu apa pun saat mengerjakan — tetapi kode yang tidak dapat Anda jelaskan sendiri tidak dinilai.

1. Tunjukkan satu berkas Java Anda. Bagaimana saya tahu di folder mana berkas ini seharusnya berada, hanya dengan membaca baris pertamanya?
2. Apa yang dilakukan Composer ketika saya menulis new Perpustakaan\Model\Buku()? Jelaskan langkah demi langkah.
3. Mengapa vendor/ dan target/ tidak boleh di-commit, tetapi composer.lock harus?
4. Tunjukkan dependensi eksternal yang Anda tambahkan, lalu tunjukkan baris kode yang memakainya.
5. Apa arti penanda versi ^2.0 pada composer.json Anda? Versi 3.0 akan ikut terpasang atau tidak?
6. Saya hapus folder vendor/ Anda sekarang. Apa yang harus Anda lakukan agar program berjalan lagi?
7. Mengapa struktur src/main/java ini penting untuk dipahami sekarang, padahal kita baru memakai Spring Boot di sesi 14?

## J. Kesalahan yang sering terjadi

| Gejala | Penyebab yang lazim | Cara memperbaiki |
|---|---|---|
| ClassNotFoundException saat menjalankan Java | Nama package tidak cocok dengan struktur folder, atau mainClass salah ditulis. | Samakan package dengan folder. Tulis mainClass dengan nama lengkap termasuk package-nya. |
| PHP: Class "Perpustakaan\Model\Buku" not found | Pemetaan PSR-4 salah, atau autoload belum dibangun ulang. | Periksa composer.json, lalu jalankan composer dump-autoload. Perhatikan huruf besar-kecil nama folder — di Linux itu berpengaruh. |
| mvn: BUILD FAILURE — source option 25 is no longer supported | JAVA_HOME menunjuk ke JDK lama. | Perbaiki JAVA_HOME, buka terminal baru, jalankan mvn -v untuk memastikan. |
| composer install gagal karena tidak ada internet | Lab tanpa akses keluar. | Gunakan cermin lokal yang disediakan asisten, atau jalankan composer dump-autoload --no-scripts untuk membangun autoload tanpa dependensi eksternal. |
| target/ atau vendor/ tetap muncul di git status | Sudah terlanjur ter-commit sebelum .gitignore dipasang. | git rm -r --cached target vendor lalu commit ulang. |

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
