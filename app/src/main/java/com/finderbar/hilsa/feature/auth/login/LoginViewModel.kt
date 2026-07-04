package com.finderbar.hilsa.feature.auth.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.finderbar.hilsa.core.common.Result
import com.finderbar.hilsa.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    var state by mutableStateOf(LoginState())
        private set

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
            LoginEvent.LoginClicked -> {
                login()
            }
        }
    }

    private fun login() {
        if (state.username.isBlank() || state.password.isBlank()) {
            state = state.copy(errorMessage = "Please enter email and password")
            return
        }

        viewModelScope.launch {
            state = state.copy(isLoading = true, errorMessage = null)
            
            val result = authRepository.login(
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
}