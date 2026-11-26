<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Relations\HasMany;
use Illuminate\Foundation\Auth\User as Authenticatable;
use Illuminate\Notifications\Notifiable;
use Laravel\Sanctum\HasApiTokens;

class User extends Authenticatable
{
    use HasApiTokens, HasFactory, Notifiable;

    protected $fillable = [
        'nomor_induk',
        'name',
        'email',
        'password',
        'role',
        'fakultas',
        'program_studi',
        'no_telepon',
        'alamat',
        'status_aktif',
    ];

    protected $hidden = [
        'password',
        'remember_token',
    ];

    protected function casts(): array
    {
        return [
            'email_verified_at' => 'datetime',
            'password' => 'hashed',
            'status_aktif' => 'boolean',
        ];
    }

    // Relasi peminjaman sebagai anggota (mahasiswa/dosen)
    public function borrowings(): HasMany
    {
        return $this->hasMany(Borrowing::class, 'user_id');
    }

    // Relasi peminjaman yang diproses sebagai petugas/pustakawan
    public function processedBorrowings(): HasMany
    {
        return $this->hasMany(Borrowing::class, 'petugas_id');
    }
}
