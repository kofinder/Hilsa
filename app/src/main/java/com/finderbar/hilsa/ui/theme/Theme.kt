package com.finderbar.hilsa.ui.theme

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

enum class AppTheme {
    DARK, WHITE, ORANGE, BLUE
}

private val DarkColorScheme = darkColorScheme(
    primary = DarkPrimary,
    secondary = PurpleGrey80,
    tertiary = Pink80,
    background = DarkBackground,
    surface = DarkSurface
)

private val WhiteColorScheme = lightColorScheme(
    primary = WhitePrimary,
    secondary = PurpleGrey40,
    tertiary = Pink40,
    background = WhiteBackground,
    surface = WhiteSurface
)

private val OrangeColorScheme = lightColorScheme(
    primary = OrangePrimary,
    secondary = OrangeSecondary,
    tertiary = OrangeTertiary,
    background = Color(0xFFFFF8E1),
    surface = Color(0xFFFFFDE7)
)

private val BlueColorScheme = lightColorScheme(
    primary = EnterpriseBlue,
    secondary = EnterpriseBlueSecondary,
    tertiary = EnterpriseBlueLight,
    background = Color(0xFFE3F2FD),
    surface = Color(0xFFF1F8E9)
)

@Composable
fun HilsaTheme(
    themeType: AppTheme = if (isSystemInDarkTheme()) AppTheme.DARK else AppTheme.BLUE, // Blue as enterprise default
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when (themeType) {
        AppTheme.DARK -> DarkColorScheme
        AppTheme.WHITE -> WhiteColorScheme
        AppTheme.ORANGE -> OrangeColorScheme
        AppTheme.BLUE -> BlueColorScheme
    }

    // Dynamic color logic (optional, keeping it but defaulting to false for enterprise look)
    val finalColorScheme = if (dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        val context = LocalContext.current
        if (themeType == AppTheme.DARK) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
    } else {
        colorScheme
    }

    MaterialTheme(
        colorScheme = finalColorScheme,
        typography = Typography,
        content = content
    )
}
