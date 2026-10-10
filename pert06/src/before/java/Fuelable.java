/**
 * Kontrak "bisa diisi bahan bakar".
 *
 * Sengaja DIPISAH dari Movable: tidak semua yang bergerak butuh bahan bakar
 * (sepeda), dan tidak semua yang butuh bahan bakar bergerak (generator).
 * Inilah Interface Segregation Principle.
 */
public interface Fuelable {
    void isiBahanBakar(double jumlah);
    double kapasitasTangki();
    TipeBahanBakar tipeBahanBakar();
}
