# Deskripsi Kebutuhan — Relasi Objek Sistem Perpustakaan

Dokumen ini merangkum kebutuhan yang dipakai pada sesi 7. Fokus sesi ini
adalah membedakan **komposisi** dan **agregasi**, menjaga enkapsulasi, serta
menerapkan invariant sederhana pada stok buku. Fitur peminjaman lengkap akan
disempurnakan pada sesi berikutnya.

## Kebutuhan Utama

1. Sistem memiliki `Buku` dengan nomor induk, judul, penulis, dan jumlah
   eksemplar.
2. Sebuah `Buku` memiliki beberapa `Bab`. Bab dibuat dengan nomor dan judul
   melalui buku yang menaunginya.
3. Sistem memiliki `Jurusan` dengan kode dan nama, serta `Mahasiswa` dengan NIM
   dan nama.
4. Jurusan dapat mendaftarkan mahasiswa yang sudah dibuat dari luar dan dapat
   mengeluarkannya kembali.
5. Layanan perpustakaan dapat menambah buku, mencari buku berdasarkan nomor
   induk, meminjam, dan mengembalikan buku.

## Aturan dan Invariant

- Jumlah eksemplar buku tidak boleh negatif.
- Stok buku tidak boleh dikurangi ketika sudah nol.
- Bab hanya dibuat melalui `Buku::tambahBab()` atau
  `Buku.tambahBab(...)`; pemanggil tidak memasukkan objek `Bab` dari luar.
- Daftar bab yang diterima pemanggil tidak boleh menjadi akses langsung untuk
  mengubah koleksi internal buku.
- Mahasiswa tidak boleh didaftarkan dua kali pada jurusan yang sama.
- Mahasiswa `null` tidak boleh didaftarkan pada versi Java.
- Mahasiswa yang dikeluarkan dari jurusan tetap hidup sebagai objek di luar
  jurusan.
- Peminjaman hanya mengurangi stok buku yang ditemukan; pengembalian hanya
  menambah stok buku yang ditemukan.

## Kata Benda dan Keputusan Kelas

| Kata benda                                   | Keputusan       | Alasan                                                                  |
| -------------------------------------------- | --------------- | ----------------------------------------------------------------------- |
| Buku                                         | Kelas           | Memiliki data, perilaku bab, dan perilaku stok.                         |
| Bab                                          | Kelas bagian    | Memiliki nomor dan judul, tetapi tidak bermakna di luar buku.           |
| Jurusan                                      | Kelas           | Mengelola daftar mahasiswa dan operasi pendaftaran.                     |
| Mahasiswa                                    | Kelas           | Dibuat dari luar jurusan dan dapat tetap hidup setelah dikeluarkan.     |
| PerpustakaanService                          | Kelas layanan   | Menyediakan operasi tambah, cari, pinjam, dan kembali.                  |
| nomor induk, judul, penulis, kode, nama, NIM | Atribut         | Hanya menyimpan nilai data, bukan objek yang memiliki perilaku mandiri. |
| jumlah eksemplar                             | Atribut         | Menyatakan keadaan stok buku dan diubah melalui method stok.            |
| koleksi, daftar bab, daftar mahasiswa        | Atribut koleksi | Menyimpan hubungan objek di dalam `Buku` atau `Jurusan`.                |
| peminjaman, pengembalian                     | Method/operasi  | Menyatakan tindakan layanan, bukan kelas pada sesi ini.                 |

## Kata Kerja dan Calon Method

- **membuat/menambah bab** menjadi `tambahBab()` pada `Buku`.
- **menyediakan** menjadi `tersedia()` pada `Buku`.
- **mengurangi stok** menjadi `kurangiStok()`.
- **menambah stok** menjadi `tambahStok()`.
- **mengambil data** menjadi getter seperti `getNomorInduk()`, `getJudul()`,
  `getBab()`, `getNama()`, dan `getMahasiswa()`.
- **mendaftarkan mahasiswa** menjadi `daftarkan()` pada `Jurusan`.
- **mengeluarkan mahasiswa** menjadi `keluarkan()` pada `Jurusan`.
- **menambah dan mencari koleksi** menjadi `tambahKoleksi()` dan `cariKoleksi()`
  pada `PerpustakaanService`.
- **meminjam dan mengembalikan** menjadi `pinjam()` dan `kembalikan()` pada
  `PerpustakaanService`.

## Keputusan Relasi

### `Buku` dan `Bab`: Komposisi

Bab tidak mempunyai arti di luar buku yang memuatnya. Karena itu, buku menjadi
pemilik siklus hidup bab:

- Java menggunakan `Buku.Bab` sebagai nested class dengan constructor `private`.
- Buku membuat bab sendiri melalui `tambahBab(int, String)`.
- `getBab()` mengembalikan `List.copyOf(bab)`, sehingga koleksi internal tidak
  dapat dikosongkan atau ditambah dari luar.
- PHP tidak memiliki nested class. Class `Bab` tetap memiliki constructor publik,
  tetapi propertinya `readonly`; konvensi desain menetapkan bahwa pembuatan bab
  dilakukan hanya oleh `Buku::tambahBab()`.
- Pada PHP, array dikembalikan berdasarkan nilai, tetapi objek di dalam array
  tetap berupa referensi yang sama. Karena itu properti `Bab` dibuat `readonly`.

### `Jurusan` dan `Mahasiswa`: Agregasi

Mahasiswa dibuat dari luar jurusan, kemudian diserahkan ke `daftarkan()`. Saat
`keluarkan()` dipanggil, relasi dihapus tetapi objek mahasiswa tetap dapat
digunakan. Java menolak `null` dan duplikat; PHP mencegah duplikat dengan
perbandingan identitas objek.

### `PerpustakaanService` dan `Buku`: Agregasi

Service menerima buku dari luar melalui `tambahKoleksi()`, lalu menyimpan
referensinya. Service tidak membuat buku dan tidak menentukan siklus hidupnya,
sehingga hubungan ini diperlakukan sebagai agregasi.

## Kesesuaian Dengan UML

Class diagram di `src/after/uml/perpustakaan-kerangka.puml` menggunakan nama
kelas konseptual `Buku`, `Bab`, `Jurusan`, `Mahasiswa`, dan
`PerpustakaanService`. Java merealisasikan `Bab` dan `Mahasiswa` sebagai nested
class, sedangkan PHP merealisasikannya sebagai class terpisah. Perbedaan
bahasa tersebut tidak mengubah makna relasi komposisi dan agregasinya.

## Batasan Sesi 7

`PerpustakaanService` baru memakai `Buku` sebagai contoh koleksi. Data anggota,
riwayat `Peminjaman`, tanggal, status koleksi, denda, serta jenis koleksi lain
belum diimplementasikan pada sesi ini. Aspek tersebut menjadi perluasan pada
sesi berikutnya setelah model dan aturan bisnis diperdalam.
