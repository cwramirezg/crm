package com.github.cwramirezg.crm.teacher.domain.repository

import com.github.cwramirezg.crm.core.data.model.Course
import com.github.cwramirezg.crm.core.data.model.User

interface TeacherRepository {
    suspend fun create(
        name: String,
        description: String,
        createdBy: String,
        onComplete: (Boolean, String) -> Unit
    )

    suspend fun getCourses(
        uid: String,
        onComplete: (List<Course>) -> Unit
    )

    suspend fun getStudentsInCourse(
        courseId: String,
        onComplete: (List<User>) -> Unit
    )

    suspend fun getAllStudents(
        onComplete: (List<User>) -> Unit
    )
}
