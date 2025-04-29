package com.github.cwramirezg.crm.authentication.data.repository

import com.github.cwramirezg.crm.authentication.domain.repository.RegisterRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class RegisterRepositoryImpl(
    val auth: FirebaseAuth,
    val firestore: FirebaseFirestore
) : RegisterRepository {
    override suspend fun register(
        name: String,
        email: String,
        password: String,
        role: String,
        onComplete: (Boolean, String?) -> Unit
    ) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val uid = auth.currentUser?.uid ?: ""
                    val userData = hashMapOf(
                        "name" to name,
                        "email" to email,
                        "role" to role
                    )
                    firestore.collection("users").document(uid)
                        .set(userData)
                        .addOnCompleteListener { firestoreTask ->
                            onComplete(firestoreTask.isSuccessful, firestoreTask.exception?.message)
                        }
                } else {
                    onComplete(false, task.exception?.message)
                }
            }
    }
}