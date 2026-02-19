# AWS Configuration for GitHub Actions

This guide explains how to create an IAM user in AWS, generate access keys, and store them as GitHub Secrets for use in GitHub Actions workflows.

---

## 📋 Table of Contents

- [1. Creating an IAM User in AWS](#1-creating-an-iam-user-in-aws)
- [2. Generating Access Keys](#2-generating-access-keys)
- [3. Adding Secrets to GitHub](#3-adding-secrets-to-github)
- [4. Additional Security Tips](#4-additional-security-tips)

---

## 1. Creating an IAM User in AWS

### Step 1: Login and Navigate to IAM

1. Log in to **AWS Management Console**.
2. In the search bar, type **IAM**, then select **IAM (Identity and Access Management)**.

---

### Step 2: Create a New User

1. In the left menu, click **Users**.
2. Click the **Create user** button.
3. Enter a username, for example: `github-actions-user`.
4. Click **Next**.

---

### Step 3: Set Permissions

1. Select **Attach policies directly**.
2. Choose policies based on your needs.

**Examples:**

* **✅ Quick Method (Testing Only)**
    * Attach policy: `AdministratorAccess`
    * *Note: Grants full access to all AWS resources.*

* **🔒 Secure Method (Recommended for Production)**
    * Grant specific policies based on the Principle of Least Privilege, such as:
        * `AmazonS3FullAccess`
        * `AmazonEC2FullAccess`
        * Or a *Custom Policy* tailored to the services you use.

> ⚠️ **Important**  
> Always apply the **Least Privilege** principle—only grant the access that is absolutely necessary.  
> Avoid granting full access unless required.

3. Click **Next** → **Create user**.
4. Verify that the user has been created and appears in the Users list.

---

## 2. Generating Access Keys

After successfully creating the user, the next step is to generate access keys so GitHub can access AWS.

---

### Step 1: Open Security Credentials

1. Click on the newly created user's name.
2. Open the **Security credentials** tab.
3. Scroll down to the **Access keys** section.
4. Click **Create access key**.

---

### Step 2: Select Use Case

1. Choose one of the following:
   - **Command Line Interface (CLI)**
   - **Application running outside AWS** (commonly used for GitHub Actions)
2. Check the box to confirm you understand the security risks.
3. Click **Next**, then click **Create access key**.

---

### Step 3: Save Your Access Key

After creation, you will see two important values:

* **Access Key ID** → Use as `AWS_ACCESS_KEY_ID`
* **Secret Access Key** → Use as `AWS_SECRET_ACCESS_KEY`

> [!CAUTION]
> **Secret Access Key** will only be displayed **once**.  
> - If lost, you must delete and create a new access key.
> - Never commit or push this key to your repository.
> - Save it in a secure location (like a Password Manager) or download the `.csv` file.

---

## 3. Adding Secrets to GitHub

To allow your workflow YAML file to read values like:

```yaml
${{ secrets.AWS_ACCESS_KEY_ID }}
```

You must store them as secrets in your GitHub repository.

---

### Steps:

1. Open your repository on GitHub.
2. Click the **Settings** tab.
3. Select **Secrets and variables** → Click **Actions**.
4. Click the **New repository secret** button.
5. Add the following secrets one by one:

| Name | Value |
| :--- | :--- |
| `AWS_ACCESS_KEY_ID` | Paste your Access Key ID |
| `AWS_SECRET_ACCESS_KEY` | Paste your Secret Access Key |
| `AWS_REGION` | Example: `ap-southeast-1` |

---

### AWS Region Reference

Region codes can be found in the top-right corner of the AWS Management Console.

| Location | AWS Region Code |
| :--- | :--- |
| **Singapore** | `ap-southeast-1` |
| **Jakarta** | `ap-southeast-3` |
| **Tokyo** | `ap-northeast-1` |
| **N. Virginia** | `us-east-1` |

Use the appropriate region code for the `AWS_REGION` variable.

---

## 4. Additional Security Tips

For production environments, consider the following best practices:

* ✅ **Avoid using `AdministratorAccess`**  
  Use specific policies that grant access only to required resources (e.g., a single S3 bucket).

* ✅ **Enable MFA for manual user logins**  
  If the user is also used for console access.

* ✅ **Consider using IAM Roles with OpenID Connect (OIDC)**  
  This eliminates the need to store static access keys and is the most secure method for GitHub Actions.

* ✅ **Rotate access keys regularly**  
  Periodically regenerate and update your keys.

* ✅ **Monitor usage with AWS CloudTrail**  
  Track API calls and detect unusual activity.

---

---

---

# Konfigurasi AWS untuk GitHub Actions

Panduan ini menjelaskan cara membuat user IAM di AWS, membuat access key, dan menyimpannya sebagai GitHub Secrets agar bisa digunakan pada workflow GitHub Actions.

---

## 📋 Daftar Isi

- [1. Membuat User IAM di AWS](#1-membuat-user-iam-di-aws)
- [2. Generate Access Key](#2-generate-access-key)
- [3. Masukkan ke GitHub Secrets](#3-masukkan-ke-github-secrets)
- [4. Tips Keamanan Tambahan](#4-tips-keamanan-tambahan)

---

## 1. Membuat User IAM di AWS

### Langkah 1: Login dan Masuk ke IAM

1. Login ke **AWS Management Console**.
2. Di kolom pencarian, ketik **IAM**, lalu pilih layanan **IAM (Identity and Access Management)**.

---

### Langkah 2: Membuat User Baru

1. Di menu sebelah kiri, klik **Users**.
2. Klik tombol **Create user**.
3. Masukkan nama user, misalnya: `github-actions-user`.
4. Klik **Next**.

---

### Langkah 3: Atur Permission (Hak Akses)

1. Pilih opsi **Attach policies directly**.
2. Pilih policy sesuai kebutuhan.

**Contoh:**

* **✅ Cara Cepat (Hanya untuk Testing)**
    * Attach policy: `AdministratorAccess`
    * *Catatan: Memberikan akses penuh ke seluruh resource AWS.*

* **🔒 Cara Aman (Recommended for Production)**
    * Berikan policy sesuai kebutuhan spesifik (Principle of Least Privilege), misalnya:
        * `AmazonS3FullAccess`
        * `AmazonEC2FullAccess`
        * Atau *Custom Policy* sesuai service yang digunakan.

> ⚠️ **Penting**  
> Selalu terapkan prinsip **Least Privilege**, yaitu hanya memberikan akses yang benar-benar dibutuhkan.  
> Jangan memberikan akses penuh jika tidak diperlukan.

3. Klik **Next** → **Create user**.
4. Pastikan user berhasil dibuat dan muncul di daftar Users.

---

## 2. Generate Access Key

Setelah user berhasil dibuat, langkah berikutnya adalah membuat access key agar GitHub bisa mengakses AWS.

---

### Langkah 1: Buka Security Credentials

1. Klik nama user yang baru dibuat.
2. Buka tab **Security credentials**.
3. Scroll ke bagian **Access keys**.
4. Klik **Create access key**.

---

### Langkah 2: Pilih Tipe Penggunaan

1. Pilih salah satu:
   - **Command Line Interface (CLI)**
   - **Application running outside AWS** (umumnya digunakan untuk GitHub Actions)
2. Centang konfirmasi bahwa Anda memahami risiko keamanan.
3. Klik **Next**, lalu klik **Create access key**.

---

### Langkah 3: Simpan Access Key

Setelah berhasil dibuat, Anda akan melihat dua informasi penting:

* **Access Key ID** → Digunakan sebagai `AWS_ACCESS_KEY_ID`
* **Secret Access Key** → Digunakan sebagai `AWS_SECRET_ACCESS_KEY`

> [!CAUTION]
> **Secret Access Key** hanya akan ditampilkan **satu kali**.  
> - Jika hilang, Anda harus menghapus dan membuat access key yang baru.
> - Jangan pernah commit atau push key ini ke repository.
> - Simpan di tempat yang aman (seperti Password Manager) atau download file `.csv` nya.

---

## 3. Masukkan ke GitHub Secrets

Agar file workflow YAML Anda dapat membaca nilai seperti:

```yaml
${{ secrets.AWS_ACCESS_KEY_ID }}
```

Anda harus menyimpannya sebagai secret di repository GitHub.

---

### Langkah-langkah:

1. Buka repository Anda di GitHub.
2. Klik tab **Settings**.
3. Pilih **Secrets and variables** → Klik **Actions**.
4. Klik tombol **New repository secret**.
5. Tambahkan data berikut satu per satu:

| Name | Value |
| :--- | :--- |
| `AWS_ACCESS_KEY_ID` | Paste Access Key ID Anda |
| `AWS_SECRET_ACCESS_KEY` | Paste Secret Access Key Anda |
| `AWS_REGION` | Contoh: `ap-southeast-1` |

---

### Referensi AWS Region

Kode region dapat dilihat pada bagian pojok kanan atas di AWS Management Console.

| Lokasi | AWS Region Code |
| :--- | :--- |
| **Singapore** | `ap-southeast-1` |
| **Jakarta** | `ap-southeast-3` |
| **Tokyo** | `ap-northeast-1` |
| **N. Virginia** | `us-east-1` |

Gunakan kode region yang sesuai untuk variabel `AWS_REGION`.

---

## 4. Tips Keamanan Tambahan

Untuk lingkungan production, pertimbangkan praktik terbaik berikut:

* ✅ **Hindari penggunaan `AdministratorAccess`**  
  Gunakan policy khusus yang hanya mengizinkan akses ke resource tertentu (misalnya hanya satu bucket S3).

* ✅ **Aktifkan MFA untuk user manual**  
  Jika user juga digunakan untuk login console.

* ✅ **Pertimbangkan menggunakan IAM Role + OpenID Connect (OIDC)**  
  Ini menghilangkan kebutuhan untuk menyimpan access key secara statis dan merupakan metode paling aman untuk GitHub Actions.

* ✅ **Rotasi access key secara berkala**  
  Generate ulang dan perbarui key Anda secara periodik.

* ✅ **Monitor penggunaan dengan AWS CloudTrail**  
  Lacak API calls dan deteksi aktivitas yang tidak biasa.

---
