<?php


namespace Perpustakaan\Exception;
use RuntimeException;
/**
 * Sesi 10 — hierarki exception domain (PHP).
 *ewdd
 * PHP tidak mengenal checked exception; seluruh exception setara dengan
 * unchecked di Java. Karena itu anotasi @throws pada PbHPDoc menjadi jauh
 * lebih penting: pembaca kode tidak dibantu kompilator.
 */


abstract class PerpustakaanException extends RuntimeException
{
}
