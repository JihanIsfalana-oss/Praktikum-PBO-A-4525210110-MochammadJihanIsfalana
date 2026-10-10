package javafile;

public class Dosen extends PegawaiTetap {

    private final String bidangKeahlian;

    public Dosen(String nip, String nama, double gajiPokok, int masaKerjaTahun, String bidangKeahlian) {
        super(nip, nama, gajiPokok, masaKerjaTahun);
        this.bidangKeahlian = bidangKeahlian;
    }

    @Override
    public String jenis() { return "DOSEN"; }

    public String getBidangKeahlian() { return bidangKeahlian; }

}
