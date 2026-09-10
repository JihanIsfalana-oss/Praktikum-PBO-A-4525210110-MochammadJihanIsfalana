public class Main {
    public static void main(String[] args) {
        // Instansiasi objek valid dari rancangan/blueprint/class Mahasiswa
        Mahasiswa orang1 = new Mahasiswa("Mochammad Jihan Isfalana", "4525210110");
        Mahasiswa orang2 = new Mahasiswa("Muhammad Rasha Zein", "452521000");

        System.out.println("\n------>> Objek orang1 <<------");
        System.out.println("Nama objek orang1\t: " + orang1.nama);
        System.out.println("NIM objek orang1\t: " + orang1.nim + "\n");
        // Assignment properties/field/atribut objek orang1
        orang1.jadwalMataKuliah = "Object-Oriented Programming";
        orang1.programStudi = "Sastra Mesin";
        orang1.kegiatanMhs = "membubut piston"; // --> jika null maka operasi pada method kegiatan tidak sah objek akan menolaknya

        // Akses Method Class Mahasiswa (Operasi Sah karena ada properti jadwalMataKuliahnya tidak null)
        orang1.belajar();
        orang1.kegiatan();

        System.out.println("\n------>> Objek orang2 <<------");
        System.out.println("Nama objek orang2\t: " + orang2.nama);
        System.out.println("NIM objek orang2\t: " + orang2.nim + "\n");
        // Assignment properties/field/atribut objek orang2
        orang2.jadwalMataKuliah = null; // --> jika null maka operasi pada method belajar tidak sah objek akan menolaknya
        orang2.programStudi = "Teknik Informatika";
        orang2.kegiatanMhs = "rapat";

        // Akses Method Class Mahasiswa (Operasi tidak Sah karena jadwalMataKuliah bernilai null)
        orang2.belajar();
        orang2.kegiatan();
    }
}
