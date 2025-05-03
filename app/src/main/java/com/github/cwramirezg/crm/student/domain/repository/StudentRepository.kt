package com.github.cwramirezg.crm.student.domain.repository

import com.github.cwramirezg.crm.core.data.model.Course

interface StudentRepository {
    suspend fun getCourses(
        uid: String,
        onComplete: (List<Course>) -> Unit
    )
}