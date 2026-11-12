# Catatan tentang kerangka sesi 11

`Repository.java` dapat dikompilasi sendiri, tetapi `RepositoryTest.java`
**belum bisa** sampai Anda menambahkan dependensi JUnit 5 ke `pom.xml`
(lihat `pom-tambahan.xml`) dan meletakkannya di `src/test/java/`.

Itu memang urutan yang benar: tambahkan dependensi lebih dulu, baru tulis ujinya.

Hal yang sama berlaku di sisi PHP: `KoleksiBukuTest.php` membutuhkan PHPUnit
(`composer require --dev phpunit/phpunit`) dan pemetaan `autoload-dev` pada
`composer.json` Anda dari sesi 9.
