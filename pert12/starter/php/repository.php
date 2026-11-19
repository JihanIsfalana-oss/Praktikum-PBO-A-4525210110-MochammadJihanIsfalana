<?php
declare(strict_types=1);

namespace Perpustakaan;

use Perpustakaan\Model\Buku;

/**
 * Sesi 12 — Repository dan Strategy (PHP).
 * Bandingkan dengan versi Java; strukturnya nyaris identik.
 */

// ══ REPOSITORY ═══════════════════════════════════════════════
interface BookRepository
{
    // TODO 1: rancang tanda tangan method yang DIBUTUHKAN LibraryService.
    //   public function simpan(Buku $buku): void;
    //   public function cariByIsbn(string $isbn): ?Buku;
    //   /** @return Buku[] */
    //   public function cariSemua(): array;
    //   public function kurangiStok(Buku $buku): void;
}

final class InMemoryBookRepository implements BookRepository
{
    /** @var array<string,Buku> */
    private array $data = [];

    // TODO 2: implementasikan seluruh method dari BookRepository.
}

// ══ STRATEGY ═════════════════════════════════════════════════
interface AturanDenda
{
    public function hitung(int $hariTerlambat): float;
    public function nama(): string;
}

// TODO 3: buat DendaMahasiswa (1000/hari), DendaDosen (500/hari),
//         dan DendaBebas (selalu 0).

// TODO 4: buat AturanDendaFactory::untuk(string $jenisAnggota): AturanDenda
//         Petunjuk: match (strtolower($jenisAnggota)) { ... }

// ══ LAYANAN ══════════════════════════════════════════════════
final class LibraryService
{
    /**
     * TODO 5: terima BookRepository DAN AturanDenda lewat constructor.
     *
     * ATURAN: setelah refactoring, tidak boleh ada satu pun `new` di dalam
     *         kelas ini untuk urusan penyimpanan maupun aturan denda.
     *         Kedua dependensi itu DATANG DARI LUAR.
     *
     * Bentuk yang diharapkan:
     *   public function __construct(
     *       private readonly BookRepository $repo,
     *       private readonly AturanDenda    $aturanDenda,
     *   ) {}
     *
     * Perhatikan baik-baik bentuk ini. Pada sesi 13 Anda akan menemukan
     * constructor yang sama persis di dalam Laravel — bedanya, di sana
     * Service Container yang mengisinya untuk Anda.
     */

    // TODO 6: pindahkan method-method aturan bisnis dari LibraryService
    //         hasil sesi 10 ke sini, dan buat semuanya memakai $this->repo.
}
