package com.finderbar.hilsa.core.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.finderbar.hilsa.core.ui.resources.Dimensions
import com.finderbar.hilsa.core.ui.resources.LocalDimensions
import com.finderbar.hilsa.core.ui.resources.LocalSpacing
import com.finderbar.hilsa.core.ui.resources.Spacing

/**
 * Supported theme types for the application.
 */
enum class AppTheme {
    DARK, WHITE, ORANGE, BLUE
}

private val DarkColorScheme = darkColorScheme(
    primary = DarkPrimary,
    onPrimary = Color.Black,
    secondary = PurpleGrey80,
    tertiary = Pink80,
    background = DarkBackground,
    onBackground = Color.White,
    surface = DarkSurface,
    onSurface = Color.White
)

private val WhiteColorScheme = lightColorScheme(
    primary = WhitePrimary,
    onPrimary = Color.White,
    secondary = PurpleGrey40,
    tertiary = Pink40,
    background = WhiteBackground,
    onBackground = Color.Black,
    surface = WhiteSurface,
    onSurface = Color.Black
)

private val OrangeColorScheme = lightColorScheme(
    primary = OrangePrimary,
    onPrimary = Color.White,
    secondary = OrangeSecondary,
    tertiary = OrangeTertiary,
    background = Color(0xFFFFF8E1),
    onBackground = Color.Black,
    surface = Color(0xFFFFFDE7),
    onSurface = Color.Black
)

private val BlueColorScheme = lightColorScheme(
    primary = EnterpriseBlue,
    onPrimary = Color.White,
    secondary = EnterpriseBlueSecondary,
    onSecondary = Color.White,
    tertiary = EnterpriseBlueLight,
    onTertiary = Color.White,
    background = Color.White,
    onBackground = Color.Black,
    surface = Color.White,
    onSurface = Color.Black,
    primaryContainer = Color(0xFFE3F2FD),
    onPrimaryContainer = EnterpriseBlue
)

/**
 * Accessor for the application's custom design system tokens (Spacing, Dimensions).
 */
object AppDesignSystem {
    val spacing: Spacing
        @Composable
        get() = LocalSpacing.current

    val dimensions: Dimensions
        @Composable
        get() = LocalDimensions.current
}

/**
 * The root theme for the Hilsa application.
 * Configures Material 3 color schemes, typography, and custom design tokens.
 */
@Composable
fun HilsaTheme(
    themeType: AppTheme = if (isSystemInDarkTheme()) AppTheme.DARK else AppTheme.BLUE,
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when (themeType) {
        AppTheme.DARK -> DarkColorScheme
        AppTheme.WHITE -> WhiteColorScheme
        AppTheme.ORANGE -> OrangeColorScheme
        AppTheme.BLUE -> BlueColorScheme
    }

    val finalColorScheme = if (dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        val context = LocalContext.current
        if (themeType == AppTheme.DARK) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
    } else {
        colorScheme
    }

    CompositionLocalProvider(
        LocalSpacing provides Spacing(),
        LocalDimensions provides Dimensions()
    ) {
        MaterialTheme(
            colorScheme = finalColorScheme,
            typography = Typography,
            shapes = Shapes,
            content = content
        )
    }
}
