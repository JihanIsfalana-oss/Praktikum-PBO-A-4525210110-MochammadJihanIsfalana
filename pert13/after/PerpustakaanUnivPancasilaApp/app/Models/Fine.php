<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\Relations\BelongsTo;

class Fine extends Model
{
    use HasFactory;

    protected $fillable = [
        'borrowing_detail_id',
        'hari_terlambat',
        'nominal_denda',
        'jenis_denda',
        'status_bayar',
        'tanggal_bayar',
    ];

    protected function casts(): array
    {
        return [
            'nominal_denda' => 'decimal:2',
            'tanggal_bayar' => 'datetime',
        ];
    }

    public function borrowingDetail(): BelongsTo
    {
        return $this->belongsTo(BorrowingDetail::class, 'borrowing_detail_id');
    }
}
