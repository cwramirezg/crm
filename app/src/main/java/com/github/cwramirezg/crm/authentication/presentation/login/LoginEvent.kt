package com.github.cwramirezg.crm.authentication.presentation.login

sealed interface LoginEvent {
    data class updateEmail(val email: String) : LoginEvent
    data class updatePassword(val password: String) : LoginEvent
    data object onLogin : LoginEvent
}