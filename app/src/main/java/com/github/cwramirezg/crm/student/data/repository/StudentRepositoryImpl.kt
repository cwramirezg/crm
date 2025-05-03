package com.github.cwramirezg.crm.student.data.repository

import com.github.cwramirezg.crm.core.data.model.Course
import com.github.cwramirezg.crm.student.domain.repository.StudentRepository
import com.google.firebase.firestore.FirebaseFirestore

class StudentRepositoryImpl(
    val firestore: FirebaseFirestore
) : StudentRepository {
    override suspend fun getCourses(
        uid: String,
        onComplete: (List<Course>) -> Unit
    ) {
        TODO("Not yet implemented")
    }
}