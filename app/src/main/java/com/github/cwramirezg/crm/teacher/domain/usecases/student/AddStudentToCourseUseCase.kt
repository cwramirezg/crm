package com.github.cwramirezg.crm.teacher.domain.usecases.student

import com.github.cwramirezg.crm.teacher.domain.repository.TeacherRepository

class AddStudentToCourseUseCase(
    private val repository: TeacherRepository
) {
    suspend operator fun invoke(
        courseId: String,
        studentId: String,
        onComplete: (Boolean, String) -> Unit
    ) = repository.addStudentToCourse(courseId, studentId, onComplete)
}