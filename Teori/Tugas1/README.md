# Tugas 1 — Class `Mahasiswa` (Java & PHP)
**Nama   :** Mochammad Jihan Isfalana
**NPM    :** 4525210110

## Domain
**Mahasiswa** — merepresentasikan seorang mahasiswa dengan atribut identitas
akademik (nama, NIM) serta atribut aktivitas akademik (jadwal mata kuliah
dan kegiatan kemahasiswaan).

## Struktur Class

____________________________________________________________________________________
| Field               | Tipe                | Keterangan                           |
|---------------------|---------------------|--------------------------------------|
| `nama`              | `String`            | Wajib diisi lewat constructor        |
| `nim`               | `String`            | Wajib diisi lewat constructor        |
| `programStudi`      | `String` (nullable) | Program studi mahasiswa              |
| `jadwalMataKuliah`  | `String` (nullable) | Jadwal mata kuliah yang diikuti      |
| `kegiatanMhs`       | `String` (nullable) | Kegiatan kemahasiswaan yang diikuti  |

**Method:**
- `belajar()` — menampilkan aktivitas belajar mahasiswa berdasarkan `jadwalMataKuliah`
- `kegiatan()` — menampilkan aktivitas kemahasiswaan berdasarkan `kegiatanMhs`

## Invarian & Alasan

1. **`jadwalMataKuliah` tidak boleh `null` saat `belajar()` dipanggil.**
   Alasan: method `belajar()` merepresentasikan aktivitas mahasiswa mengikuti
   kelas tertentu. Tanpa jadwal mata kuliah, tidak ada konteks valid untuk
   menjalankan aktivitas tersebut. Pelanggaran akan melempar
   `IllegalArgumentException` (Java) / `InvalidArgumentException` (PHP).

2. **`kegiatanMhs` tidak boleh `null` saat `kegiatan()` dipanggil.**
   Alasan: method `kegiatan()` merepresentasikan partisipasi mahasiswa dalam
   kegiatan himpunan/organisasi. Tanpa kegiatan yang ditentukan, tidak ada
   informasi yang bisa ditampilkan secara bermakna. Pelanggaran akan
   melempar `IllegalArgumentException` (Java) / `InvalidArgumentException` (PHP).

### Struktur Berkas
```Struktur Folder
PBO/ **(Root Folder)**
├── Tugas1/
    ├── img/
    ├── src/
    |    ├── Mahasiswa.java # Definisi class (Java)
    |    ├── Main.java # Program utama (Java)
    |    ├── Mahasiswa.php # Definisi class (PHP)
    |    ├── index.php # Program utama (PHP)
    └── README.md
```

## Cara Menjalankan

### Java
```Powershell / bash
javac Mahasiswa.java Main.java
java Main
```

### PHP
```Powershell / bash
php index.php
```

## Deklarasi Penggunaan AI
Asisten AI (Claude, Anthropic) digunakan untuk membantu menyusun struktur
dan format dokumen README.md ini, berdasarkan ketentuan tugas dan kode
program yang telah dibuat sebelumnya. AI tidak digunakan untuk menulis
kode Java/PHP pada tugas ini.

**Prompt:** 
Bertindaklah Sebagai Analyst dan juga Writer.
Saya ingin anda Membuatkan saya README.md nya sesuai instruksi pada gambar  secara rapi, terstruktur, dan jelas lalu jelaskan penggunaan AI "Claude" untuk membantu menulis README.md (Buatkan hasil outputnya hanya teks pada sesi percakapan ini saja biar saya yang buat README nya).
