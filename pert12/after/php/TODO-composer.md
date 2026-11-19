    # TODO untuk composer.json

    Berkas `composer.json` di folder ini sengaja dibiarkan dengan pemetaan autoload
    KOSONG. Lengkapi sendiri — inilah inti sesi 9 di sisi PHP.

    ## TODO 1 — pemetaan autoload

    Isi bagian `autoload.psr-4` sehingga namespace `Perpustakaan\` menunjuk ke
    folder `src/`.

    Perhatikan dua hal yang sering salah:

    - Di dalam JSON, backslash harus ditulis **ganda**: `"Perpustakaan\\"`.
    - Nama namespace diakhiri backslash; jalur folder diakhiri garis miring: `"src/"`.

    ## TODO 2 — pemetaan autoload untuk pengujian

    Isi `autoload-dev.psr-4` sehingga `Perpustakaan\Tests\` menunjuk ke `tests/`.
    Bagian ini baru dipakai pada sesi 11, tetapi lebih baik disiapkan sekarang.

    Bedanya dengan `autoload`: `autoload-dev` hanya dimuat saat pengembangan dan
    pengujian, tidak ikut saat aplikasi dipasang di server produksi.

    ## TODO 3 — dependensi eksternal

    Tambahkan satu dependensi yang benar-benar Anda pakai. Saran:

    ```bash
    composer require nesbot/carbon
    ```

    Lalu pakai untuk menghitung tanggal jatuh tempo peminjaman, menggantikan
    aritmetika tanggal manual.

    ## Memeriksa hasil pekerjaan Anda

    ```bash
    composer validate        # memeriksa apakah composer.json sah
    composer install
    php main.php
    ```

    Jika `composer validate` mengeluh, bacalah pesannya sampai selesai — pesan
    Composer biasanya menyebutkan persis bagian mana yang salah.
