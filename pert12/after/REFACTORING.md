# REFACTORING.md

Nama / NIM: Mochammad Jihan Isfalana / 4525210110

## Bagian 1 — Temuan pelanggaran SOLID

### Temuan 1

| Aspek               | Temuan                                                                                              |
| ------------------- | --------------------------------------------------------------------------------------------------- |
| Prinsip             | SRP                                                                                                 |
| Berkas/bukti        | Versi lama `LibraryService` menyimpan stok, judul, dan jumlah pinjaman sekaligus mengatur transaksi |
| Mengapa pelanggaran | Satu kelas memiliki beberapa alasan perubahan sekaligus                                             |
| Akibat              | Perubahan penyimpanan berisiko mengganggu aturan bisnis                                             |

### Temuan 2

| Aspek               | Temuan                                                       |
| ------------------- | ------------------------------------------------------------ |
| Prinsip             | OCP                                                          |
| Berkas/bukti        | Jenis denda lama memerlukan penambahan percabangan           |
| Mengapa pelanggaran | Jenis anggota baru memaksa pengubahan kode yang sudah stabil |
| Akibat              | Risiko regression dan LibraryService semakin kompleks        |

### Temuan 3

| Aspek               | Temuan                                                                 |
| ------------------- | ---------------------------------------------------------------------- |
| Prinsip             | DIP                                                                    |
| Berkas/bukti        | Service bergantung pada penyimpanan konkret dan membuat objek internal |
| Mengapa pelanggaran | Aturan bisnis tidak bergantung pada abstraksi repository               |
| Akibat              | Pengujian dan penggantian penyimpanan menjadi sulit                    |

## Bagian 2 — Sebelum dan sesudah

| Aspek        | Sebelum                                   | Sesudah                                       |
| ------------ | ----------------------------------------- | --------------------------------------------- |
| Penyimpanan  | Map internal LibraryService               | `BookRepository` dan `InMemoryBookRepository` |
| Aturan denda | Kondisi bercabang                         | Strategy `AturanDenda` dan factory            |
| Perakitan    | LibraryService membuat dependensi sendiri | Constructor injection dari App/Main           |
| Pengujian    | Sulit terisolasi                          | In-memory repository dan repository palsu     |

## Bagian 3 — Bukti Open-Closed

- Berkas baru: `DendaAlumni.java` dan `DendaAlumni.php`
- Berkas lama berubah: factory saja, sedangkan `LibraryService` tidak berubah
- LibraryService tidak menyunting aturan untuk alumni.

## Bagian 4 — Bukti Dependency Inversion

- Berkas baru saat basis data: `DatabaseBookRepository`
- Berkas lama diubah: satu baris perakitan objek di App/Main
- LibraryService dan aturan bisnis tetap bergantung pada interface repository.

## Bagian 5 — Sengaja tidak direfaktor

Model `Buku` sengaja tidak diberi Factory atau Repository tambahan pada konstruktor bab. Satu kelas data sudah cukup jelas untuk skala praktikum ini; lapisan Factory pada proses pembuatan Buku akan menambah kompleksitas tanpa kebutuhan yang nyata.
