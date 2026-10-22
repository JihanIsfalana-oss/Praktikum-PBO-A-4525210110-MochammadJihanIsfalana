package com.sewakos.model;

// Materi 6: abstract class menjadi kerangka umum untuk pengguna kos.
// Materi 2: enkapsulasi diterapkan dengan atribut private dan getter.
public abstract class PenggunaKos {
    private final String nama;
    private final String noTelepon;

    // Materi 3: konstruktor memvalidasi data saat objek dibuat.
    protected PenggunaKos(String nama, String noTelepon) throws Exception {
        if (nama == null || nama.trim().isEmpty()) {
            throw new Exception("Nama tidak boleh kosong.");
        }
        if (noTelepon == null || !noTelepon.matches("08\\d{8,11}")) {
            throw new Exception("Nomor telepon harus berupa 10-13 digit dan diawali 08.");
        }
        this.nama = nama.trim();
        this.noTelepon = noTelepon.trim();
    }

    public String getNama() {
        return nama;
    }

    public String getNoTelepon() {
        return noTelepon;
    }

    // Materi 6: abstract method wajib diimplementasikan oleh subclass.
    public abstract String getPeran();
    public abstract void tampilkanInfo();
}
