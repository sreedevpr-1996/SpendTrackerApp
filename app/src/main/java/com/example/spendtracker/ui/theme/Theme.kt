package com.example.spendtracker.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = Purple80, secondary = PurpleGrey80, tertiary = Pink80,
    background = Color(0xFF3D0586)

)


@Composable
fun SpendTrackerTheme(
    content: @Composable () -> Unit
) {
    val colorScheme = DarkColorScheme



    MaterialTheme(
        colorScheme = colorScheme, typography = Typography, content = content
    )
}