# REFACTORING.md

Nama / NIM: ..........................

---

## Bagian 1 — Temuan pelanggaran SOLID

Temukan **minimal tiga** pelanggaran pada kode Anda sendiri dari sesi 10–11.
Bukan contoh dari slide — kode Anda sendiri.

### Temuan 1

| | |
|---|---|
| Prinsip yang dilanggar | |
| Berkas dan baris | |
| Mengapa ini pelanggaran | |
| Akibatnya bila dibiarkan | |

### Temuan 2

| | |
|---|---|
| Prinsip yang dilanggar | |
| Berkas dan baris | |
| Mengapa ini pelanggaran | |
| Akibatnya bila dibiarkan | |

### Temuan 3

| | |
|---|---|
| Prinsip yang dilanggar | |
| Berkas dan baris | |
| Mengapa ini pelanggaran | |
| Akibatnya bila dibiarkan | |

> **Petunjuk tempat mencari.**
> SRP: kelas yang menyimpan data sekaligus mengatur aturan bisnis sekaligus mencetak keluaran.
> OCP: rantai if untuk jenis anggota.
> DIP: pembuatan objek penyimpanan langsung di dalam layanan.

---

## Bagian 2 — Tabel sebelum dan sesudah

| Aspek | Sebelum | Sesudah | Alasan perubahan |
|---|---|---|---|
| Penyimpanan data | | | |
| Aturan denda | | | |
| Perakitan objek | | | |
| Kemampuan diuji | | | |

---

## Bagian 3 — Bukti Open-Closed

Setelah menambahkan aturan denda keempat (alumni):

- Berkas **baru** yang ditambahkan: ..........
- Berkas **lama** yang isinya berubah: ..........
- Apakah `LibraryService` ikut disunting? ..........

> Jika `LibraryService` ikut disunting, Strategy belum benar diterapkan.
> Periksa ulang sebelum melanjutkan.

---

## Bagian 4 — Bukti Dependency Inversion

Bila penyimpanan diganti dari memori ke basis data:

- Berkas **baru** yang perlu ditulis: ..........
- Berkas **lama** yang isinya berubah: ..........

---

## Bagian 5 — Yang sengaja TIDAK diberi pattern

Sebutkan **satu** tempat di kode Anda yang sengaja Anda biarkan sederhana,
beserta alasannya.

**Bagian ini dinilai sama beratnya dengan Bagian 1 sampai 4.** Kemampuan
menahan diri dari over-engineering sama pentingnya dengan kemampuan
menerapkan pattern.

| | |
|---|---|
| Bagian kode | |
| Pattern yang sempat dipertimbangkan | |
| Alasan tidak dipakai | |

> Alasan yang diterima berpijak pada kebutuhan nyata — misalnya "hanya ada
> satu implementasi dan tidak ada rencana bertambah", atau "constructor-nya
> sudah sederhana sehingga factory hanya menambah lapisan".
>
> Alasan "belum sempat" tidak diterima.
