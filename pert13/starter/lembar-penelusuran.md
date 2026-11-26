# Lembar Penelusuran — Laravel 13

Nama / NIM: ..........................
Proyek yang ditelusuri: `studi-kasus/perpustakaan/03-laravel/`

Isi kolom **Berkas** dan **Baris** dengan yang benar-benar Anda temukan di
proyek ini. Jangan menyalin dari modul — nomor barisnya akan diperiksa.

Kolom **Catatan Anda** diisi satu kalimat dengan bahasa Anda sendiri.

| Konsep OOP (pertemuan) | Wujudnya di Laravel | Berkas | Baris | Catatan Anda |
|---|---|---|---|---|
| Namespace & autoload (9) | PSR-4 `App\` → `app/` | | | |
| Pewarisan (4) | `class Buku extends Model` | | | |
| Enkapsulasi (2) | `$fillable` / `$hidden` | | | |
| Polimorfisme (5) | method yang Anda override, dipanggil framework | | | |
| Trait (6) | `use HasFactory, SoftDeletes` | | | |
| Enum (6) | `enum StatusBuku: string` | | | |
| Relasi objek (7) | `hasMany()` / `belongsTo()` | | | |
| Abstraksi & interface (6) | interface repository | | | |
| Exception (10) | custom exception + `render()` | | | |
| Collections (11) | `Illuminate\Support\Collection` | | | |
| Repository & SOLID (12) | Repository + Service | | | |
| Dependency Injection (13) | constructor injection di Controller | | | |
| Binding interface → implementasi (13) | Service Provider | | | |

---

## Pertanyaan lanjutan

**1. Apa yang diwarisi `Buku` dari `Model`?** Sebutkan minimal empat method
yang tersedia tanpa Anda tulis sendiri.

**2. Apa yang terjadi bila `$fillable` dikosongkan?** Coba, lalu tuliskan.

**3. Di mana objek `PeminjamanService` dibuat?** Cari — mungkin tidak ada di
mana pun yang Anda tulis. Jelaskan siapa yang membuatnya.

**4. Bandingkan dengan kode Anda sendiri di sesi 12.** Sebutkan satu hal yang
bentuknya SAMA PERSIS.
