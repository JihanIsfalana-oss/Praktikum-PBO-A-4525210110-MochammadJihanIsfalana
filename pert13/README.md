# Praktikum Sesi 13 — Menelusuri dan Memodifikasi Aplikasi Laravel 13

**Sub-CPMK-P9 — Mahasiswa mampu menelusuri, menjalankan, dan memodifikasi aplikasi Laravel 13 dan Spring Boot 4.1. (P3, C4)**

**Keterkaitan teori:** Pertemuan 14 — OOP dalam Laravel 13  
**Durasi:** 170 menit (1 SKS praktikum) · **Bobot:** 7% dari nilai praktikum  
**Bahasa:** PHP (Laravel 13)

> Versi cetak modul ini: [`Modul-Praktikum-13-menelusuri-dan-memodifikasi-aplikasi-laravel-13.docx`](Modul-Praktikum-13-menelusuri-dan-memodifikasi-aplikasi-laravel-13.docx). Kerangka kode: [`starter/`](starter/).

---

## A. Tujuan sesi

1. Menjalankan proyek Laravel dan memahami alur satu permintaan HTTP.
2. Menunjuk letak setiap konsep OOP yang telah dipelajari di dalam kode framework.
3. Menambah satu fitur kecil yang menyentuh seluruh lapisan aplikasi.
4. Membandingkan struktur Laravel dengan hasil refactoring Anda sendiri pada sesi 12.

## B. Alat dan bahan

- Laravel 13 sudah terpasang (tugas sesi 12). PHP 8.3–8.5 dan Composer.
- Proyek starter Sistem Perpustakaan versi Laravel yang disediakan dosen.
- Basis data SQLite (paling sederhana) atau MySQL/MariaDB.

## C. Berkas starter

Salin ke folder tugas Anda; **jangan** menyunting berkas aslinya. Setiap komentar `TODO` harus Anda lengkapi sendiri — kode yang disalin dari sumber lain akan terlihat pada sesi demo.

| Berkas | Keterangan |
|---|---|
| `studi-kasus/perpustakaan/03-laravel/` | Proyek Laravel Sistem Perpustakaan yang sudah berjalan. |
| `starter/lembar-penelusuran.md` | Tabel kosong yang harus Anda isi dengan nama berkas dan nomor baris. |
| `starter/TODO-fitur-bahasa.md` | Rincian fitur yang harus Anda tambahkan, dengan daftar berkas yang perlu disentuh. |

## D. Langkah kerja

Kerjakan berurutan. Jangan melanjutkan ke langkah berikutnya sebelum checkpoint terpenuhi.

### Langkah 1 — Menjalankan proyek

- Salin proyek starter, pasang dependensinya, siapkan berkas .env, dan jalankan migrasi beserta seeder.

**Terminal**

```bash
cd studi-kasus/perpustakaan/03-laravel
composer install
cp .env.example .env
php artisan key:generate
touch database/database.sqlite
php artisan migrate --seed
php artisan serve
```

> **Checkpoint —** Membuka http://localhost:8000/api/buku menampilkan daftar buku dalam bentuk JSON.

### Langkah 2 — Mengisi lembar penelusuran

- Salin lembar-penelusuran.md ke sesi-13/. Isi kolom "berkas" dan "baris" untuk setiap konsep OOP:
- pewarisan, enkapsulasi, trait, enum, relasi objek, exception, interface sebagai kontrak, dependency injection, dan collections.
- JANGAN menyalin dari modul. Tulis nomor baris yang benar-benar Anda temukan di proyek ini.

> **Checkpoint —** Lembar penelusuran terisi lengkap dengan nomor baris yang bisa diverifikasi.

### Langkah 3 — Menelusuri alur satu permintaan HTTP

- Telusuri GET /api/buku/{isbn} dari routes/api.php sampai respons JSON kembali.
- Tuliskan urutannya di sesi-13/alur-request.md, sebutkan setiap berkas yang dilewati.
- Aktifkan pencatatan query untuk melihat SQL yang sesungguhnya dijalankan, lalu salin ke berkas yang sama.

