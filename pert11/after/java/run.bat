@echo off
setlocal

echo [INFO] Mengompilasi proyek Java...
call mvn compile
if errorlevel 1 (
  echo [ERROR] Kompilasi gagal. Jalankan mvn compile untuk melihat detail.
  exit /b 1
)

echo.
echo [INFO] Menjalankan aplikasi dengan console interaktif...
java -cp target\classes id.ac.univpancasila.perpustakaan.Main
