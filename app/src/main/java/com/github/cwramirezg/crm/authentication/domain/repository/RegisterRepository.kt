package com.github.cwramirezg.crm.authentication.domain.repository

interface RegisterRepository {
    suspend fun register(
        name: String,
        email: String,
        password: String,
        role: String,
        onComplete: (Boolean, String?) -> Unit
    )
}