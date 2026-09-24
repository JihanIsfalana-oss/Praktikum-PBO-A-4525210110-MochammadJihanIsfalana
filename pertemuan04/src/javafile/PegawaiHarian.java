package javafile;

public class PegawaiHarian extends Pegawai {

    private final int jamKerja;
    private final double upahPerJam;

    public PegawaiHarian(String nip, String nama, double upahPerJam, int jamKerja) {
        super(nip, nama, 0);
        this.upahPerJam = upahPerJam;
        this.jamKerja = jamKerja;
    }

    @Override
    public double hitungGaji() {
        return upahPerJam * jamKerja;
    }

    @Override
    public String jenis() { return "HARIAN"; }

    protected int getJamKerja() { return jamKerja; }

}
