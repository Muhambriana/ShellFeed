# Continuous Deployment (CD) -- Build & Distribute APK (Production)

## Overview

This document explains how to:

-   Secure secret keys (API keys, base URLs, etc.)
-   Use a keystore (.jks) for release builds
-   Generate a structured production APK filename
-   Automatically distribute APKs to AWS S3, Google Drive, or Firebase
    App Distribution

------------------------------------------------------------------------

## 1. Using Secret Keys (local.properties → GitHub Secrets)

If you store secrets such as:

-   base_url_api
-   api_key

You should move them to **GitHub Secrets**.

Go to:

Repository → Settings → Secrets and variables → Actions → New repository
secret

Example:

-   BASE_URL
-   API_KEY

### Gradle Helper Function

``` kotlin
fun loadProperty(propertyName: String, defaultValue: String = ""): String {
    (project.findProperty(propertyName) as String?)?.takeIf { it.isNotBlank() }?.let { return it }

    System.getenv(propertyName)?.takeIf { it.isNotBlank() }?.let { return it }
    System.getenv(propertyName.uppercase(Locale.US))?.takeIf { it.isNotBlank() }?.let { return it }

    val propertiesFile = rootProject.file("local.properties")
    return Properties().apply {
        if (propertiesFile.exists()) load(propertiesFile.inputStream())
    }.getProperty(propertyName, defaultValue)
}
```

Use inside cd.yml:

``` yaml
env:
  BASE_URL: ${{ secrets.BASE_URL }}
  API_KEY: ${{ secrets.API_KEY }}
```

------------------------------------------------------------------------

## 2. Setup Keystore (.jks) for Release Build

Convert your keystore to Base64.

### macOS / Linux

``` bash
base64 your-release-key.jks | pbcopy
```

### Windows (PowerShell)

``` powershell
[Convert]::ToBase64String([IO.File]::ReadAllBytes("your-release-key.jks")) | Set-Clipboard
```

Add these secrets:

-   KEYSTORE_BASE64
-   KEYSTORE_PASSWORD
-   KEYSTORE_ALIAS
-   KEYSTORE_ALIAS_PASSWORD

Decode in cd.yml:

``` yaml
- name: Decode Keystore
  run: |
    echo "${{ secrets.KEYSTORE_BASE64 }}" | base64 --decode > keystore.jks
```

------------------------------------------------------------------------

## 3. Production File Naming Format

Format:

app-prod-v{VersionName}-{YYYYMMDD-HHMMSS}.apk

Example:

app-prod-v1.2.0-20260218-143522.apk

Get version:

``` yaml
- name: Get version
  id: get_version
  run: |
    VERSION_NAME=$(grep -A 5 "defaultConfig" app/build.gradle.kts | grep "versionName" | sed 's/.*= "(.*)".*/\1/' | tr -d ' ')
    echo "VERSION=$VERSION_NAME" >> $GITHUB_OUTPUT
```

Set filename:

``` yaml
- name: Set filename
  run: echo "FILENAME=app-prod-v${{ steps.get_version.outputs.VERSION }}-$(date +%Y%m%d-%H%M%S).apk" >> $GITHUB_ENV
```

------------------------------------------------------------------------

## 4. APK Distribution

### [AWS S3](https://github.com/Muhambriana/ShellFeed/blob/main/AWS-S3-SETUP.MD)

``` yaml
- name: Configure AWS Credentials
  uses: aws-actions/configure-aws-credentials@v4
  with:
    aws-access-key-id: ${{ secrets.AWS_ACCESS_KEY_ID }}
    aws-secret-access-key: ${{ secrets.AWS_SECRET_ACCESS_KEY }}
    aws-region: ${{ secrets.AWS_REGION }}
```

### [Google Drive (rclone)](https://github.com/Muhambriana/ShellFeed/blob/main/GDRIVE_SETUP.md)

``` yaml
- name: Upload APK to Google Drive
  run: |
    rclone copyto "app/build/outputs/apk/production/release/app-production-release.apk" "gdrive:$FILENAME"
```

### [Firebase App Distribution](https://github.com/Muhambriana/ShellFeed/blob/main/FIREBASE-SETUP.MD)

``` yaml
- name: Upload to Firebase
  uses: wzieba/Firebase-Distribution-Github-Action@v1
  with:
    appId: ${{ secrets.FIREBASE_APP_ID }}
    serviceCredentialsFileContent: ${{ secrets.FIREBASE_SERVICE_ACCOUNT }}
    file: ${{ env.FILENAME }}
    groups: production
```

------------------------------------------------------------------------

## Best Practices

-   Never commit local.properties
-   Never commit .jks files
-   Use a dedicated IAM user for uploads
-   Separate production and staging secrets

## \# 🇮🇩 VERSI BAHASA INDONESIA

## Gambaran Umum

Dokumen ini menjelaskan cara:

-   Mengamankan secret key (API key, base URL, dll)
-   Menggunakan keystore (.jks) untuk build release
-   Membuat format nama file APK production
-   Mendistribusikan APK secara otomatis ke AWS S3, Google Drive, atau
    Firebase

------------------------------------------------------------------------

## 1. Menggunakan Secret Key (local.properties → GitHub Secrets)

Jika Anda menyimpan secret seperti:

-   base_url_api
-   api_key

Sebaiknya pindahkan ke **GitHub Secrets**.

Masuk ke:

Repository → Settings → Secrets and variables → Actions → New repository
secret

Contoh:

-   BASE_URL
-   API_KEY

Gunakan fungsi Gradle yang sama seperti di atas untuk membaca dari
environment variable.

Tambahkan di cd.yml:

``` yaml
env:
  BASE_URL: ${{ secrets.BASE_URL }}
  API_KEY: ${{ secrets.API_KEY }}
```

------------------------------------------------------------------------

## 2. Setup Keystore (.jks) untuk Build Release

Encode file .jks ke Base64, lalu simpan di GitHub Secrets.

Tambahkan:

-   KEYSTORE_BASE64
-   KEYSTORE_PASSWORD
-   KEYSTORE_ALIAS
-   KEYSTORE_ALIAS_PASSWORD

Decode di cd.yml:

``` yaml
- name: Decode Keystore
  run: |
    echo "${{ secrets.KEYSTORE_BASE64 }}" | base64 --decode > keystore.jks
```

------------------------------------------------------------------------

## 3. Format Nama File Production

Format:

app-prod-v{VersionName}-{YYYYMMDD-HHMMSS}.apk

Ambil versi dari Gradle, lalu set nama file seperti pada contoh bagian
English.

------------------------------------------------------------------------

## 4. Distribusi APK

Anda dapat menggunakan:

-   AWS S3
-   Google Drive (rclone)
-   Firebase App Distribution

Contoh konfigurasi YAML sama seperti pada bagian English.

------------------------------------------------------------------------

## Best Practice

-   Jangan commit local.properties
-   Jangan commit file .jks
-   Gunakan IAM user khusus untuk upload
-   Pisahkan secret production dan staging

------------------------------------------------------------------------

Dengan konfigurasi ini, proses build dan distribusi APK production
menjadi otomatis, aman, dan profesional.
