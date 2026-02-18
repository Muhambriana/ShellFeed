# Android CI/CD Project Sample – Automated APK Build and Distribution


A reproducible Continuous Integration and Continuous Deployment (CI/CD) pipeline for building, signing, and distributing Android APK artifacts automatically across multiple channels.


This project eliminates manual APK handling and ensures consistent, reliable, and scalable delivery for testing and internal releases.


---


# 🇬🇧 English


## Background


In many Android projects, APK distribution is still handled manually: building locally via Android Studio and uploading the generated file to one or multiple platforms such as AWS, Google Drive, or Firebase.


Not every application is released through the Play Store. Internal testing, QA validation, beta releases, or private deployments often require faster and more flexible distribution methods.


Manual processes tend to be:


- time-consuming  
- error-prone  
- difficult to reproduce consistently  
- inefficient during rapid development cycles  


This repository adopts a **Continuous Integration and Continuous Deployment (CI/CD)** approach using **GitHub Actions** to automate the entire lifecycle of building, signing, versioning, and distributing APK artifacts.


Every push to the production branch automatically produces a ready-to-use artifact for testers and stakeholders.


---


## Objectives


- Automate APK build and signing  
- Remove dependency on local developer machines  
- Reduce human errors during distribution  
- Enable simultaneous multi-channel delivery  
- Maintain consistent artifact naming and versioning  


---

## [STEP](https://github.com/Muhambriana/ShellFeed/blob/main/README-STEP.md)
## Workflow Architecture


Each `push` to the `main` branch triggers the following pipeline:


1. Prepare environment (Java 17 + Android SDK)  
2. Decode keystore from GitHub Secrets  
3. Build Production Release APK  
4. Extract `versionName` automatically from Gradle  
5. Generate filename using version + timestamp  
6. Upload APK to:
   - AWS S3 (primary storage / backup)
   - Google Drive (internal sharing)
   - Firebase App Distribution (testers/QA)


One push → the APK becomes instantly available across all distribution channels.


---


## Technology Stack


- GitHub Actions — CI/CD orchestration  
- Gradle — build system  
- AWS S3 — artifact storage  
- Rclone — Google Drive integration  
- Firebase App Distribution — tester delivery  


---


## Artifact Naming Convention


Format:



app-prod-v<version>-yyyymmdd-hhmmss.apk



Example:



app-prod-v1.2.3-20260209-103012.apk


### APP
- BASE_URL  
- API_KEY  


### AWS
- AWS_ACCESS_KEY_ID  
- AWS_SECRET_ACCESS_KEY  
- AWS_REGION  
- AWS_S3_BUCKET_NAME  


### Google Drive (Rclone)
- GDRIVE_CLIENT_ID  
- GDRIVE_CLIENT_SECRET  
- GDRIVE_REFRESH_TOKEN  
- GDRIVE_FOLDER_ID  


### Firebase
- FIREBASE_APP_ID  
- FIREBASE_SERVICE_ACCOUNT  


---


## Benefits


This approach provides:


- faster and more consistent releases  
- fully automated distribution  
- high reproducibility  
- lower operational overhead  
- better scalability for growing teams  


---


## Summary


Simply push your code to the production branch.


The pipeline will automatically:


**Build → Sign → Version → Distribute**


A clean, reliable, and production-ready delivery workflow suitable for testing and private deployments.


The workflow can also be extended to support direct Play Store publishing in the future.


---


---


# 🇮🇩 Bahasa Indonesia


## Latar Belakang


Pada banyak proyek Android, distribusi APK masih dilakukan secara manual: melakukan build secara lokal melalui Android Studio, kemudian mengunggah file ke satu atau beberapa platform seperti AWS, Google Drive, atau Firebase.


Tidak semua aplikasi didistribusikan melalui Play Store. Untuk kebutuhan internal, QA, beta testing, tim memerlukan metode alternatif yang lebih cepat dan fleksibel.


Pendekatan manual cenderung:


- memakan waktu  
- rentan kesalahan manusia  
- sulit direplikasi secara konsisten  
- kurang efisien saat iterasi build yang intens  


Repositori ini menerapkan **Continuous Integration dan Continuous Deployment (CI/CD)** menggunakan **GitHub Actions** untuk mengotomatisasi proses build, signing, versioning, dan distribusi APK secara menyeluruh.


---


## Tujuan


- Mengotomatisasi proses build dan signing APK  
- Menghilangkan ketergantungan pada build local developer  
- Mengurangi human error  
- Menyediakan distribusi multi-channel  
- Menjaga konsistensi penamaan artefak  


---


## Ringkasan Alur


Setiap `push` ke branch `main` akan:


**Build → Sign → Versioning → Distribusi**


APK otomatis tersedia di AWS S3, Google Drive, dan Firebase App Distribution.


Workflow ini juga dapat diperluas untuk mendukung penerbitan langsung ke Play Store di masa mendatang.
