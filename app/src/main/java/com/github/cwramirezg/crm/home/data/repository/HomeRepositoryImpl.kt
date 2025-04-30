package com.github.cwramirezg.crm.home.data.repository

import com.github.cwramirezg.crm.home.domain.repository.HomeRepository
import com.google.firebase.firestore.FirebaseFirestore

class HomeRepositoryImpl(
    val firestore: FirebaseFirestore
) : HomeRepository {
    override suspend fun fetchUserRole(
        uid: String,
        onComplete: (Boolean, String) -> Unit
    ) {
        firestore.collection("users").document(uid).get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    document.getString("role")?.also {
                        onComplete(true, it)
                    }
                }
            }
            .addOnFailureListener {
                onComplete(false, "")
            }
    }
}