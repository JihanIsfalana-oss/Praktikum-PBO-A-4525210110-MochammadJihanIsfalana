# Analisis TODO 4

`PerpustakaanService` menyimpan daftar `Buku` yang diterima dari luar melalui
`tambahKoleksi()`. Relasinya adalah **agregasi**, karena service tidak membuat
atau mengendalikan siklus hidup objek `Buku`.

Method yang diterapkan:

- `tambahKoleksi()` menambahkan buku ke daftar koleksi.
- `cariKoleksi()` mencari buku berdasarkan nomor induk.
- `pinjam()` memastikan buku ditemukan, lalu mengurangi stok.
- `kembalikan()` memastikan buku ditemukan, lalu menambah stok.

Validasi jumlah stok dan penolakan peminjaman ketika stok habis dikelola oleh
kelas `Buku`. Data anggota belum disimpan oleh service karena implementasi
sesi 7 masih berupa kerangka dan akan dikembangkan pada sesi berikutnya.
