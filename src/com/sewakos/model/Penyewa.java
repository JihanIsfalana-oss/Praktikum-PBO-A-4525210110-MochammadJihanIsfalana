package com.sewakos.model;

// Materi 4: Penyewa mewarisi atribut dan perilaku umum dari PenggunaKos.
public class Penyewa extends PenggunaKos {
    private final String nomorIdentitas;

    public Penyewa(String nama, String noTelepon, String nomorIdentitas) throws Exception {
        super(nama, noTelepon);
        if (nomorIdentitas == null || nomorIdentitas.trim().length() < 5) {
            throw new Exception("Nomor identitas minimal 5 karakter.");
        }
        this.nomorIdentitas = nomorIdentitas.trim();
    }

    public String getNomorIdentitas() {
        return nomorIdentitas;
    }

    // Materi 5: override menghasilkan perilaku spesifik untuk objek Penyewa.
    @Override
    public String getPeran() {
        return "Penyewa";
    }

    @Override
    public void tampilkanInfo() {
        System.out.println("Peran: " + getPeran()
                + " | Nama: " + getNama()
                + " | Telepon: " + getNoTelepon()
                + " | Identitas: " + nomorIdentitas);
    }
}
