package id.ac.univpancasila.perpustakaan;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

/**
 * Sesi 11 — kelas generik Anda sendiri.
 *
 * <T> adalah TYPE PARAMETER: kelas ini bekerja untuk tipe apa pun, tetapi
 * setelah dipakai sebagai Repository<Buku>, kompilator memastikan hanya Buku
 * yang bisa masuk dan keluar.
 *
 * Bandingkan tanda tangannya dengan JpaRepository<Buku, Long> yang akan Anda
 * temui di Spring Data JPA pada sesi 14. Gagasannya persis sama.
 */
public class Repository<T> {

    private final Map<String, T> data = new LinkedHashMap<>();

    /**
     * Fungsi untuk mengambil kunci dari sebuah item.
     * Dengan cara ini Repository tidak perlu tahu apa pun tentang T.
     */
    private final Function<T, String> pengambilKunci;

    public Repository(Function<T, String> pengambilKunci) {
        this.pengambilKunci = pengambilKunci;
    }

    /** TODO 1: simpan item, dengan kunci diambil lewat pengambilKunci. */
    public void simpan(T item) {
    }

    /**
     * TODO 2: kembalikan Optional yang berisi item bila ada, kosong bila tidak.
     *         JANGAN mengembalikan null.
     *         Petunjuk: Optional.ofNullable(...)
     */
    public Optional<T> cariById(String id) {
        return Optional.empty();   // ganti
    }

    /**
     * TODO 3: kembalikan SALINAN TAK-BERUBAH dari seluruh isi.
     *         Pelajaran sesi 7 tetap berlaku: koleksi internal tidak boleh bocor.
     *         Petunjuk: List.copyOf(...)
     */
    public List<T> cariSemua() {
        return List.of();   // ganti
    }

    /** TODO 4: hapus item; kembalikan true bila ada yang terhapus. */
    public boolean hapus(String id) {
        return false;   // ganti
    }

    public int jumlah() { return data.size(); }
}
