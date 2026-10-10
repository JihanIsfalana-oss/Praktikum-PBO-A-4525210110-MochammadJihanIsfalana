package javafile;

public class PegawaiTetap extends Pegawai {

    /** Tunjangan masa kerja: 2% gaji pokok per tahun, maksimum 40%. */
    protected static final double TUNJANGAN_PER_TAHUN = 0.02;
    protected static final double TUNJANGAN_MAKSIMUM  = 0.40;

    private final int masaKerjaTahun;

    public PegawaiTetap(String nip, String nama, double gajiPokok, int masaKerjaTahun) {
        // Baris berikut WAJIB dan harus menjadi pernyataan pertama.
        // TODO 1 (Langkah 3): hapus sementara baris ini, kompilasi,
        //         salin pesan kesalahannya ke catatan.md, lalu kembalikan.
        
        super(nip, nama, gajiPokok);

        this.masaKerjaTahun = masaKerjaTahun;
    }

    /**
     * TODO 2: hitung gaji = gaji dasar induk + tunjangan masa kerja.
     *
     * PENTING: panggil super.hitungGaji() untuk memperoleh gaji dasar.
     *          JANGAN menyalin rumus induk ke sini — itu yang dinilai.
     */
    @Override
    public double hitungGaji() {
        return super.hitungGaji() * (1 + Math.min(masaKerjaTahun * TUNJANGAN_PER_TAHUN, TUNJANGAN_MAKSIMUM));
    }

    @Override
    public String jenis() { return "TETAP"; }

    protected int getMasaKerjaTahun() { return masaKerjaTahun; }
}
