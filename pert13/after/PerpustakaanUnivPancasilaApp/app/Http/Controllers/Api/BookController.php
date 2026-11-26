<?php

namespace App\Http\Controllers\Api;

use App\Http\Controllers\Controller;
use App\Models\Book;
use Illuminate\Http\Request;

class BookController extends Controller
{
    public function index(Request $request)
    {
        $query = Book::with(['category', 'publisher']);

        if ($request->has('search')) {
            $search = $request->search;
            $query->where('judul', 'like', "%{$search}%")
                ->orWhere('penulis', 'like', "%{$search}%")
                ->orWhere('kode_buku', 'like', "%{$search}%");
        }

        return response()->json([
            'success' => true,
            'data' => $query->latest()->get(),
        ]);
    }

    public function store(Request $request)
    {
        $validated = $request->validate([
            'category_id' => 'required|exists:categories,id',
            'publisher_id' => 'required|exists:publishers,id',
            'kode_buku' => 'required|string|max:30|unique:books,kode_buku',
            'isbn' => 'required|string|max:20|unique:books,isbn',
            'judul' => 'required|string|max:255',
            'penulis' => 'required|string|max:255',
            'tahun_terbit' => 'required|digits:4',
            'lokasi_rak' => 'nullable|string|max:50',
            'stok_total' => 'required|integer|min:0',
            'stok_tersedia' => 'required|integer|min:0',
            'sinopsis' => 'nullable|string',
        ]);

        $book = Book::create($validated);

        return response()->json([
            'success' => true,
            'message' => 'Buku berhasil ditambahkan',
            'data' => $book->load(['category', 'publisher']),
        ], 201);
    }

    public function show(Book $book)
    {
        return response()->json([
            'success' => true,
            'data' => $book->load(['category', 'publisher']),
        ]);
    }

    public function update(Request $request, Book $book)
    {
        $validated = $request->validate([
            'category_id' => 'sometimes|required|exists:categories,id',
            'publisher_id' => 'sometimes|required|exists:publishers,id',
            'kode_buku' => 'sometimes|required|string|max:30|unique:books,kode_buku,' . $book->id,
            'isbn' => 'sometimes|required|string|max:20|unique:books,isbn,' . $book->id,
            'judul' => 'sometimes|required|string|max:255',
            'penulis' => 'sometimes|required|string|max:255',
            'tahun_terbit' => 'sometimes|required|digits:4',
            'lokasi_rak' => 'nullable|string|max:50',
            'stok_total' => 'sometimes|required|integer|min:0',
            'stok_tersedia' => 'sometimes|required|integer|min:0',
            'sinopsis' => 'nullable|string',
        ]);

        $book->update($validated);

        return response()->json([
            'success' => true,
            'message' => 'Data buku berhasil diperbarui',
            'data' => $book->load(['category', 'publisher']),
        ]);
    }

    public function destroy(Book $book)
    {
        $book->delete();

        return response()->json([
            'success' => true,
            'message' => 'Buku berhasil dihapus',
        ]);
    }
}
