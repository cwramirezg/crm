package com.github.cwramirezg.crm.teacher.domain.usecases.course

import com.github.cwramirezg.crm.teacher.domain.repository.TeacherRepository

class CreateCourseUseCase(
    private val repository: TeacherRepository
) {
    suspend operator fun invoke(
        name: String,
        description: String,
        createdBy: String,
        onComplete: (Boolean, String) -> Unit
    ) = repository.createCourse(name, description, createdBy, onComplete)
}
