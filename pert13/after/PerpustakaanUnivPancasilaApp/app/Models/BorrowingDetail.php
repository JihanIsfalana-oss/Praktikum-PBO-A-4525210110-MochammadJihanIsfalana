<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\Relations\BelongsTo;
use Illuminate\Database\Eloquent\Relations\HasOne;

class BorrowingDetail extends Model
{
    use HasFactory;

    protected $fillable = [
        'borrowing_id',
        'book_id',
        'jumlah',
        'tanggal_dikembalikan',
        'kondisi_kembali',
        'status_item',
    ];

    protected function casts(): array
    {
        return [
            'tanggal_dikembalikan' => 'date',
        ];
    }

    public function borrowing(): BelongsTo
    {
        return $this->belongsTo(Borrowing::class, 'borrowing_id');
    }

    public function book(): BelongsTo
    {
        return $this->belongsTo(Book::class, 'book_id');
    }

    public function fine(): HasOne
    {
        return $this->hasOne(Fine::class, 'borrowing_detail_id');
    }
}
