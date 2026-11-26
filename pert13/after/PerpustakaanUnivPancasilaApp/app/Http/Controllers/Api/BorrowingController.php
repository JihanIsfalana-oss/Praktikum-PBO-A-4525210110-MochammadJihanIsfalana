<?php

namespace App\Http\Controllers\Api;

use App\Http\Controllers\Controller;
use App\Models\Book;
use App\Models\Borrowing;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\DB;
use Illuminate\Support\Str;

class BorrowingController extends Controller
{
    public function index()
    {
        $borrowings = Borrowing::with(['user', 'petugas', 'details.book', 'details.fine'])
            ->latest()
            ->get();

        return response()->json([
            'success' => true,
            'data' => $borrowings,
        ]);
    }

    public function store(Request $request)
    {
        $validated = $request->validate([
            'user_id' => 'required|exists:users,id',
            'tanggal_pinjam' => 'required|date',
            'tanggal_jatuh_tempo' => 'required|date|after_or_equal:tanggal_pinjam',
            'catatan' => 'nullable|string',
            'items' => 'required|array|min:1',
            'items.*.book_id' => 'required|exists:books,id',
            'items.*.jumlah' => 'required|integer|min:1',
        ]);

        return DB::transaction(function () use ($validated, $request) {
            $kode = 'PJM-UP-' . now()->format('Ymd') . '-' . strtoupper(Str::random(4));

            $borrowing = Borrowing::create([
                'kode_peminjaman' => $kode,
                'user_id' => $validated['user_id'],
                'petugas_id' => $request->user()?->id,
                'tanggal_pinjam' => $validated['tanggal_pinjam'],
                'tanggal_jatuh_tempo' => $validated['tanggal_jatuh_tempo'],
                'status' => 'dipinjam',
                'catatan' => $validated['catatan'] ?? null,
            ]);

            foreach ($validated['items'] as $item) {
                $book = Book::findOrFail($item['book_id']);

                if ($book->stok_tersedia < $item['jumlah']) {
                    abort(422, "Stok buku '{$book->judul}' tidak mencukupi.");
                }

                $borrowing->details()->create([
                    'book_id' => $book->id,
                    'jumlah' => $item['jumlah'],
                    'status_item' => 'dipinjam',
                ]);

                $book->decrement('stok_tersedia', $item['jumlah']);
            }

            return response()->json([
                'success' => true,
                'message' => 'Transaksi peminjaman berhasil dicatat',
                'data' => $borrowing->load(['user', 'details.book']),
            ], 201);
        });
    }

    public function show(Borrowing $borrowing)
    {
        return response()->json([
            'success' => true,
            'data' => $borrowing->load(['user', 'petugas', 'details.book', 'details.fine']),
        ]);
    }
}
