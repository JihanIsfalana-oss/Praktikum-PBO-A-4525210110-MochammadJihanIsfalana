<?php
declare(strict_types=1);

namespace Perpustakaan\Tests;

use PHPUnit\Framework\TestCase;
use Perpustakaan\Exception\BukuTidakDitemukanException;
use Perpustakaan\Model\Buku;
use Perpustakaan\Model\KoleksiBuku;
use Perpustakaan\Service\LibraryService;
use Perpustakaan\Repository\InMemoryBookRepository;
use Perpustakaan\Denda\DendaBebas;
use Perpustakaan\Repository\BookRepository;
use RuntimeException;

final class KoleksiBukuTest extends TestCase
{
    private function buatService(): LibraryService
    {
        return new LibraryService(new InMemoryBookRepository(), new DendaBebas());
    }

    public function testLibraryServiceMeneruskanKegagalanRepositoryPalsu(): void
    {
        $repositoryPalsu = new class implements BookRepository {
            public function simpan(Buku $buku): void {}
            public function cariByIsbn(string $isbn): ?Buku
            {
                throw new RuntimeException('Repository palsu selalu gagal');
            }
            public function cariSemua(): array { return []; }
            public function kurangiStok(Buku $buku): void {}
        };
        $service = new LibraryService($repositoryPalsu, new DendaBebas());
        $service->daftarkanAnggota('A-001');
        $this->expectException(RuntimeException::class);
        $service->pinjam('A-001', '999');
    }

    private KoleksiBuku $koleksi;

    protected function setUp(): void
    {
        $this->koleksi = new KoleksiBuku();
    }

    public function testItemYangDisimpanDapatDitemukanKembali(): void
    {
        $this->koleksi->simpan(new Buku('978-1', 'PBO', 'Jihan', 2));
        $buku = $this->koleksi->cariByIsbn('978-1');
        self::assertNotNull($buku);
        self::assertSame('PBO', $buku->getJudul());
    }

    public function testMencariIsbnYangTidakAdaMengembalikanNull(): void
    {
        self::assertNull($this->koleksi->cariByIsbn('tidak-ada'));
    }

    public function testKoleksiBaruBerisiNolItem(): void
    {
        self::assertCount(0, $this->koleksi);
    }

    public function testFilterHanyaMengembalikanYangLolosPredikat(): void
    {
        $this->koleksi->simpan(new Buku('1', 'Ada', 'A', 1));
        $this->koleksi->simpan(new Buku('2', 'Habis', 'B', 0));
        $hasil = $this->koleksi->filter(static fn(Buku $b): bool => $b->tersedia());
        self::assertCount(1, $hasil);
        self::assertSame('Ada', $hasil->cariByIsbn('1')?->getJudul());
    }

    public function testMapMengembalikanDaftarJudul(): void
    {
        $this->koleksi->simpan(new Buku('1', 'PBO', 'A', 1));
        $this->koleksi->simpan(new Buku('2', 'Java', 'B', 2));
        self::assertSame(['PBO', 'Java'], $this->koleksi->map(
            static fn(Buku $b): string => $b->getJudul()
        ));
    }

    public function testJumlahkanMenghitungTotalDenganBenar(): void
    {
        $this->koleksi->simpan(new Buku('1', 'A', 'X', 2));
        $this->koleksi->simpan(new Buku('2', 'B', 'Y', 3));
        self::assertSame(5.0, $this->koleksi->jumlahkan(
            static fn(Buku $b): int => $b->getJumlahEksemplar()
        ));
    }

    public function testKunciYangSamaMenimpaBuku(): void
    {
        $this->koleksi->simpan(new Buku('1', 'Lama', 'A', 1));
        $this->koleksi->simpan(new Buku('1', 'Baru', 'A', 2));
        self::assertCount(1, $this->koleksi);
        self::assertSame('Baru', $this->koleksi->cariByIsbn('1')?->getJudul());
    }

    public function testPinjamIsbnYangTidakAdaMelemparException(): void
    {
        $service = $this->buatService();
        $service->daftarkanAnggota('A-001');
        $this->expectException(BukuTidakDitemukanException::class);
        $service->pinjam('A-001', '999');
    }
}

