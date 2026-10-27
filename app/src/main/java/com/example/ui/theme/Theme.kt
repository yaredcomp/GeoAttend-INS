package com.example.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val LightColorScheme = lightColorScheme(
    primary = DuForestGreen,
    onPrimary = DuTextInverse,
    primaryContainer = DuGreenContainer,
    onPrimaryContainer = DuOnGreenContainer,
    secondary = DuGold,
    onSecondary = DuTextInverse,
    secondaryContainer = DuGoldLight,
    onSecondaryContainer = DuGoldDark,
    tertiary = DuSuccessEmerald,
    onTertiary = DuTextInverse,
    tertiaryContainer = DuSuccessLight,
    onTertiaryContainer = DuSuccessEmerald,
    error = DuAlertCrimson,
    onError = DuTextInverse,
    errorContainer = DuCrimsonLight,
    onErrorContainer = DuCrimsonDark,
    background = DuSurfaceCanvas,
    onBackground = DuTextPrimary,
    surface = DuSurfaceCard,
    onSurface = DuTextPrimary,
    surfaceVariant = DuSurfaceSubtle,
    onSurfaceVariant = DuTextSecondary,
    outline = DuSurfaceBorder
)

@Composable
fun GeoAttendTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = LightColorScheme
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as? Activity)?.window
            if (window != null) {
                window.statusBarColor = DuForestGreen.toArgb()
                WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = false
            }
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
