package com.sewakos.model;

// Materi 6: interface menetapkan kontrak perilaku pembayaran.
public interface DapatDibayar {
    double hitungTotal();
    void bayar(double nominal) throws Exception;
}
