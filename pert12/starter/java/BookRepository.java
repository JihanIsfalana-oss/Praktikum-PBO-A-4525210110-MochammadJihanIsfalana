package id.ac.univpancasila.perpustakaan.repository;

import id.ac.univpancasila.perpustakaan.model.Buku;

import java.util.List;
import java.util.Optional;

/**
 * Sesi 12 — REPOSITORY PATTERN.
 *
 * Interface ini menyembunyikan CARA data disimpan dari logika bisnis
 * yang memakainya. LibraryService cukup tahu kontraknya.
 *
 * Akibat langsungnya: aturan bisnis bisa diuji dengan implementasi
 * in-memory, tanpa basis data sama sekali.
 *
 * Pada sesi 13 Anda akan menemukan interface yang sangat mirip di Laravel,
 * dan pada sesi 14 di Spring Data JPA — di sana implementasinya bahkan
 * dibuatkan otomatis oleh framework.
 */
public interface BookRepository {

    // TODO 1: rancang tanda tangan method yang DIBUTUHKAN LibraryService.
    //         Jangan menambahkan method yang tidak dipakai — itu pelanggaran
    //         Interface Segregation Principle.
    //
    // Saran minimal:
    //   void simpan(Buku buku);
    //   Optional<Buku> cariByIsbn(String isbn);
    //   List<Buku> cariSemua();
    //   void kurangiStok(Buku buku);
}
