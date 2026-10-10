<?php
declare(strict_types=1);

abstract class BangunDatar
{
    public function __construct(private readonly string $nama) {}

    abstract public function luas(): float;
    abstract public function keliling(): float;

    public function getNama(): string { return $this->nama; }

    public function __toString(): string
    {
        return sprintf('%-12s luas=%10.2f  keliling=%10.2f',
            $this->nama, $this->luas(), $this->keliling());
    }
}

class Lingkaran extends BangunDatar
{
    public function __construct(private readonly float $jariJari)
    {
        parent::__construct('Lingkaran');
        // TODO 1: tolak jari-jari <= 0.
        if ($jariJari <= 0) {
            throw new InvalidArgumentException('Jari-jari harus lebih besar dari 0.');
        }
    }

    // TODO 2: lengkapi. Gunakan M_PI, bukan 3.14.
    public function luas(): float     {
        return M_PI * $this->jariJari * $this->jariJari;
    }
    public function keliling(): float {
        return 2 * M_PI * $this->jariJari;
    }

    public function getJariJari(): float { return $this->jariJari; }
}

class Persegi extends BangunDatar
{
    public function __construct(private readonly float $sisi)
    {
        parent::__construct('Persegi');
        // TODO 1: tolak sisi <= 0.
        if ($sisi <= 0) {
            throw new InvalidArgumentException('Sisi harus lebih besar dari 0.');
        }
    }

    // TODO 2: lengkapi.
    public function luas(): float     {
         return $this->sisi * $this->sisi; }
    public function keliling(): float {
         return 4 * $this->sisi; }
}

// TODO Langkah 2: buat kelas Segitiga (tiga sisi, rumus Heron).
//                 Tolak konstruksi bila ketiga sisi tidak membentuk segitiga.
// TODO Langkah 4: buat kelas Trapesium.
class Segitiga extends BangunDatar
{
    public function __construct(private readonly float $a, private readonly float $b, private readonly float $c)
    {
        parent::__construct('Segitiga');
        // Tolak konstruksi bila ketiga sisi tidak membentuk segitiga.
        if ($a <= 0 || $b <= 0 || $c <= 0) {
            throw new InvalidArgumentException('Sisi harus lebih besar dari 0.');
        }
        if ($a + $b <= $c || $a + $c <= $b || $b + $c <= $a) {
            throw new InvalidArgumentException('Ketiga sisi tidak membentuk segitiga.');
        }
    }

    public function luas(): float
    {
        // Rumus Heron
        $s = ($this->a + $this->b + $this->c) / 2;
        return sqrt($s * ($s - $this->a) * ($s - $this->b) * ($s - $this->c));
    }

    public function keliling(): float
    {
        return $this->a + $this->b + $this->c;
    }
}

class Trapesium extends BangunDatar
{
    public function __construct(private readonly float $a, private readonly float $b, private readonly float $tinggi)
    {
        parent::__construct('Trapesium');
        // Tolak konstruksi bila sisi <= 0.
        if ($a <= 0 || $b <= 0 || $tinggi <= 0) {
            throw new InvalidArgumentException('Sisi dan tinggi harus lebih besar dari 0.');
        }
    }

    public function luas(): float
    {
        return (($this->a + $this->b) / 2) * $this->tinggi;
    }

    public function keliling(): float
    {
        // Asumsikan trapesium sama kaki untuk menghitung keliling.
        $sisiMiring = sqrt(pow(($this->b - $this->a) / 2, 2) + pow($this->tinggi, 2));
        return $this->a + $this->b + (2 * $sisiMiring);
    }
}