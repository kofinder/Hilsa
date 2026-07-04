package com.finderbar.hilsa.feature.auth.login

/**
 * Author: Ko Thein (Nathan Mratt)
 * Created: 7/4/2026
 * Project: Hilsa
 */
sealed interface LoginEvent {

    data class UsernameChanged(val value: String) : LoginEvent

    data class PasswordChanged(val value: String) : LoginEvent

    data object TogglePasswordVisibility : LoginEvent

    data object LoginClicked : LoginEvent
}