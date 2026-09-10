public class Mahasiswa {
    // Atribut/field/properties
    public String nama;
    public String nim;
    public String programStudi;
    public String jadwalMataKuliah;
    public String kegiatanMhs;

    //Constructor
    Mahasiswa(String nama, String nim){
        this.nama = nama;
        this.nim = nim;
    }
    
    // Method
    public void belajar() {
        if(this.jadwalMataKuliah == null) {
            throw new IllegalArgumentException("Maaf, jadwalMataKuliah bernilai NULL!\n"); // --> jika operasi tidak sah
        }
        System.out.println(this.nama + " sedang belajar " + this.jadwalMataKuliah + " di kelas.\n");
    }
    public void kegiatan() {
        if(this.kegiatanMhs == null) {
            throw new IllegalArgumentException("Maaf, kegiatanMhs bernilai NULL!\n"); // --> jika operasi tidak sah
        }
        System.out.println(this.nama + " sedang " + this.kegiatanMhs + " bersama mahasiswa himpunan " + this.programStudi + " lainnya\n");
    }
}
