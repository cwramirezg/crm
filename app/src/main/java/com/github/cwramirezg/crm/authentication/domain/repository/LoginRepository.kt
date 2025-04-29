package com.github.cwramirezg.crm.authentication.domain.repository

import com.google.firebase.auth.FirebaseUser

interface LoginRepository {
    suspend fun login(email: String, password: String): Result<FirebaseUser?>
}
