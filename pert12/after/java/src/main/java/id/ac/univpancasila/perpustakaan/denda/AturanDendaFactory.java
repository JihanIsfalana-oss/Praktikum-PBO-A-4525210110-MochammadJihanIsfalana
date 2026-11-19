package id.ac.univpancasila.perpustakaan.denda;

public final class AturanDendaFactory {
    
    private AturanDendaFactory() {

    }

    public static AturanDenda untuk(String jenisAnggota) {
        switch (jenisAnggota.toLowerCase()) {
            case "mahasiswa":
                return new DendaMahasiswa();
            case "dosen":
                return new DendaDosen();
            case "kehormatan":
            case "alumni":
                return new DendaAlumni();
            case "bebas":
                return new DendaBebas();
            default:
                throw new IllegalArgumentException("Jenis anggota tidak dikenal: " + jenisAnggota);
        }
    }
}
