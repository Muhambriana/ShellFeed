package com.mshell.shellfeed.ui.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.unit.sp
import com.mshell.shellfeed.R

val Provider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs
)

val PoppinsFamily = FontFamily(
    Font(googleFont = GoogleFont("Poppins"), fontProvider = Provider),
    Font(googleFont = GoogleFont("Poppins"), fontProvider = Provider, weight = FontWeight.Bold),
    Font(googleFont = GoogleFont("Poppins"), fontProvider = Provider, weight = FontWeight.SemiBold),
)
val InterFamily = FontFamily(
    Font(googleFont = GoogleFont("Inter"), fontProvider = Provider),
    Font(googleFont = GoogleFont("Inter"), fontProvider = Provider, weight = FontWeight.Bold),
    Font(googleFont = GoogleFont("Inter"), fontProvider = Provider, weight = FontWeight.SemiBold),
)
val DMSansFamily = FontFamily(
    Font(googleFont = GoogleFont("DM Sans"), fontProvider = Provider),
    Font(googleFont = GoogleFont("DM Sans"), fontProvider = Provider, weight = FontWeight.Bold),
    Font(googleFont = GoogleFont("DM Sans"), fontProvider = Provider, weight = FontWeight.SemiBold),
)

val baseline = Typography()

val Typography = Typography(
    displayLarge = baseline.displayLarge.copy(fontFamily = DMSansFamily),
    displayMedium = baseline.displayMedium.copy(fontFamily = DMSansFamily),
    displaySmall = baseline.displaySmall.copy(fontFamily = DMSansFamily),
    headlineLarge = baseline.headlineLarge.copy(fontFamily = DMSansFamily),
    headlineMedium = baseline.headlineMedium.copy(fontFamily = DMSansFamily),
    headlineSmall = baseline.headlineSmall.copy(fontFamily = DMSansFamily),
    titleLarge = baseline.titleLarge.copy(fontFamily = DMSansFamily),
    titleMedium = baseline.titleMedium.copy(fontFamily = DMSansFamily),
    titleSmall = baseline.titleSmall.copy(fontFamily = DMSansFamily),
    bodyLarge = baseline.bodyLarge.copy(fontFamily = DMSansFamily),
    bodyMedium = baseline.bodyMedium.copy(fontFamily = DMSansFamily),
    bodySmall = baseline.bodySmall.copy(fontFamily = DMSansFamily),
    labelLarge = baseline.labelLarge.copy(fontFamily = DMSansFamily),
    labelMedium = baseline.labelMedium.copy(fontFamily = DMSansFamily),
    labelSmall = baseline.labelSmall.copy(fontFamily = DMSansFamily)
)