**Terminal**

```bash
php artisan tinker
>>> \DB::enableQueryLog();
>>> App\Models\Buku::where("tahun", ">", 2024)->get();
>>> \DB::getQueryLog();
```

> **Checkpoint —** alur-request.md memuat minimal enam tahap dan satu contoh SQL yang dihasilkan Eloquent.

### Langkah 4 — Menambahkan fitur: kolom bahasa

- Ikuti TODO-fitur-bahasa.md. Fitur ini kecil, tetapi menyentuh seluruh lapisan:
  1. Migration baru untuk menambah kolom bahasa.
  2. Tambahkan bahasa ke $fillable pada Model.
  3. Tambahkan aturan validasi pada Form Request.
  4. Pastikan bahasa muncul pada respons API.
  5. Tambahkan satu query scope: scopeBerbahasa($query, $bahasa).
- Jalankan migrasi dan uji lewat curl atau Postman.

**Terminal**

```bash
php artisan make:migration tambah_kolom_bahasa_ke_tabel_buku
php artisan migrate
curl -X POST http://localhost:8000/api/buku -H "Content-Type: application/json" -d '{"isbn":"978-602-1234-56-7","judul":"Uji","penulis":"X","tahun":2026,"jumlah_eksemplar":2,"bahasa":"id"}'
```

> **Checkpoint —** POST tanpa kolom bahasa ditolak dengan pesan validasi; POST dengan bahasa berhasil dan nilainya muncul di respons.

### Langkah 5 — Membuat custom exception dengan render()

- Buat BukuTidakBolehDipinjamException yang membawa isbn dan alasan.
- Lengkapi method render() sehingga mengembalikan HTTP 422 beserta data penyebabnya.
- Bandingkan dengan exception yang Anda buat sendiri pada sesi 10 — strukturnya nyaris sama.

> **Checkpoint —** Meminjam buku berstatus rusak menghasilkan respons 422 dengan isbn dan alasan di badan respons.

### Langkah 6 — Membuktikan Service Container bekerja

- Buka Service Provider yang mengikat BookRepository ke EloquentBookRepository.
- Buat implementasi kedua: InMemoryBookRepository (boleh sederhana). Ubah binding-nya ke implementasi baru itu.
- Jalankan ulang aplikasi. Amati bahwa Controller dan Service TIDAK BERUBAH sama sekali.
- Kembalikan binding ke Eloquent. Catat pengalaman ini di sesi-13/container.md.

> **Checkpoint —** Mengganti satu baris di Service Provider mengubah sumber data seluruh aplikasi.

### Langkah 7 — Membandingkan dengan kode Anda sendiri

- Buat sesi-13/perbandingan-dengan-punya-saya.md.
- Bandingkan LibraryService hasil sesi 12 dengan PeminjamanService di Laravel. Apa yang mirip? Apa yang berbeda?
- Bandingkan pula perakitan objek di App Anda dengan Service Provider Laravel.

> **Checkpoint —** Dokumen memuat minimal tiga kemiripan dan dua perbedaan yang konkret.

## E. Latihan mandiri di lab

_Dikerjakan bila langkah kerja selesai lebih awal. Tidak wajib, tetapi menambah nilai pada aspek penerapan konsep._

1. Tambahkan satu Middleware yang menolak permintaan bila header X-Anggota-Aktif tidak bernilai true. Amati bahwa Laravel yang memanggil method handle() Anda, bukan sebaliknya.
2. Ubah satu query yang menimbulkan masalah N+1 menjadi eager loading dengan with(). Bandingkan jumlah query pada log sebelum dan sesudah.

## F. Tugas rumah

1. Lengkapi lembar penelusuran sampai seluruh baris terisi — ini menjadi separuh bahan Tugas 5 pada mata kuliah teori.
2. Persiapan sesi 14: buat proyek Spring Boot 4.1 lewat start.spring.io (Maven, Java 25, Spring Web, Spring Data JPA, H2, Validation) dan pastikan ./mvnw spring-boot:run berhasil. Selesaikan SEBELUM sesi 14.

