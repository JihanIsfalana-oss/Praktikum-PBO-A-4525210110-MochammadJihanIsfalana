<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

return new class extends Migration
{
    public function up(): void
    {
        Schema::create('books', function (Blueprint $table) {
            $table->id();
            $table->foreignId('category_id')->constrained('categories')->restrictOnDelete();
            $table->foreignId('publisher_id')->constrained('publishers')->restrictOnDelete();
            $table->string('kode_buku', 30)->unique();
            $table->string('isbn', 20)->unique();
            $table->string('judul');
            $table->string('penulis');
            $table->year('tahun_terbit');
            $table->string('lokasi_rak', 50)->nullable();
            $table->unsignedInteger('stok_total')->default(0);
            $table->unsignedInteger('stok_tersedia')->default(0);
            $table->string('cover_image')->nullable();
            $table->text('sinopsis')->nullable();
            $table->timestamps();
        });
    }

    public function down(): void
    {
        Schema::dropIfExists('books');
    }
};
