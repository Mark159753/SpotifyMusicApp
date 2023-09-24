package com.example.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.ui.R

val gothamFontFamily = FontFamily(
    Font(R.font.gotham_regular, FontWeight.Normal),
    Font(R.font.gotham_bold, FontWeight.Bold),
    Font(R.font.gotham_medium, FontWeight.Medium),
    Font(R.font.gotham_light, FontWeight.Light),
)

val typography = Typography(
    labelSmall = TextStyle(fontFamily = gothamFontFamily, fontWeight = FontWeight.Medium, fontSize = 11.sp),
    labelMedium = TextStyle(fontFamily = gothamFontFamily, fontWeight = FontWeight.Medium, fontSize = 12.sp),
    labelLarge = TextStyle(fontFamily = gothamFontFamily, fontWeight = FontWeight.Medium, fontSize = 14.sp),
    bodySmall = TextStyle(fontFamily = gothamFontFamily, fontWeight = FontWeight.Normal, fontSize = 12.sp),
    bodyMedium = TextStyle(fontFamily = gothamFontFamily, fontWeight = FontWeight.Normal, fontSize = 14.sp),
    bodyLarge = TextStyle(fontFamily = gothamFontFamily, fontWeight = FontWeight.Normal, fontSize = 16.sp),
    titleSmall = TextStyle(fontFamily = gothamFontFamily, fontWeight = FontWeight.Medium, fontSize = 14.sp),
    titleMedium = TextStyle(fontFamily = gothamFontFamily, fontWeight = FontWeight.Medium, fontSize = 16.sp),
    titleLarge = TextStyle(fontFamily = gothamFontFamily, fontWeight = FontWeight.Medium, fontSize = 22.sp),
    headlineSmall = TextStyle(fontFamily = gothamFontFamily, fontWeight = FontWeight.Bold, fontSize = 22.sp),
    headlineMedium = TextStyle(fontFamily = gothamFontFamily, fontWeight = FontWeight.Bold, fontSize = 28.sp),
    headlineLarge = TextStyle(fontFamily = gothamFontFamily, fontWeight = FontWeight.Bold, fontSize = 32.sp),
    displaySmall = TextStyle(fontFamily = gothamFontFamily, fontWeight = FontWeight.Bold, fontSize = 36.sp),
    displayMedium = TextStyle(fontFamily = gothamFontFamily, fontWeight = FontWeight.Bold, fontSize = 45.sp),
    displayLarge = TextStyle(fontFamily = gothamFontFamily, fontWeight = FontWeight.Bold, fontSize = 57.sp)
)