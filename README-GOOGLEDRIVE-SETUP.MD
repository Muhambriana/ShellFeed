## 📂 Konfigurasi Google Drive (via Rclone)

Ikuti langkah-langkah ini untuk mengintegrasikan Google Drive sebagai media penyimpanan menggunakan OAuth 2.0.

### 1. Buat Project di Google Cloud
1.  Buka [Google Cloud Console](https://console.cloud.google.com).
2.  Buat **Project Baru** atau pilih project yang sudah ada.

### 2. Aktifkan Google Drive API
1.  Buka menu **APIs & Services** > **Library**.
2.  Cari **"Google Drive API"**.
3.  Klik tombol **Enable**.

### 3. Buat OAuth Client ID
1.  Masuk ke **APIs & Services** > **Credentials**.
2.  Klik **Create Credentials** > **OAuth client ID**.
3.  *Jika diminta:* Atur **OAuth consent screen** terlebih dahulu (Pilih "External" untuk penggunaan pribadi).
4.  Pilih **Application type**: `Desktop App`.
5.  Simpan kredensial yang muncul:
    * `GDRIVE_CLIENT_ID`
    * `GDRIVE_CLIENT_SECRET`

---

### 4. Dapatkan Refresh Token (via Rclone)
Gunakan [Rclone](https://rclone.org/install/) di perangkat lokal Anda untuk mendapatkan token akses permanen.

1.  Jalankan perintah: `rclone config`
2.  Pilih **n** (New remote).
3.  Beri nama (misal: `gdrive`).
4.  Pilih tipe storage: `drive`.
5.  Masukkan `client_id` dan `client_secret` dari langkah sebelumnya.
6.  Pilih **scope**: `1` (Full access).
7.  Pada bagian **Use auto config?**, pilih **y**.
8.  Selesaikan proses login di browser dan klik **Allow**.

**Ambil Token:**
Buka file konfigurasi rclone (biasanya di `~/.config/rclone/rclone.conf` atau via command `rclone config file` untuk melihat lokasinya).

Cari bagian `refresh_token`:
```json
{
  "access_token": "...",
  "refresh_token": "ISI_INI_YANG_DIAMBIL",
  "expiry": "..."
}