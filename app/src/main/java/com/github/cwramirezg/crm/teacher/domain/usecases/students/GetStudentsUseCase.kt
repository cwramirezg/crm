package com.github.cwramirezg.crm.teacher.domain.usecases.students

import com.github.cwramirezg.crm.core.data.model.User
import com.github.cwramirezg.crm.teacher.domain.repository.TeacherRepository

class GetStudentsUseCase(
    private val repository: TeacherRepository
) {
    suspend operator fun invoke(
        courseId: String,
        onComplete: (List<User>) -> Unit
    ) = repository.getStudentsInCourse(courseId, onComplete)
}