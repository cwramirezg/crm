package com.github.cwramirezg.crm.authentication.presentation.login

data class LoginState(
    val email: String = "asd@gmail.com",
    val password: String = "123456",
    val uid: String = "",
    val isLoggedIn: Boolean = false,
    val isLoading: Boolean = false,
    val error: String = "",
)
