# 📂 Google Drive Configuration (via Rclone)

Follow these steps to integrate Google Drive as a storage medium using OAuth 2.0.

## 1. Create a Project in Google Cloud
1. Go to [Google Cloud Console](https://console.cloud.google.com).
2. Create a **New Project** or select an existing project.

## 2. Enable Google Drive API
1. Open the **APIs & Services** > **Library** menu.
2. Search for **"Google Drive API"**.
3. Click the **Enable** button.

## 3. Create OAuth Client ID
1. Go to **APIs & Services** > **Credentials**.
2. Click **Create Credentials** > **OAuth client ID**.
3. *If prompted:* Set up the **OAuth consent screen** first (Choose "External" for personal use).
4. Select **Application type**: `Desktop App`.
5. Save the credentials that appear:
   * `GDRIVE_CLIENT_ID`
   * `GDRIVE_CLIENT_SECRET`

---

## 4. Get Refresh Token (via Rclone)
Use [Rclone](https://rclone.org/install/) on your local device to obtain a permanent access token.

1. Run the command: `rclone config`
2. Select **n** (New remote).
3. Give it a name (e.g., `gdrive`).
4. Select storage type: `drive`.
5. Enter the `client_id` and `client_secret` from the previous step.
6. Select **scope**: `1` (Full access).
7. In the **Use auto config?** section, select **y**.
8. Complete the login process in the browser and click **Allow**.

**Extract the Token:**
Open the rclone configuration file (usually located at `~/.config/rclone/rclone.conf` or use the command `rclone config file` to see its location).

Look for the `refresh_token` section:
```json
{
  "access_token": "...",
  "refresh_token": "COPY_THIS_VALUE",
  "expiry": "..."
}
```

Copy the value of `refresh_token` and save it as:
* `GDRIVE_REFRESH_TOKEN`

---

## 5. Set Environment Variables
Add the following variables to your environment or `.env` file:

```env
GDRIVE_CLIENT_ID=your_client_id_here
GDRIVE_CLIENT_SECRET=your_client_secret_here
GDRIVE_REFRESH_TOKEN=your_refresh_token_here
```

---

## 6. Test the Connection
Use Rclone or your application to verify that the connection to Google Drive is working properly.

```bash
rclone lsd gdrive:
```

If successful, you should see a list of folders in your Google Drive.

---

## ⚠️ Important Notes
- **Keep your credentials secure**: Do not share `client_secret` or `refresh_token` publicly.
- **Token expiration**: Refresh tokens typically do not expire, but may be revoked if not used for an extended period.
- **API Quota**: Be aware of Google Drive API usage limits.

---

<br>
<br>

---

<br>
<br>

# 📂 Konfigurasi Google Drive (via Rclone)

Ikuti langkah-langkah ini untuk mengintegrasikan Google Drive sebagai media penyimpanan menggunakan OAuth 2.0.

## 1. Buat Project di Google Cloud
1. Buka [Google Cloud Console](https://console.cloud.google.com).
2. Buat **Project Baru** atau pilih project yang sudah ada.

## 2. Aktifkan Google Drive API
1. Buka menu **APIs & Services** > **Library**.
2. Cari **"Google Drive API"**.
3. Klik tombol **Enable**.

## 3. Buat OAuth Client ID
1. Masuk ke **APIs & Services** > **Credentials**.
2. Klik **Create Credentials** > **OAuth client ID**.
3. *Jika diminta:* Atur **OAuth consent screen** terlebih dahulu (Pilih "External" untuk penggunaan pribadi).
4. Pilih **Application type**: `Desktop App`.
5. Simpan kredensial yang muncul:
   * `GDRIVE_CLIENT_ID`
   * `GDRIVE_CLIENT_SECRET`

---

## 4. Dapatkan Refresh Token (via Rclone)
Gunakan [Rclone](https://rclone.org/install/) di perangkat lokal Anda untuk mendapatkan token akses permanen.

1. Jalankan perintah: `rclone config`
2. Pilih **n** (New remote).
3. Beri nama (misal: `gdrive`).
4. Pilih tipe storage: `drive`.
5. Masukkan `client_id` dan `client_secret` dari langkah sebelumnya.
6. Pilih **scope**: `1` (Full access).
7. Pada bagian **Use auto config?**, pilih **y**.
8. Selesaikan proses login di browser dan klik **Allow**.

**Ambil Token:**
Buka file konfigurasi rclone (biasanya di `~/.config/rclone/rclone.conf` atau via command `rclone config file` untuk melihat lokasinya).

Cari bagian `refresh_token`:
```json
{
  "access_token": "...",
  "refresh_token": "ISI_INI_YANG_DIAMBIL",
  "expiry": "..."
}
```

Salin nilai dari `refresh_token` dan simpan sebagai:
* `GDRIVE_REFRESH_TOKEN`

---

## 5. Atur Environment Variables
Tambahkan variabel berikut ke environment atau file `.env` Anda:

```env
GDRIVE_CLIENT_ID=client_id_anda_disini
GDRIVE_CLIENT_SECRET=client_secret_anda_disini
GDRIVE_REFRESH_TOKEN=refresh_token_anda_disini
```

---

## 6. Uji Koneksi
Gunakan Rclone atau aplikasi Anda untuk memverifikasi bahwa koneksi ke Google Drive berfungsi dengan baik.

```bash
rclone lsd gdrive:
```

Jika berhasil, Anda akan melihat daftar folder di Google Drive Anda.

---

## ⚠️ Catatan Penting
- **Jaga keamanan kredensial**: Jangan bagikan `client_secret` atau `refresh_token` secara publik.
- **Kedaluwarsa token**: Refresh token biasanya tidak kedaluwarsa, tetapi dapat dicabut jika tidak digunakan dalam waktu lama.
- **Kuota API**: Perhatikan batas penggunaan Google Drive API.

---

## 📚 Referensi
- [Rclone Documentation](https://rclone.org/drive/)
- [Google Drive API Documentation](https://developers.google.com/drive/api/guides/about-sdk)
- [OAuth 2.0 Guide](https://developers.google.com/identity/protocols/oauth2)