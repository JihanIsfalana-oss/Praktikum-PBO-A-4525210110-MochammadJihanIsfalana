# Analisis Komparatif Paradigma OOP: Java vs PHP 8
**Studi Kasus: Implementasi Kelas RekeningBank**

Dokumen ini menganalisis perbedaan pendekatan struktural, manajemen memori/state, dan penanganan fitur Object-Oriented Programming (OOP) antara bahasa **Java** dan **PHP 8** berdasarkan kode proyek `RekeningBank`.

---

## 1. Ringkasan Perbandingan Fitur

| Fitur OOP | Pendekatan Java | Pendekatan PHP 8 |
| :--- | :--- | :--- |
| **Constructor Overloading** | Didukung secara *native* (beberapa constructor dengan nama sama tapi beda parameter). | **Tidak didukung**. Digantikan dengan *Default Parameter* dan *Named Constructor*. |
| **Inisialisasi Properti** | Manual di dalam body constructor. | *Constructor Property Promotion* (Deklarasi + inisialisasi langsung di parameter). |
| **Konstanta & Variabel Statis** | Menggunakan `static` / `final static`. Akses via `NamaKelas.variabel`. | Menggunakan `const` dan `private static`. Akses via `self::` atau `static::`. |
| **Late Static Binding** | Ditangani secara otomatis lewat polimorfisme runtime. | Harus dideklarasikan eksplisit menggunakan kata kunci `static` (`new static()`). |

---

## 2. Analisis Perbedaan Struktur Utama

### A. Konstruksi Objek & Overloading
*   **Java (Constructor Chaining):**
    Java memungkinkan pembuatan *Constructor Ringkas* dan *Constructor Lengkap*. Kita melakukan **delegasi** menggunakan `this(...)` dari constructor ringkas ke constructor lengkap.
    ```java
    // Java mendelegasikan antar constructor
    public RekeningBank(String nomor, String pemilik) {
        this(nomor, pemilik, 0.0); 
    }
    ```
*   **PHP 8 (Default Parameter + Named Constructor):**
    Karena PHP tidak mendukung *overloading*, kelas menggunakan **Default Parameter** pada constructor utama untuk meniru constructor ringkas. Untuk kebutuhan khusus (seperti pembuatan jenis rekening spesifik), PHP menggunakan *Named Constructor* (Static Factory Method).
    ```php
    // PHP menggunakan default value dan static factory
    public function __construct(..., float $saldoAwal = 0) { ... }
    
    public static function rekeningPelajar(...): static {
        return new static($nomor, $pemilik, 0);
    }
    ```

### B. Validasi Terpusat (Prinsip DRY)
Kedua bahasa sukses menerapkan prinsip **DRY (Don't Repeat Yourself)** namun dengan mekanisme berbeda:
*   Di **Java**, validasi dipusatkan di *Constructor Lengkap*. Jalur pembuatan objek mana pun pasti akan bermuara dan diperiksa di constructor tersebut.
*   Di **PHP**, validasi dipusatkan di **satu-satunya constructor** (`__construct`). *Named constructor* seperti `rekeningPelajar` bertindak sebagai *wrapper* yang pada akhirnya tetap memanggil `new static()` yang memicu validasi utama tersebut.

### C. Alasan Penggunaan `new static()` vs `new self()` di PHP
Pada **TODO 5** kode PHP, diwajibkan menggunakan `new static()` karena mendukung **Late Static Binding**.
*    Jika menggunakan `new self()`, instansiasi akan *dikunci* hanya untuk kelas `RekeningBank` itu sendiri.
*   Jika menggunakan `new static()`, PHP akan melihat kelas mana yang sedang memanggilnya saat *runtime*. Jika di kemudian hari ada kelas `RekeningPremium extends RekeningBank` dan memanggil method `rekeningPelajar()`, maka objek yang tercipta adalah `RekeningPremium`, bukan kelas induknya.

---

## 3. Analisis Logika & Bug yang Diperbaiki

### A. Perbaikan Logika Validasi (Java & PHP)
Pada kode awal Java, terdapat kesalahan logika menggunakan operator `&&` (DAN) pada pengecekan error:
```java
// SALAH (Bug)
if(saldoAwal < 0 && nomor == null) { ... } 
```
*Dampak Bug:* Jika user memasukkan nomor rekening `null` tetapi saldo awalnya positif (misal `100000`), objek ilegal ini akan lolos dari validasi.
*Solusi:* Diubah menjadi pengecekan terpisah atau menggunakan operator `||` (ATAU) serta menambahkan fungsi pembersih spasi (`trim()`) pada PHP untuk mencegah nomor rekening yang hanya berisi spasi kosong (`"   "`).

### B. Mekanisme Penghitung Rekening (Static Counter)
Baik di Java maupun PHP, *counter* jumlah rekening (`jumlahRekening++` / `self::$jumlahRekening++`) **hanya boleh ditaruh di constructor utama**. 
*Jika ditaruh di method factory atau di kedua constructor, angka counter akan terhitung ganda (inkonsisten) saat proses delegasi objek terjadi.*

---

## 4. Kesimpulan Rekayasa Kode
1. **Java** memberikan fleksibilitas penulisan banyak constructor yang sangat rapi untuk variasi inisialisasi objek.
2. **PHP 8** menawarkan modernisasi kode lewat *Constructor Property Promotion* yang memangkas boilerplate code (baris kode repetitif), menjadikannya jauh lebih ringkas daripada Java dalam mendeklarasikan properti kelas.
3. Pemahaman *scope resolution operator* di PHP (`self::` untuk kelas saat ini, `static::` untuk polimorfisme runtime) sangat krusial agar tidak terjadi *bug* arsitektur saat kelas diwariskan.

---

## 5. Penggunaan AI
Tools   : Gemini
Prompt  : buatkan saya analisis.md nya yang rapi, terstruktur, jelas dari code java dan php ini
(CODE JAVA & PHP YANG SAYA BERIKAN UNTUK DIBUAT ANALISIS.md)