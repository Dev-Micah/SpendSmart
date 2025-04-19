package com.micahnyabuto.spendsmart.ui.theme

import androidx.compose.material3.R
import androidx.compose.material3.Typography
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight

val productSan = FontFamily(
    Font(R.font.product_san_regular,)
)


val Typography = Typography().run {
    copy(
        displayLarge = displayLarge.copy(
            fontFamily = productSan
        ),
        displayMedium = displayMedium.copy(
            fontFamily = productSan
        ),
        displaySmall = displaySmall.copy(
            fontFamily = productSan
        ),
        headlineLarge = headlineLarge.copy(
            fontFamily = productSan
        ),
        headlineMedium = headlineMedium.copy(
            fontFamily = productSan
        ),
        headlineSmall = headlineSmall.copy(
            fontFamily = productSan
        ),
        titleLarge = titleLarge.copy(
            fontFamily = productSan,
            fontWeight = FontWeight.Bold
        ),
        titleMedium = titleMedium.copy(
            fontFamily = productSan,
            fontWeight = FontWeight.Bold
        ),
        titleSmall = titleSmall.copy(
            fontFamily = productSan,
            fontWeight = FontWeight.Bold
        ),
        bodyLarge = bodyLarge.copy(
            fontFamily = productSan
        ),
        bodyMedium = bodyMedium.copy(
            fontFamily = productSan
        ),
        bodySmall = bodySmall.copy(
            fontFamily = productSan
        ),
        labelLarge = labelLarge.copy(
            fontFamily = productSan
        ),
        labelMedium = labelMedium.copy(
            fontFamily = productSan
        ),
        labelSmall = labelSmall.copy(
            fontFamily = productSan
        ),
    )
}