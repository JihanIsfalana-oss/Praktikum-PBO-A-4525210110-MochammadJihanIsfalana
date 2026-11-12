<?php

declare(strict_types=1);

namespace Perpustakaan\Model;

use Carbon\Carbon;
use Perpustakaan\Util\Format;

/**
 * Merepresentasikan satu transaksi peminjaman buku di perpustakaan.
 */
final class Peminjaman
{
    private readonly Carbon $tanggalPinjam;

    private ?Carbon $tanggalKembali = null;

    public function __construct(
        private readonly Anggota $anggota,
        private readonly Buku $buku,
        ?Carbon $tanggalPinjam = null
    ) {
        // Salin objek tanggal agar perubahan dari luar tidak mengubah transaksi.
        $this->tanggalPinjam = $tanggalPinjam?->copy() ?? Carbon::now();

        // Stok hanya dikurangi ketika transaksi peminjaman berhasil dibuat.
        $this->buku->kurangiStok();
    }

    public function getAnggota(): Anggota
    {
        return $this->anggota;
    }

    public function getBuku(): Buku
    {
        return $this->buku;
    }

    public function getTanggalPinjam(): Carbon
    {
        return $this->tanggalPinjam->copy();
    }

    public function getTanggalKembali(): ?Carbon
    {
        return $this->tanggalKembali?->copy();
    }

    /**
     * Mengembalikan buku dan menambahkan kembali stoknya.
     *
     * @param Carbon|null $tanggalKembali Tanggal pengembalian. Jika null,
     *                                    tanggal saat ini yang digunakan.
     */
    public function kembalikan(?Carbon $tanggalKembali = null): void
    {
        if ($this->tanggalKembali !== null) {
            throw new \LogicException('Buku pada transaksi ini sudah dikembalikan.');
        }

        $tanggalPengembalian = $tanggalKembali?->copy() ?? Carbon::now();

        if ($tanggalPengembalian->isBefore($this->tanggalPinjam)) {
            throw new \InvalidArgumentException(
                'Tanggal pengembalian tidak boleh lebih awal dari tanggal peminjaman.'
            );
        }

        // Ubah status transaksi dan stok setelah seluruh validasi lolos.
        $this->tanggalKembali = $tanggalPengembalian;
        $this->buku->tambahStok();
    }

    public function sudahDikembalikan(): bool
    {
        return $this->tanggalKembali !== null;
    }

    public function getStatus(): string
    {
        return $this->sudahDikembalikan() ? 'Dikembalikan' : 'Dipinjam';
    }

    public function __toString(): string
    {
        return sprintf(
            'Buku: %s | Pinjam: %s | Kembali: %s | Status: %s',
            $this->buku->getJudul(),
            Format::tanggal($this->tanggalPinjam),
            Format::tanggal($this->tanggalKembali),
            $this->getStatus()
        );
    }
}
