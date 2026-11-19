# Catatan Uji Pertemuan 11

Kasus tersulit bagi saya adalah uji exception pada `pinjam()` karena uji harus menyiapkan keadaan awal anggota, memanggil skenario ISBN yang tidak terdaftar, dan memastikan exception domain yang tepat tanpa sekadar memeriksa pesan teks. Solusinya adalah membuat LibraryService bersih melalui setup, mendaftarkan anggota, lalu memakai `assertThrows` atau `expectException`.
