# 🔥 Firebase App Distribution Configuration

[🇬🇧 English](#english) | [🇮🇩 Bahasa Indonesia](#bahasa-indonesia)

---

<a name="english"></a>
## 🇬🇧 English Version

Follow these steps to automate app distribution (specifically Android) via Firebase.

### 1️⃣ Get Your FIREBASE_APP_ID

1. Open [Firebase Console](https://console.firebase.google.com)
2. Select your project
3. Click the **Settings** icon (⚙️) > **Project settings**
4. Scroll down to the **Your apps** section
5. Select your Android app
6. Copy the **App ID** value (Example: `1:1234567890:android:abc123def456`)

> This value will be used for the secret: `FIREBASE_APP_ID`

---

### 2️⃣ Create a Service Account (For CI/CD)

1. Still in **Project settings**, click the **Service accounts** tab
2. Click the **Generate new private key** button
3. A JSON file will be automatically downloaded to your computer

**The JSON file will look like this:**
```json
{
  "type": "service_account",
  "project_id": "your-project-id",
  "private_key_id": "...",
  "private_key": "-----BEGIN PRIVATE KEY-----\n....",
  "client_email": "firebase-adminsdk-xxxxx@your-project.iam.gserviceaccount.com",
  ...
}
```

> **Note:** This service account is actually from Google Cloud.

---

### 3️⃣ Add to GitHub Secrets

Open the JSON file you just downloaded using a text editor (Notepad/VS Code).

1. **Copy the ENTIRE JSON content** (including the curly braces `{ }`)
2. Open your GitHub Repository > **Settings** > **Secrets and variables** > **Actions**
3. Create a new secret with the name: `FIREBASE_SERVICE_ACCOUNT`
4. **Paste** the JSON content as-is without any format changes

> [!IMPORTANT]
> Do NOT encode to Base64 or modify the JSON content. Paste it raw so GitHub Actions can read it correctly.

---

### 4️⃣ Ensure Service Account Has Correct Permissions

For GitHub Actions to upload APK/AAB files, the service account must have sufficient permissions in [Google Cloud Console](https://console.cloud.google.com/iam-admin/iam).

Make sure the service account email has at least one of the following roles:
* **Firebase App Distribution Admin** (Highly recommended)
* **Editor** (For testing only)

**How to check:**
1. Go to [Google Cloud Console](https://console.cloud.google.com/iam-admin/iam)
2. Find your service account email
3. Verify it has the proper role assigned

---

### 5️⃣ Configure Tester Groups

In your GitHub Actions workflow file, you'll typically see a configuration like `groups: production`.

1. Open **Firebase Console** > **App Distribution**
2. Click the **Testers & Groups** tab
3. Click **Add group** and name it `production`
4. Add tester emails to this group

> [!CAUTION]
> If the `production` group hasn't been created in Firebase Console, the upload from GitHub Actions will fail.

---

## ✅ Summary Checklist

- [ ] `FIREBASE_APP_ID` added to GitHub Secrets
- [ ] `FIREBASE_SERVICE_ACCOUNT` (full JSON) added to GitHub Secrets
- [ ] Service account has **Firebase App Distribution Admin** role
- [ ] Tester group `production` created in Firebase Console
- [ ] Testers added to the `production` group

---

<a name="bahasa-indonesia"></a>
## 🇮🇩 Versi Bahasa Indonesia

Ikuti langkah-langkah ini untuk mengotomatiskan pengiriman aplikasi (khususnya Android) melalui Firebase.

### 1️⃣ Ambil FIREBASE_APP_ID

1. Buka [Firebase Console](https://console.firebase.google.com)
2. Pilih project Anda
3. Klik ikon **Settings** (⚙️) > **Project settings**
4. Scroll ke bawah ke bagian **Your apps**
5. Pilih aplikasi Android Anda
6. Salin nilai **App ID** (Contoh: `1:1234567890:android:abc123def456`)

> Nilai ini digunakan untuk secret: `FIREBASE_APP_ID`

---

### 2️⃣ Buat Service Account (Untuk CI/CD)

1. Masih di **Project settings**, klik tab **Service accounts**
2. Klik tombol **Generate new private key**
3. File JSON akan terunduh secara otomatis ke komputer Anda

**File JSON akan terlihat seperti ini:**
```json
{
  "type": "service_account",
  "project_id": "your-project-id",
  "private_key_id": "...",
  "private_key": "-----BEGIN PRIVATE KEY-----\n....",
  "client_email": "firebase-adminsdk-xxxxx@your-project.iam.gserviceaccount.com",
  ...
}
```

> **Catatan:** Service account ini sebenarnya berasal dari Google Cloud.

---

### 3️⃣ Masukkan ke GitHub Secrets

Buka file JSON yang baru saja diunduh menggunakan Text Editor (Notepad/VS Code).

1. **Copy SELURUH isi JSON** tersebut (termasuk kurung kurawal `{ }`)
2. Buka GitHub Repository > **Settings** > **Secrets and variables** > **Actions**
3. Buat secret baru dengan nama: `FIREBASE_SERVICE_ACCOUNT`
4. **Paste** isi JSON apa adanya tanpa perubahan format

> [!IMPORTANT]
> Jangan melakukan encode Base64 atau mengubah isi JSON. Paste mentah-mentah agar GitHub Action bisa membacanya dengan benar.

---

### 4️⃣ Pastikan Role Service Account Benar

Agar GitHub Actions bisa mengunggah file APK/AAB, Service Account tersebut harus memiliki izin yang cukup di [Google Cloud Console](https://console.cloud.google.com/iam-admin/iam).

Pastikan email Service Account tersebut memiliki minimal salah satu role berikut:
* **Firebase App Distribution Admin** (Sangat disarankan)
* **Editor** (Hanya untuk testing)

**Cara mengecek:**
1. Buka [Google Cloud Console](https://console.cloud.google.com/iam-admin/iam)
2. Cari email service account Anda
3. Pastikan memiliki role yang sesuai

---

### 5️⃣ Atur Tester Groups

Pada file workflow GitHub Actions, biasanya terdapat konfigurasi `groups: production`.

1. Buka **Firebase Console** > **App Distribution**
2. Klik tab **Testers & Groups**
3. Klik **Add group** dan beri nama `production`
4. Masukkan email para tester ke dalam grup tersebut

> [!CAUTION]
> Jika grup `production` belum dibuat di Firebase Console, proses upload dari GitHub Actions akan gagal.

---

## ✅ Daftar Periksa

- [ ] `FIREBASE_APP_ID` ditambahkan ke GitHub Secrets
- [ ] `FIREBASE_SERVICE_ACCOUNT` (JSON lengkap) ditambahkan ke GitHub Secrets
- [ ] Service account memiliki role **Firebase App Distribution Admin**
- [ ] Grup tester `production` dibuat di Firebase Console
- [ ] Tester ditambahkan ke grup `production`

---

## 📚 Additional Resources / Sumber Tambahan

- [Firebase App Distribution Documentation](https://firebase.google.com/docs/app-distribution)
- [GitHub Actions Secrets](https://docs.github.com/en/actions/security-guides/encrypted-secrets)
- [Google Cloud IAM Roles](https://cloud.google.com/iam/docs/understanding-roles)

---

**Made with ❤️ for ShellFeed**
