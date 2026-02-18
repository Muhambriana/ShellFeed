## ☁️ Konfigurasi AWS IAM

Ikuti langkah-langkah berikut untuk mengatur perizinan dan mendapatkan kredensial AWS Anda.

### 4. Berikan Permission
Pilih salah satu metode di bawah ini sesuai dengan kebutuhan lingkungan kerja Anda:

* **✅ Cara Cepat (Hanya untuk Testing)**
    * Attach policy: `AdministratorAccess`
    * *Catatan: Memberikan akses penuh ke seluruh resource AWS.*

* **🔒 Cara Aman (Recommended for Production)**
    * Berikan policy sesuai kebutuhan spesifik (Principle of Least Privilege), misalnya:
        * `AmazonS3FullAccess`
        * `AmazonEC2FullAccess`
        * Atau *Custom Policy* sesuai service yang digunakan.

> **Langkah Selanjutnya:** Klik **Next** → **Create user**.

---

### 5. Buat Access Key
Setelah user berhasil dibuat, ikuti langkah ini untuk mendapatkan kunci akses:

1.  Buka detail user yang baru saja dibuat.
2.  Klik pada tab **Security credentials**.
3.  Scroll ke bawah ke bagian **Access keys** dan klik **Create access key**.
4.  Pilih opsi: **Application running outside AWS**.
5.  Klik **Create**.

Anda akan mendapatkan dua nilai penting:
* **Access Key ID** → `AWS_ACCESS_KEY_ID`
* **Secret Access Key** → `AWS_SECRET_ACCESS_KEY`

> [!CAUTION]
> **Secret Access Key** hanya akan tampil **satu kali**. Simpan di tempat yang aman (seperti Password Manager) atau download file `.csv` nya.

---

### 6. Tentukan AWS Region
Region dapat dilihat pada bagian pojok kanan atas di AWS Management Console. Gunakan kode region (string) untuk konfigurasi Anda.

| Lokasi | AWS Region Code |
| :--- | :--- |
| **Singapore** | `ap-southeast-1` |
| **Jakarta** | `ap-southeast-3` |
| **Tokyo** | `ap-northeast-1` |
| **N. Virginia** | `us-east-1` |

Nilai inilah yang akan digunakan untuk variabel: `AWS_REGION`