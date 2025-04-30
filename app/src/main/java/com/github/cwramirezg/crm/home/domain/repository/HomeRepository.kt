package com.github.cwramirezg.crm.home.domain.repository

interface HomeRepository {
    suspend fun fetchUserRole(
        uid: String,
        onComplete: (Boolean, String) -> Unit
    )
}