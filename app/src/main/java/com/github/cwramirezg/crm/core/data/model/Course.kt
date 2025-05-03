package com.github.cwramirezg.crm.core.data.model

import com.google.firebase.firestore.DocumentSnapshot

data class Course(
    val id: String,
    val name: String,
    val description: String,
    val createdBy: String,
    val students: List<String>
) {
    companion object {
        fun fromDoc(doc: DocumentSnapshot): Course {
            return Course(
                id = doc.id,
                name = doc.getString("name") ?: "",
                description = doc.getString("description") ?: "",
                createdBy = doc.getString("createdBy") ?: "",
                students = doc.get("students") as? List<String> ?: emptyList()
            )
        }
    }
}