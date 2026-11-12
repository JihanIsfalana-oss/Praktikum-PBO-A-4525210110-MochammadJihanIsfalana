<?php
declare(strict_types=1);

namespace Perpustakaan\Tests;

use PHPUnit\Framework\TestCase;
use Perpustakaan\Model\Buku;
use Perpustakaan\Model\KoleksiBuku;

/**
 * Sesi 11 — unit test PHPUnit.
 *
 * Letakkan di tests/ dan pastikan autoload-dev pada composer.json sudah
 * memetakan Perpustakaan\Tests\ ke tests/.
 *
 * Jalankan: ./vendor/bin/phpunit tests
 */
final class KoleksiBukuTest extends TestCase
{
    private KoleksiBuku $koleksi;

    protected function setUp(): void
    {
        // Dijalankan ulang sebelum SETIAP pengujian.
        $this->koleksi = new KoleksiBuku();
    }

    public function testItemYangDisimpanDapatDitemukanKembali(): void
    {
        // TODO 1
        $this->fail('TODO 1 belum dikerjakan');
    }

    public function testMencariIsbnYangTidakAdaMengembalikanNull(): void
    {
        // TODO 2
        $this->fail('TODO 2 belum dikerjakan');
    }

    public function testKoleksiBaruBerisiNolItem(): void
    {
        // TODO 3: perhatikan bahwa count($this->koleksi) bekerja
        //         karena kelasnya implements Countable.
        $this->fail('TODO 3 belum dikerjakan');
    }

    public function testFilterHanyaMengembalikanYangLolosPredikat(): void
    {
        // TODO 4
        $this->fail('TODO 4 belum dikerjakan');
    }

    public function testJumlahkanMenghitungTotalDenganBenar(): void
    {
        // TODO 5
        $this->fail('TODO 5 belum dikerjakan');
    }

    // TODO 6: terjemahkan minimal LIMA skenario dari skenario-uji.md sesi 10
    //         menjadi kasus uji di sini, termasuk satu yang memastikan
    //         exception yang benar dilemparkan.
    //         Petunjuk: $this->expectException(BukuTidakDitemukanException::class);
}
