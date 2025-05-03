package com.github.cwramirezg.crm.core.data.model

import com.google.firebase.firestore.DocumentSnapshot

data class User(
    val uid: String,
    val name: String,
    val email: String
) {
    companion object {
        fun fromDoc(doc: DocumentSnapshot): User {
            return User(
                uid = doc.id,
                name = doc.getString("name") ?: "",
                email = doc.getString("email") ?: ""
            )
        }
    }
}