## 🔥 Konfigurasi Firebase App Distribution

Ikuti langkah-langkah ini untuk mengotomatiskan pengiriman aplikasi (khususnya Android) melalui Firebase.

### 1. Ambil FIREBASE_APP_ID
1.  Buka [Firebase Console](https://console.firebase.google.com).
2.  Pilih project Anda, lalu klik ikon **Settings** (gear ⚙️) > **Project settings**.
3.  Scroll ke bawah ke bagian **Your apps**.
4.  Pilih aplikasi Android Anda.
5.  Salin nilai **App ID** (Contoh: `1:1234567890:android:abc123def456`).

> Nilai ini digunakan untuk secret: `FIREBASE_APP_ID`

---

### 2. Buat Service Account (Untuk CI/CD)
1.  Masih di **Project settings**, klik tab **Service accounts**.
2.  Klik tombol **Generate new private key**.
3.  File JSON akan terunduh secara otomatis ke komputer Anda.

---

### 3. Masukkan ke GitHub Secrets
Buka file JSON yang baru saja diunduh menggunakan Text Editor (Notepad/VS Code).

1.  **Copy SELURUH isi JSON** tersebut (termasuk kurung kurawal `{ }`).
2.  Buka GitHub Repository > **Settings** > **Secrets and variables** > **Actions**.
3.  Buat secret baru dengan nama: `FIREBASE_SERVICE_ACCOUNT`.
4.  **Paste** isi JSON apa adanya tanpa perubahan format.

> [!IMPORTANT]
> Jangan melakukan encode Base64 atau mengubah isi JSON. Paste mentah-mentah agar GitHub Action bisa membacanya dengan benar.

---

### 4. Pastikan Role Service Account Benar
Agar GitHub Actions bisa mengunggah file APK/AAB, Service Account tersebut harus memiliki izin yang cukup di [Google Cloud Console](https://console.cloud.google.com/iam-admin/iam).

Pastikan email Service Account tersebut memiliki minimal salah satu role berikut:
* **Firebase App Distribution Admin** (Sangat disarankan)
* **Editor** (Hanya untuk testing)

---

### 5. Atur Tester Groups
Pada file workflow GitHub Actions, biasanya terdapat konfigurasi `groups: production`.

1.  Buka **Firebase Console** > **App Distribution**.
2.  Klik tab **Testers & Groups**.
3.  Klik **Add group** dan beri nama `production`.
4.  Masukkan email para tester ke dalam grup tersebut.

> [!CAUTION]
> Jika grup `production` belum dibuat di Firebase Console, proses upload dari GitHub Actions akan gagal.