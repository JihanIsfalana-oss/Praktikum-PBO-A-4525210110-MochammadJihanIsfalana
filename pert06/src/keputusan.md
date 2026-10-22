# **Mengapa Penolakan Saat Kompilasi Menguntungkan?**

- **Type Safety:** Menangkap kesalahan logika atau tipe data lebih awal sebelum program dijalankan (runtime).

- **Mencegah Crash:** Menghindari kegagalan program di lingkungan produksi (seperti ClassCastException).

- **Feedback Cepat:** Developer langsung mengetahui batas kemampuan suatu objek tanpa perlu melakukan manual testing.

## **Output Exception**

## JAVA OUTPUT

```output
$ javac Main.java
Main.java:36: error: incompatible types: Sepeda cannot be converted to Fuelable
        isiPenuh(sepeda);
                 ^
Note: Some messages have been simplified; recompile with -Xdiags:verbose to get full output
1 error
```

---

## PHP OUTPUT

```output
[10:43:14] Mobil: mengisi bahan bakar
  Diisi penuh Bensin — biaya Rp540.000
PHP Fatal error:  Uncaught TypeError: isiPenuh(): Argument #1 ($kendaraan) must be of type Fuelable, Sepeda given, called in D:\Praktikum_PBO_A_MochammadJihanIsfalana_4525210110\pertemuan06\src\starter\php\main.php on line 31 and defined in D:\Praktikum_PBO_A_MochammadJihanIsfalana_4525210110\pertemuan06\src\starter\php\main.php:7
Stack trace:
#0 D:\Praktikum_PBO_A_MochammadJihanIsfalana_4525210110\pertemuan06\src\starter\php\main.php(31): isiPenuh(Object(Sepeda))
#1 {main}
  thrown in D:\Praktikum_PBO_A_MochammadJihanIsfalana_4525210110\pertemuan06\src\starter\php\main.php on line 7

Fatal error: Uncaught TypeError: isiPenuh(): Argument #1 ($kendaraan) must beof type Fuelable, Sepeda given, called in D:\Praktikum_PBO_A_MochammadJihanIsfalana_4525210110\pertemuan06\src\starter\php\main.php on line 31 and defined in D:\Praktikum_PBO_A_MochammadJihanIsfalana_4525210110\pertemuan06\src\starter\php\main.php:7
Stack trace:
#0 D:\Praktikum_PBO_A_MochammadJihanIsfalana_4525210110\pertemuan06\src\starter\php\main.php(31): isiPenuh(Object(Sepeda))
#1 {main}
  thrown in D:\Praktikum_PBO_A_MochammadJihanIsfalana_4525210110\pertemuan06\src\starter\php\main.php on line 7
```
