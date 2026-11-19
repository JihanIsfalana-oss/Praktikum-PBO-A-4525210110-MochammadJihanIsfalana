<?php
declare(strict_types=1);

namespace Perpustakaan\Denda;

use InvalidArgumentException;

// TODO 4: buat AturanDendaFactory::untuk(string $jenisAnggota): AturanDenda
final class AturanDendaFactory
{
    public static function untuk(string $jenisAnggota): AturanDenda
    {
        return match (strtolower(trim($jenisAnggota))) {
            'mahasiswa' => new DendaMahasiswa(),
            'dosen' => new DendaDosen(),
            'kehormatan', 'bebas' => new DendaBebas(),
            'alumni' => new DendaAlumni(),
            default => throw new InvalidArgumentException("Jenis anggota tidak dikenal: {$jenisAnggota}"),
        };
    }
}