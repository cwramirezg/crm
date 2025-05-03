package com.github.cwramirezg.crm.student.domain.usecases.get

import com.github.cwramirezg.crm.core.data.model.Course
import com.github.cwramirezg.crm.student.domain.repository.StudentRepository
import com.github.cwramirezg.crm.teacher.domain.repository.TeacherRepository

class GetCoursesUseCase(
    private val repository: StudentRepository
) {
    suspend fun invoke(
        uid: String,
        onComplete: (List<Course>) -> Unit
    ) = repository.getCourses(uid, onComplete)
}
