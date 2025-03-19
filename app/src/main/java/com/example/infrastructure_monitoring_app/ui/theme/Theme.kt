package com.example.infrastructure_monitoring_app.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.material.Shapes
import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.unit.sp

@Composable
fun InfrastructureMonitoringAppTheme(content: @Composable () -> Unit) {
    val colors = lightColors(
        primary = Color(0xFF0288D1), // Biru modern untuk tombol dan aksen
        primaryVariant = Color(0xFF01579B),
        secondary = Color(0xFF26A69A),
        background = Color(0xFFF5F5F5), // Abu-abu muda untuk latar
        surface = Color.White,
        error = Color(0xFFD32F2F),
        onPrimary = Color.White,
        onSecondary = Color.Black,
        onBackground = Color.Black,
        onSurface = Color.Black,
        onError = Color.White
    )

    val shapes = Shapes(
        small = RoundedCornerShape(8.dp),
        medium = RoundedCornerShape(12.dp),
        large = RoundedCornerShape(16.dp)
    )

    val typography = Typography(
        body1 = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp
        ),
        button = TextStyle(
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp
        ),
        h6 = TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
    )

    MaterialTheme(
        colors = colors,
        typography = typography,
        shapes = shapes,
        content = content
    )
}