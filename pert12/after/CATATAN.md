# Catatan tentang kerangka sesi 12

Berkas kerangka di folder ini **sengaja tidak dapat dikompilasi sendiri**,
karena keduanya merujuk pada kelas milik proyek Anda:

- `BookRepository.java` dan `InMemoryBookRepository.java` memakai
  `id.ac.univpancasila.perpustakaan.model.Buku` — kelas dari proyek Maven Anda
  sendiri, hasil sesi 9.
- `repository.php` memakai `Perpustakaan\Model\Buku` dari proyek Composer Anda.

Cara memakainya:

1. Salin berkas ini ke dalam proyek Anda, pada package/namespace yang sesuai:
   - `src/main/java/id/ac/univpancasila/perpustakaan/repository/`
   - `src/main/java/id/ac/univpancasila/perpustakaan/denda/`
   - `src/Repository/` dan `src/Denda/` untuk versi PHP
2. Sesuaikan `import` / `use` dengan struktur proyek Anda.
3. Baru kemudian lengkapi TODO-nya.

Kalau Anda mengompilasi berkas ini di luar proyek dan mendapat pesan
"cannot find symbol: class Buku", itu bukan kesalahan Anda — memang begitu
seharusnya. Salin ke proyek Anda lebih dulu.
