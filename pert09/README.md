# **Pertemuan 09 - Praktikum Pemrograman Berorientasi Objek (PBO)**

## Biodata Mahasiswa

- **Nama**: Mochammad Jihan Isfalana
- **NIM**: 4525210110
- **Kelas**: Praktikum PBO A

## Mata Kuliah dan Dosen

- **Mata Kuliah**: Pemrograman Berorientasi Objek
- **Dosen Pengampu**: Adi Wahyu Pribadi, S.Si., M.Kom.

---

## Tugas Pertemuan 09

**Materi**: Package, namespace, dan build tool menggunakan Maven untuk Java
serta Composer dan PSR-4 untuk PHP.

Tugas yang dikerjakan:

1. Menata proyek Java ke dalam struktur Maven dan package domain terbalik.
2. Mengelola dependensi Java melalui `pom.xml`, termasuk Datafaker.
3. Menata proyek PHP dalam namespace `Perpustakaan` dan memuat kelas melalui
   autoload PSR-4 Composer.
4. Menggunakan Carbon sebagai dependensi eksternal PHP untuk mengolah tanggal.
5. Menjalankan dan memvalidasi proyek Java dan PHP.

---

## Pengerjaan Tugas, Penjelasan, dan Hasil

### 1. Konfigurasi Build Tool

#### Java — Maven

File `perpustakaan/pom.xml` menetapkan koordinat proyek, versi compiler Java,
dependensi JUnit untuk pengujian, Datafaker untuk membuat nama contoh, dan
konfigurasi `exec-maven-plugin` untuk menjalankan class utama.

- Sebelum konfigurasi: [pom.xml sebelum](./images/pom-xml-before.png)
- Sesudah konfigurasi: [pom.xml sesudah](./images/pom-xml-after.png)

#### PHP — Composer

File `php/composer.json` menetapkan kebutuhan PHP dan Carbon. Pemetaan
`Perpustakaan\\` ke `src/` mengaktifkan PSR-4, sedangkan pemetaan
`Perpustakaan\\Tests\\` disiapkan untuk kode pengujian.

- [composer.json sesudah](./images/composer-after.png)

### 2. Struktur dan Implementasi

#### Java

Proyek Maven berada di folder `perpustakaan/`. Kode aplikasi dikelompokkan
dengan package `id.ac.univpancasila.perpustakaan`, yang memiliki sub-package
`main`, `model`, `service`, dan `util`. Struktur package tersebut mengikuti
lokasi file di `src/main/java`.

| Bagian | Penjelasan |
| --- | --- |
| `main/App.java` | Menjalankan demonstrasi model dan layanan perpustakaan. |
| `model/` | Berisi model buku, anggota, mahasiswa, dan jurusan. |
| `service/PerpustakaanService.java` | Menyediakan koleksi buku, pencarian, serta proses peminjaman dan pengembalian. |
| `util/Format.java` | Menyediakan format nilai rupiah. |
| `pom.xml` | Mengatur identitas proyek, dependensi, versi Java, dan plugin Maven. |

#### PHP

Proyek Composer berada di folder `php/`. Kelas-kelas dalam `src/Model` dan
`src/Service` menggunakan namespace `Perpustakaan\\Model` dan
`Perpustakaan\\Service`. Titik masuk `main.php` hanya memerlukan
`vendor/autoload.php` Composer memuat kelas aplikasi berdasarkan PSR-4.

| File | Penjelasan |
| --- | --- |
| `src/Model/Buku.php` | Menyimpan informasi buku dan bab, memvalidasi stok, serta menjaga stok agar tidak negatif. |
| `src/Model/Bab.php` | Mewakili bab yang dibuat melalui `Buku::tambahBab()`. |
| `src/Model/Jurusan.php` | Mengelola daftar mahasiswa dan mencegah mahasiswa yang sama didaftarkan berulang kali. |
| `src/Model/Anggota.php` | Mengelola data anggota serta memeriksa masa keanggotaan menggunakan Carbon. |
| `src/Service/PerpustakaanService.php` | Mengelola koleksi, peminjaman, dan pengembalian buku beserta stoknya. |
| `composer.json` dan `composer.lock` | Menetapkan autoload PSR-4, kebutuhan PHP, serta versi dependensi yang terpasang. |
| `main.php` | Menjalankan demonstrasi relasi, layanan, stok, dan tanggal jatuh tempo. |

### 3. Cara Menjalankan

Pastikan Java/JDK 25, Maven, PHP 8.5.12 atau lebih baru yang sesuai dengan
constraint proyek, dan Composer tersedia di terminal.

#### Java

Dari root repositori:

```bash
cd pert09/perpustakaan
mvn clean
mvn compile
mvn exec:java
```

Hasil perintah Maven:

- [mvn clean](./images/mvn-clean.png)
- [mvn compile](./images/mvn-compile.png)
- [mvn exec:java](./images/mvn-exec-java.png)

#### PHP

Dari root repositori:

```bash
cd pert09/php
composer install
php main.php
```

`composer install` memasang versi dependensi sesuai `composer.lock` dan
membangun autoloader. Hasil menjalankan aplikasi:

- [php main.php](./images/php-run.png)

### 4. Hasil Pengujian

Program Java berhasil dibersihkan, dikompilasi, dan dijalankan melalui Maven.
Demonstrasi Java menampilkan relasi buku-bab, agregasi jurusan-mahasiswa,
format rupiah, serta perubahan stok ketika buku dipinjam dan dikembalikan.

Konfigurasi Composer valid, semua berkas PHP lolos pemeriksaan sintaks, dan
aplikasi PHP berhasil dijalankan. Demonstrasi PHP menampilkan bab buku,
agregasi mahasiswa, stok sebelum dan sesudah peminjaman, tanggal jatuh tempo
14 hari menggunakan Carbon, serta status keanggotaan.

### 5. Keputusan Desain

- Package Java menggunakan nama domain terbalik dan susunan folder yang
  mengikuti deklarasi package.
- Composer menggunakan PSR-4 agar kelas pada namespace `Perpustakaan\\`
  ditemukan otomatis dari folder `src/`.
- Datafaker dan Carbon digunakan sebagai dependensi eksternal pada masing-masing
  proyek, bukan hanya dicantumkan di konfigurasi.
- `composer.lock` dipertahankan agar instalasi Composer menggunakan versi
  dependensi yang konsisten.
- Folder hasil build seperti `target/` dan `vendor/` merupakan artefak lokal
  dependensi PHP dapat dipasang kembali dengan `composer install`.

---

## Kesimpulan

Praktikum ini menerapkan struktur package dan namespace pada Java serta
autoload PSR-4 pada PHP. Maven dan Composer membantu mengelola proses build dan
dependensi sehingga aplikasi dapat dijalankan dengan langkah yang terukur dan
reproducible.

Dokumen ini dibuat sebagai bagian dari tugas Praktikum PBO A &copy Milik
Mochammad Jihan Isfalana - 4525210110
