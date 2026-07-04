package com.finderbar.hilsa.feature.auth

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.finderbar.hilsa.feature.auth.login.LoginScreen
import com.finderbar.hilsa.feature.auth.login.LoginViewModel

/**
 * Author: Ko Thein (Nathan Mratt)
 * Created: 7/4/2026
 * Project: Hilsa
 */
@Composable
fun LoginRoute(
    onLoginSuccess: () -> Unit,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val state = viewModel.state
    LoginScreen(
        state = state,
        onEvent = viewModel::onEvent,
        onLoginSuccess = onLoginSuccess
    )
}