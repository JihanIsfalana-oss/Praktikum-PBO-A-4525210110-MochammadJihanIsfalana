<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

return new class extends Migration
{
    public function up(): void
    {
        Schema::create('fines', function (Blueprint $table) {
            $table->id();
            $table->foreignId('borrowing_detail_id')->constrained('borrowing_details')->cascadeOnDelete();
            $table->unsignedInteger('hari_terlambat')->default(0);
            $table->decimal('nominal_denda', 10, 2)->default(0.00);
            $table->enum('jenis_denda', ['keterlambatan', 'kerusakan', 'hilang'])->default('keterlambatan');
            $table->enum('status_bayar', ['belum_lunas', 'lunas'])->default('belum_lunas');
            $table->timestamp('tanggal_bayar')->nullable();
            $table->timestamps();
        });
    }

    public function down(): void
    {
        Schema::dropIfExists('fines');
    }
};
