package com.finderbar.hilsa.feature.auth.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.finderbar.hilsa.core.common.Result
import com.finderbar.hilsa.domain.usecase.auth.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for the Login screen.
 * Handles user interaction events and coordinates with domain use cases.
 */
@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    var state by mutableStateOf(LoginState())
        private set

    /**
     * Entry point for all UI events on the login screen.
     */
    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.UsernameChanged -> {
                state = state.copy(username = event.value)
            }
            is LoginEvent.PasswordChanged -> {
                state = state.copy(password = event.value)
            }
            LoginEvent.TogglePasswordVisibility -> {
                state = state.copy(
                    isPasswordVisible = !state.isPasswordVisible
                )
            }
            LoginEvent.ToggleRememberMe -> {
                state = state.copy(
                    rememberMe = !state.rememberMe
                )
            }
            LoginEvent.LoginClicked -> {
                performLogin()
            }
        }
    }

    private fun performLogin() {
        if (!isInputValid()) return

        // For demonstration: Check for demo/test credentials
        if ((state.username == "demo" && state.password == "demo") || 
            (state.username == "test" && state.password == "test")) {
            state = state.copy(isLoading = false, isLoggedIn = true)
            return
        }

        viewModelScope.launch {
            state = state.copy(isLoading = true, errorMessage = null)
            
            val result = loginUseCase(
                email = state.username,
                password = state.password
            )

            state = when (result) {
                is Result.Success -> {
                    state.copy(isLoading = false, isLoggedIn = true)
                }
                is Result.Error -> {
                    state.copy(isLoading = false, errorMessage = result.exception.message)
                }
                Result.Loading -> {
                    state.copy(isLoading = true)
                }
            }
        }
    }

    private fun isInputValid(): Boolean {
        return if (state.username.isBlank() || state.password.isBlank()) {
            state = state.copy(errorMessage = "Please enter both email and password")
            false
        } else {
            true
        }
    }
}
