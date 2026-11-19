# Pilihan Koleksi dan Null Safety

## Pemilihan struktur data

- **Map** dipakai untuk catalog buku berdasarkan ISBN atau nomor induk karena pencarian cukup langsung melalui kunci. Kompleksitas pencarian rata-rata adalah O(1), bukan O(n) seperti pencarian linear pada List.
- **List** dipakai untuk urutan hasil laporan dan daftar bab karena urutan koleksi perlu dipertahankan.
- **Set** disiapkan sebagai pilihan bila model membutuhkan daftar unik; pada implementasi saat ini Map menjaga keunikan ISBN secara langsung.

## Java Optional

`Repository<T>.cariById(String)` mengembalikan `Optional<T>`, sehingga pemanggil harus memeriksa `isPresent()` atau memakai `orElseThrow()`. Ini menghilangkan pengembalian `null` langsung pada pencarian. Pada `LibraryService.pinjam()`, pengecekan data dilakukan sebelum mutasi stok agar tidak terjadi `NullPointerException` dari pencarian Map.

## PHP nullable

`KoleksiBuku::cariByIsbn()` memakai `?Buku` dan mengembalikan `null` sebagai nilai kosong yang eksplisit. Pemanggil dapat memakai `?->` atau `??`. `array_filter()` menerima urutan array dulu, baru callable; `array_map()` menerima callable dulu, baru array.

## Bukti kegagalan sengaja

Baris `cariById()` diubah menjadi `return Optional.empty();`. Hasilnya:

```text
RepositoryTest.simpanLaluTemukan:29 expected: <true> but was: <false>
Tests run: 8, Failures: 1, Errors: 1, Skipped: 0
```

Pesan itu membantu menemukan lokasi masalah: item tetap tersimpan, tetapi pencarian selalu kosong; dengan stack trace pada `kunciSamaMenimpaItem` juga terlihat bahwa `Optional.orElseThrow()` tidak menemukan nilai. Setelah baris dikembalikan ke `Optional.ofNullable(data.get(id))`, seluruh uji kembali hijau.
