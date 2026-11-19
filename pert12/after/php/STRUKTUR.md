# Struktur proyek Composer

```
sesi-09/php/
├── composer.json
├── composer.lock          <- WAJIB di-commit
├── main.php               <- titik masuk
├── src/
│   ├── Model/
│   │   ├── Buku.php       -> namespace Perpustakaan\Model;
│   │   ├── Anggota.php
│   │   └── Peminjaman.php
│   ├── Service/
│   │   └── PerpustakaanService.php
│   └── Util/
│       └── Format.php
├── tests/                 <- dipakai sesi 11
└── vendor/                <- JANGAN di-commit
```

## Cara kerja autoload PSR-4

Dengan pemetaan `"Perpustakaan\\": "src/"`, Composer menerjemahkan:

```
Perpustakaan\Model\Buku   →   src/Model/Buku.php
Perpustakaan\Util\Format  →   src/Util/Format.php
```

Setelah itu, seluruh `require_once` di kode Anda dapat DIHAPUS. Cukup satu
baris di titik masuk:

```php
require __DIR__ . '/vendor/autoload.php';
```

Inilah mekanisme yang membuat Laravel dapat menemukan Model dan Controller
Anda tanpa Anda mendaftarkannya satu per satu.

## Perhatikan huruf besar-kecil

Di macOS dan Windows, nama berkas tidak peka huruf besar-kecil, sehingga
`src/model/buku.php` mungkin tetap berjalan di perangkat Anda. Di server
Linux, itu akan GAGAL. Biasakan menulis nama folder persis seperti namespace-nya:
`src/Model/Buku.php`.

## Perintah

```bash
composer install                 # pasang sesuai composer.lock
composer update                  # perbarui dan tulis ulang composer.lock
composer require <paket>          # tambah dependensi
composer dump-autoload            # bangun ulang peta autoload
```
