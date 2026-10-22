/**
 * Sesi 6 — kontrak "bisa bergerak".
 * Interface menjawab: APA YANG BISA dilakukan, bukan APA benda ini.
 */
public interface Movable {

    void bergerak();

    double kecepatanMaksimum();

    /**
     * TODO 1: default method — kembalikan ringkasan berupa
     *         "kecepatan maksimum 180 km/jam" memakai kecepatanMaksimum().
     *
     * Default method (Java 8+) menyediakan implementasi bawaan yang boleh
     * ditimpa implementornya. PHP tidak punya padanannya di interface.
     */
    default String ringkasanGerak() {
        return "(TODO 1 belum dikerjakan)";
    }
}
