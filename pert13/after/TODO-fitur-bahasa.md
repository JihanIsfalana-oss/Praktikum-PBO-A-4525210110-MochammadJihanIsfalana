# TODO — Menambahkan kolom `bahasa`

Fitur ini kecil, tetapi sengaja dipilih karena menyentuh **seluruh lapisan**
aplikasi. Kerjakan berurutan, dan jalankan ulang aplikasi setiap kali selesai
satu langkah.

Fitur yang sama akan Anda kerjakan di Spring Boot pada sesi 14. Catat waktu
yang Anda butuhkan — perbandingannya menjadi bahan Tugas 5.

---

## 1. Migration

```bash
php artisan make:migration tambah_kolom_bahasa_ke_tabel_buku
```

Di dalam method `up()`, tambahkan kolom `bahasa` bertipe string dengan panjang
wajar dan nilai bawaan `'id'`. Jangan lupa method `down()` untuk menghapusnya
kembali — migration yang tidak bisa dibatalkan adalah migration yang belum selesai.

```bash
php artisan migrate
```

**Checkpoint:** `php artisan migrate:status` menampilkan migration Anda sebagai
sudah dijalankan.

---

## 2. Model

Buka `app/Models/Buku.php`.

- Tambahkan `'bahasa'` ke `$fillable`.
- **Percobaan:** jangan tambahkan dulu. Kirim POST dengan kolom bahasa, lalu
  amati bahwa nilainya diabaikan. Baru setelah itu tambahkan, dan bandingkan.
  Catat pengalaman ini — ini pertanyaan demo nomor 2.

---

## 3. Validasi

Buka Form Request untuk penyimpanan buku.

Tambahkan aturan untuk `bahasa`: wajib diisi, berupa string, dan hanya boleh
bernilai dari daftar yang Anda tentukan (misalnya `id`, `en`, `ar`).

Tambahkan pula pesan kesalahan berbahasa Indonesia di method `messages()`.

**Checkpoint:** POST tanpa kolom bahasa ditolak dengan pesan yang Anda tulis
sendiri, bukan pesan bawaan berbahasa Inggris.

---

## 4. Respons

Pastikan `bahasa` muncul di respons `GET /api/buku`.

Bila proyek memakai API Resource, tambahkan pemetaannya di sana. Bila
mengembalikan model langsung, cukup pastikan tidak masuk `$hidden`.

---

## 5. Query scope

Tambahkan query scope pada Model:

```php
public function scopeBerbahasa($query, string $bahasa)
{
    // TODO
}
```

Lalu pakai dari Controller atau Repository sehingga
`GET /api/buku?bahasa=id` menyaring hasilnya.

**Checkpoint:** endpoint menghasilkan daftar yang tersaring, dan SQL yang
tercetak di log menunjukkan klausa WHERE — bukan penyaringan di PHP.

---

## 6. Menguji

```bash
curl -X POST http://localhost:8000/api/buku \
  -H "Content-Type: application/json" \
  -d '{"isbn":"978-602-1234-56-7","judul":"Uji","penulis":"X","tahun":2026,"jumlah_eksemplar":2,"bahasa":"id"}'

curl "http://localhost:8000/api/buku?bahasa=id"
```

---

## Yang dicatat untuk Tugas 5

| Pertanyaan | Jawaban Anda |
|---|---|
| Berapa berkas yang Anda sentuh? | |
| Berapa lama waktu yang dibutuhkan? | |
| Bagian mana yang paling membingungkan? | |
| Adakah yang "ajaib" — bekerja tanpa Anda tahu sebabnya? | |
