/**
 * Sesi 3 — constructor berdelegasi, anggota statis, dan konstanta.
 *
 * Invariant:
 *   1. saldo tidak pernah negatif
 *   2. nomor rekening tidak berubah setelah objek dibuat
 *   3. setoran dan penarikan selalu bernilai positif
 */
public class RekeningBank {

    // TODO 1: ganti tiga angka ajaib berikut menjadi konstanta bernama
    //         (public static final). Setelah itu, tidak boleh ada lagi
    //         angka literal di dalam badan method.
    //   - bunga tahunan          : 0.025
    //   - biaya administrasi     : 5000
    //   - batas penarikan sekali : 5000000
    public static final double bungaTahunan = 0.025d;
    public static final double biayaAdministrasi = 5000d;
    public static final double batasPenarikanSekali = 5000000d;


    // TODO 2: deklarasikan field statis penghitung jumlah rekening.
    //         Perhatikan: static, privat, dan bernilai awal 0.
    private static int jumlahRekening = 0;


    private final String nomor;
    private final String pemilik;
    private double saldo;

    /**
     * Constructor ringkas.
     * TODO 3: DELEGASIKAN ke constructor lengkap dengan this(...).
     *         Jangan menyalin validasi ke sini.
     */
    public RekeningBank(String nomor, String pemilik) {
        // TODO 3 — ganti baris di bawah dengan delegasi
        this(nomor, pemilik, 0.0);
    }

    /** Constructor lengkap — SATU-SATUNYA tempat validasi berada. */
    public RekeningBank(String nomor, String pemilik, double saldoAwal) {
        // TODO 4: tolak nomor kosong dan saldo awal negatif.
        if(nomor == null || nomor.trim().isEmpty()) {
            throw new NullPointerException("Nomor Rekening tidak boleh Kosong !\n");
        }
        if(saldoAwal < 0) {
            throw new IllegalArgumentException("Saldo Awal tidak boleh Negatif !\n");
        }

        this.nomor = nomor;
        this.pemilik = pemilik;
        this.saldo = saldoAwal;

        // TODO 5: naikkan penghitung jumlah rekening DI SINI SAJA.
        //         Pikirkan mengapa bukan di kedua constructor.
        RekeningBank.jumlahRekening++; // Karena Constructor yang lain memanggil Constructor ini sehingga memicu penghitung jumlah rekening disini
    }

    public void setor(double jumlah) {
        // TODO 6: tolak jumlah <= 0, lalu tambahkan ke saldo.
        if(jumlah <= 0) {
            throw new IllegalArgumentException("Jumlah Penyetoran Tunai tidak boleh 0 ataupun Negatif !\n");
        }

        this.saldo += jumlah;
        System.out.println("[SUKSES] Setor :" + jumlah + " ke Rekening : " + this.nomor);
        System.out.println(toString() + "\n");
    }

    public void tarik(double jumlah) {
        // TODO 7: tolak jumlah <= 0, tolak jika melebihi saldo,
        //         dan tolak jika melebihi batas penarikan sekali transaksi.
        if(jumlah <= 0) {
            throw new IllegalArgumentException("Jumlah Penarikan tidak boleh 0 ataupun Negatif !\n");
        } else if(jumlah > this.saldo) {
            throw new IllegalArgumentException("Jumlah Penarikan Melebihi Saldo yang Anda Miliki ! \n");
        } else if(jumlah > batasPenarikanSekali) {
            throw new IllegalArgumentException("Jumlah Penarikan Melebihi Batas Penarikan Sekali Transaksi !\n");
        }

        this.saldo -= jumlah;
        System.out.println("[SUKSES] Tarik :" + jumlah + " dari Rekening : " + this.nomor);
        System.out.println(toString() + "\n");
    }

    /** TODO 8: kurangi saldo sebesar biaya administrasi, tetapi jangan sampai negatif. */
    public void potongBiayaAdmin() {
        if(this.saldo - biayaAdministrasi >= 0){
            this.saldo -= biayaAdministrasi;
        }

    }

    /** TODO 9: method statis — kembalikan jumlah rekening yang pernah dibuat. */
    public static int getJumlahRekening() {
        return RekeningBank.jumlahRekening;   // ganti
    }

    /**
     * TODO 10: method statis utilitas — hitung bunga setahun dari pokok.
     *          Perhatikan: method ini tidak membaca keadaan objek mana pun.
     *          Itulah alasan ia pantas menjadi static.
     */
    public static double bungaSetahun(double pokok) {
        return pokok * (RekeningBank.bungaTahunan);   // ganti
    }

    public double getSaldo()  { return saldo; }
    public String getNomor()  { return nomor; }

    @Override
    public String toString() {
        return String.format("Rekening[%s] %-14s Rp%,.2f", nomor, pemilik, this.saldo);
    }
}
