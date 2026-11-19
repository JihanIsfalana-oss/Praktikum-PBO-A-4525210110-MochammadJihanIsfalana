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
    @Override
    public void simpan(Buku buku) {
        data.put(buku.getIsbn(), buku);
    }

    @Override
    public Optional<Buku> cariByIsbn(String isbn) {
        return Optional.ofNullable(data.get(isbn));
    }

    @Override
    public List<Buku> cariSemua() {
        return new ArrayList<>(data.values());
    }

    @Override
    public void kurangiStok(Buku buku) {
        // Implementasi untuk mengurangi stok buku
        if (buku.getStok() > 0) {
            System.out.println("Stok sebelum pengurangan: " + buku.getStok());
            System.out.println("------------------------------------------------------");
            System.out.println("Mengurangi stok buku: " + buku.getJudul());
            System.out.println("------------------------------------------------------");
            buku.setStok(buku.getStok() - 1);
        } else {
            throw new IllegalStateException("Stok buku habis");
        }
    }
}
