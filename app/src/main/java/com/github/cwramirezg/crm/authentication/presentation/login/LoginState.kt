package com.github.cwramirezg.crm.authentication.presentation.login

data class LoginState(
    val username: String = "",
    val password: String = "",
    val isLoggedIn: Boolean = false,
    val isLoading: Boolean = false,
    val error: String = "",
)