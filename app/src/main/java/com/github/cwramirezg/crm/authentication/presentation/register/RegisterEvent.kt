package com.github.cwramirezg.crm.authentication.presentation.register

sealed interface RegisterEvent {
    data class updateName(val name: String) : RegisterEvent
    data class updateEmail(val email: String) : RegisterEvent
    data class updatePassword(val password: String) : RegisterEvent
    data class updateConfirmPassword(val confirmPassword: String) : RegisterEvent
    data class updateRole(val role: String) : RegisterEvent
    object submit : RegisterEvent
}