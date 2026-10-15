# Deskripsi Kebutuhan — Sistem Perpustakaan

Deskripsi ini menjadi dasar seluruh pekerjaan Anda dari sesi 7 sampai sesi 14.
Bacalah dengan saksama; setiap kalimat mengandung informasi rancangan.

---

Perpustakaan Universitas Pancasila ingin mengganti pencatatan manual dengan
sebuah sistem.

Perpustakaan menyimpan **koleksi**. Setiap koleksi punya nomor induk, judul,
penulis, tahun terbit, dan jumlah eksemplar. Ada tiga jenis koleksi: **buku**
yang dapat dipinjam selama 14 hari, **majalah** yang hanya dapat dipinjam
selama 3 hari, dan **skripsi** yang sama sekali tidak dapat dipinjam dan hanya
boleh dibaca di tempat.

Setiap buku terdiri atas beberapa **bab**, masing-masing punya nomor dan judul.
Bab tidak punya arti apa pun di luar buku yang memuatnya, dan tidak pernah
berpindah dari satu buku ke buku lain.

**Anggota** perpustakaan punya nomor anggota, nama, dan jenis keanggotaan:
mahasiswa, dosen, atau anggota kehormatan. Nomor anggota tidak pernah berubah
setelah didaftarkan. Seorang anggota tidak boleh meminjam lebih dari tiga
koleksi pada saat bersamaan.

Setiap **peminjaman** mencatat satu anggota, satu koleksi, tanggal pinjam,
tanggal jatuh tempo, dan tanggal kembali. Tanggal jatuh tempo dihitung dari
tanggal pinjam ditambah masa pinjam koleksi yang bersangkutan. Selama belum
dikembalikan, tanggal kembali masih kosong.

Koleksi hanya dapat dipinjam bila masih ada eksemplar tersedia. Setiap
peminjaman mengurangi jumlah eksemplar tersedia sebanyak satu, dan setiap
pengembalian menambahkannya kembali.

Keterlambatan pengembalian dikenai **denda** per hari. Besar denda per hari
berbeda menurut jenis keanggotaan: mahasiswa Rp1.000, dosen Rp500, dan anggota
kehormatan bebas denda. Pihak perpustakaan memperkirakan akan ada jenis
keanggotaan baru di kemudian hari.

Sebuah koleksi dapat berstatus **tersedia, dipinjam, rusak, atau hilang**.
Koleksi yang berstatus rusak atau hilang tidak dapat dipinjam apa pun jenisnya.

Petugas perpustakaan dapat menambah koleksi, mendaftarkan anggota, mencatat
peminjaman dan pengembalian, serta melihat daftar koleksi yang sedang dipinjam.
Anggota dapat mencari koleksi dan melihat riwayat peminjamannya sendiri.

---

## Yang harus Anda lakukan

1. Tandai seluruh **kata benda** — calon kelas dan calon atribut.
2. Tandai seluruh **kata kerja** — calon method.
3. Tandai seluruh kalimat yang memuat **"tidak boleh", "harus", "hanya"** —
   itulah invariant dan aturan bisnisnya.
4. Coret kata benda yang ternyata hanya atribut, bukan kelas. Tuliskan alasannya.
5. Perhatikan kalimat *"Bab tidak punya arti apa pun di luar buku yang
   memuatnya"* dan *"Pihak perpustakaan memperkirakan akan ada jenis
   keanggotaan baru"*. Keduanya adalah petunjuk rancangan yang kuat —
   pikirkan apa maksudnya.
