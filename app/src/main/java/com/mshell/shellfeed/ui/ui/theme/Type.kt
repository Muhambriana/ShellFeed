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
    Font(googleFont = GoogleFont("Poppins"), fontProvider = Provider)
)
val InterFamily = FontFamily(
    Font(googleFont = GoogleFont("Inter"), fontProvider = Provider)
)
val DMSansFamily = FontFamily(
    Font(googleFont = GoogleFont("DM Sans"), fontProvider = Provider)
)

val Typography = Typography(
    displayLarge = TextStyle(fontFamily = PoppinsFamily, color = androidx.compose.ui.graphics.Color.Cyan),
    displayMedium = TextStyle(fontFamily = PoppinsFamily, color = androidx.compose.ui.graphics.Color.Cyan),
    displaySmall = TextStyle(fontFamily = PoppinsFamily, color = androidx.compose.ui.graphics.Color.Cyan),
    headlineLarge = TextStyle(fontFamily = PoppinsFamily, color = androidx.compose.ui.graphics.Color.Cyan),
    headlineMedium = TextStyle(fontFamily = PoppinsFamily, color = androidx.compose.ui.graphics.Color.Cyan),
    headlineSmall = TextStyle(fontFamily = PoppinsFamily, color = androidx.compose.ui.graphics.Color.Cyan),
    titleLarge = TextStyle(fontFamily = PoppinsFamily, color = androidx.compose.ui.graphics.Color.Cyan),
    titleMedium = TextStyle(fontFamily = PoppinsFamily, color = androidx.compose.ui.graphics.Color.Cyan),
    titleSmall = TextStyle(fontFamily = PoppinsFamily, color = androidx.compose.ui.graphics.Color.Cyan),
    bodyLarge = TextStyle(
        fontFamily = PoppinsFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp,
        color = androidx.compose.ui.graphics.Color.Cyan
    ),
    bodyMedium = TextStyle(fontFamily = PoppinsFamily, color = androidx.compose.ui.graphics.Color.Cyan),
    bodySmall = TextStyle(fontFamily = PoppinsFamily, color = androidx.compose.ui.graphics.Color.Cyan),
    labelLarge = TextStyle(fontFamily = PoppinsFamily, color = androidx.compose.ui.graphics.Color.Cyan),
    labelMedium = TextStyle(fontFamily = PoppinsFamily, color = androidx.compose.ui.graphics.Color.Cyan),
    labelSmall = TextStyle(fontFamily = PoppinsFamily, color = androidx.compose.ui.graphics.Color.Cyan)
)