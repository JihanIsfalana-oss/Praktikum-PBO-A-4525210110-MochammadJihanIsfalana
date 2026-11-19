package id.ac.univpancasila.perpustakaan.repository;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.Map;
import java.util.stream.Collectors;

public class Repository<T> {
    private final Map<String, T> data = new LinkedHashMap<>();
    private final Function<T, String> pengambilKunci;

    public Repository(Function<T, String> pengambilKunci) {
        if (pengambilKunci == null) {
            throw new IllegalArgumentException("pengambilKunci tidak boleh null");
        }
        this.pengambilKunci = pengambilKunci;
    }

    public void simpan(T item) {
        if (item == null) {
            throw new IllegalArgumentException("item tidak boleh null");
        }
        String kunci = pengambilKunci.apply(item);
        if (kunci == null || kunci.isBlank()) {
            throw new IllegalArgumentException("kunci tidak boleh kosong");
        }
        data.put(kunci, item);
    }

    public Optional<T> cariById(String id) {
        return Optional.ofNullable(data.get(id));
    }

    public List<T> cariSemua() {
        return List.copyOf(data.values());
    }

    public boolean hapus(String id) {
        return data.remove(id) != null;
    }

    public int jumlah() {
        return data.size();
    }

    public List<T> filter(Predicate<T> predikat) {
        return data.values().stream().filter(predikat).toList();
    }

    public <R> List<R> map(Function<T, R> pemetaan) {
        return data.values().stream().map(pemetaan).toList();
    }

    public <K> Map<K, Long> kelompokkan(Function<T, K> pengelompokan) {
        return data.values().stream()
                .collect(Collectors.groupingBy(pengelompokan, Collectors.counting()));
    }
}
