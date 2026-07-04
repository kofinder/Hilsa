package com.finderbar.hilsa.feature.auth.login

/**
 * Author: Ko Thein (Nathan Mratt)
 * Created: 7/4/2026
 * Project: Hilsa
 */
data class LoginState(
    val username: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val isPasswordVisible: Boolean = false,
    val usernameError: String? = null,
    val passwordError: String? = null,
    val errorMessage: String? = null,
    val isLoggedIn: Boolean = false
)