## G. Luaran yang dikumpulkan

Seluruh luaran diserahkan melalui repositori Git pribadi Anda, dengan riwayat commit yang menunjukkan proses pengerjaan — bukan satu commit tunggal di akhir.

| Berkas / folder | Keterangan |
|---|---|
| `sesi-13/lembar-penelusuran.md` | Pemetaan konsep OOP ke berkas dan nomor baris. |
| `sesi-13/alur-request.md` | Penelusuran alur HTTP dan contoh SQL yang dihasilkan. |
| `Proyek Laravel dengan fitur bahasa` | Migration, Model, Form Request, scope, dan respons yang sudah diperbarui. |
| `Custom exception dengan render()` | Menghasilkan HTTP 422. |
| `sesi-13/container.md dan perbandingan-dengan-punya-saya.md` | Catatan percobaan Service Container dan perbandingan dengan kode sendiri. |

## H. Rubrik penilaian sesi ini

| Aspek | Bobot | Kriteria |
|---|---|---|
| Kebenaran fungsional | 35% | Seluruh perintah pada langkah kerja berjalan dan menghasilkan keluaran yang diminta. |
| Penerapan konsep sesi ini | 30% | Konsep yang menjadi Sub-CPMK sesi ini diterapkan dengan tepat, bukan sekadar membuat program berjalan. |
| Keterbacaan dan konvensi | 10% | Penamaan bermakna, format konsisten, komentar seperlunya, riwayat commit wajar. |
| Demo dan pertanyaan lisan | 25% | Mampu menjelaskan setiap baris kode sendiri dan menjawab pertanyaan demo. MENGGUGURKAN: tanpa demo, tugas tidak dinilai. |

## I. Pertanyaan demo

> Pertanyaan berikut akan diajukan saat Anda mendemokan pekerjaan sesi ini. Daftar ini sengaja dibuka agar Anda mempersiapkan **pemahaman**, bukan hafalan. Anda boleh memakai alat bantu apa pun saat mengerjakan — tetapi kode yang tidak dapat Anda jelaskan sendiri tidak dinilai.

1. Tunjukkan baris di Model Buku yang merupakan pewarisan. Apa yang diwarisi kelas ini, dan dari mana?
2. Apa fungsi $fillable? Hapus kolom bahasa dari $fillable sekarang, lalu kirim POST. Apa yang terjadi?
3. Telusuri GET /api/buku/978-1 dari awal sampai respons. Sebutkan setiap berkas yang dilewati.
4. Controller Anda menerima PeminjamanService lewat constructor. Siapa yang membuat objek PeminjamanService itu? Bagaimana ia tahu apa yang harus disuntikkan?
5. Ubah binding di Service Provider ke InMemoryBookRepository sekarang. Berkas mana lagi yang harus ikut berubah?
6. Tunjukkan method render() pada exception Anda. Siapa yang memanggilnya? Anda tidak pernah menulis pemanggilannya, bukan?
7. Bandingkan PeminjamanService Laravel dengan LibraryService buatan Anda di sesi 12. Sebutkan satu hal yang benar-benar sama.

## J. Kesalahan yang sering terjadi

| Gejala | Penyebab yang lazim | Cara memperbaiki |
|---|---|---|
| php artisan serve gagal: No application encryption key | Berkas .env belum dibuat atau APP_KEY kosong. | cp .env.example .env lalu php artisan key:generate. |
| SQLSTATE: no such table | Migrasi belum dijalankan, atau berkas SQLite belum ada. | touch database/database.sqlite lalu php artisan migrate --seed. |
| Kolom bahasa tidak muncul di respons meski sudah ditambahkan | Belum masuk $fillable, atau tersembunyi lewat $hidden, atau cache konfigurasi. | Periksa $fillable dan $hidden; jalankan php artisan config:clear. |
| Validasi tidak berjalan | Controller memakai Request biasa, bukan Form Request. | Ganti type hint parameter menjadi kelas Form Request Anda. |
| Perubahan kode tidak terasa | Cache konfigurasi atau route masih aktif. | php artisan optimize:clear. |

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
