package com.finderbar.hilsa.core.ui.layout

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.finderbar.hilsa.core.ui.components.LoadingIndicator

/**
 * A standardized layout for all screens in the application.
 * Automatically handles the TopBar, BottomBar, Snackbar hosting, and full-screen loading overlays.
 *
 * @param modifier Modifier for the root layout.
 * @param topBar Composable to display as the top bar.
 * @param bottomBar Composable to display as the bottom bar.
 * @param snackbarHostState State for the snackbar host.
 * @param isLoading When true, displays a semi-transparent loading overlay.
 * @param containerColor The background color of the screen.
 * @param content The main content of the screen.
 */
@Composable
fun AppScreen(
    modifier: Modifier = Modifier,
    topBar: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() },
    isLoading: Boolean = false,
    containerColor: Color = MaterialTheme.colorScheme.background,
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = topBar,
        bottomBar = bottomBar,
        snackbarHost = { SnackbarHost(snackbarHostState) },
        containerColor = containerColor
    ) { paddingValues ->
        Box(modifier = Modifier.fillMaxSize()) {
            content(paddingValues)
            if (isLoading) {
                LoadingIndicator()
            }
        }
    }
}
