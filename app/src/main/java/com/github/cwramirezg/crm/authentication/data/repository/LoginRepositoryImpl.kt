package com.github.cwramirezg.crm.authentication.data.repository

import com.github.cwramirezg.crm.authentication.domain.repository.LoginRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.tasks.await

class LoginRepositoryImpl(
    val auth: FirebaseAuth
) : LoginRepository {
    override suspend fun login(email: String, password: String): Result<FirebaseUser?> {
        return try {
            val result = auth.signInWithEmailAndPassword(email, password).await()
            Result.success(result.user)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
