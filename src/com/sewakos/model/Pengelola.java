package com.sewakos.model;

// Materi 4: Pengelola adalah subclass lain dari abstract class PenggunaKos.
public class Pengelola extends PenggunaKos {
    private final String kodePengelola;

    public Pengelola(String nama, String noTelepon, String kodePengelola) throws Exception {
        super(nama, noTelepon);
        if (kodePengelola == null || !kodePengelola.matches("PG-[A-Z0-9]{3,8}")) {
            throw new Exception("Kode pengelola harus berformat PG- diikuti 3-8 huruf/angka.");
        }
        this.kodePengelola = kodePengelola;
    }

    public String getKodePengelola() {
        return kodePengelola;
    }

    // Materi 5: method yang sama dengan Penyewa, tetapi implementasinya berbeda.
    @Override
    public String getPeran() {
        return "Pengelola";
    }

    @Override
    public void tampilkanInfo() {
        System.out.println("Peran: " + getPeran()
                + " | Nama: " + getNama()
                + " | Telepon: " + getNoTelepon()
                + " | Kode: " + kodePengelola);
    }
}
