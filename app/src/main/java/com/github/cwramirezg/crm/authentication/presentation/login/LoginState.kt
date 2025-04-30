package com.github.cwramirezg.crm.authentication.presentation.login

data class LoginState(
    val email: String = "",
    val password: String = "",
    val uid: String = "",
    val isLoggedIn: Boolean = false,
    val isLoading: Boolean = false,
    val error: String = "",
)
