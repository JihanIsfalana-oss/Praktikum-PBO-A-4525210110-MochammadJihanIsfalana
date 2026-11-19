package id.ac.univpancasila.perpustakaan.repository;

import id.ac.univpancasila.perpustakaan.model.Buku;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Implementasi in-memory — untuk latihan dan PENGUJIAN.
 *
 * Keberadaan implementasi kedua inilah yang membenarkan pembuatan interface
 * BookRepository. Interface dengan satu implementasi yang tidak akan pernah
 * bertambah adalah abstraksi spekulatif — dan itu pemborosan.
 */
public class InMemoryBookRepository implements BookRepository {

    private final Map<String, Buku> data = new LinkedHashMap<>();

    // TODO 1: implementasikan seluruh method dari BookRepository.
    //         Perhatikan: cariSemua() harus mengembalikan salinan tak-berubah.
}
