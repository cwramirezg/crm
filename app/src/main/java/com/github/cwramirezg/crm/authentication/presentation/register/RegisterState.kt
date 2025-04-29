package com.github.cwramirezg.crm.authentication.presentation.register

data class RegisterState(
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val role: String = "",
    val isLoggedIn: Boolean = false,
    val isLoading: Boolean = false,
    val error: String = "",
)
