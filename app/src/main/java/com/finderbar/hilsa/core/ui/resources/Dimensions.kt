package com.finderbar.hilsa.core.ui.resources

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Immutable
data class Dimensions(
    val toolbarHeight: Dp = 56.dp,
    val minButtonHeight: Dp = 48.dp,
    val cardElevation: Dp = 4.dp,
    val iconSizeSmall: Dp = 24.dp,
    val iconSizeMedium: Dp = 32.dp,
    val iconSizeLarge: Dp = 48.dp
)

val LocalDimensions = staticCompositionLocalOf { Dimensions() }
