package com.github.cwramirezg.crm.teacher.domain.usecases.student

import com.github.cwramirezg.crm.core.data.model.User
import com.github.cwramirezg.crm.teacher.domain.repository.TeacherRepository

class GetAllStudentsUseCase(
    private val repository: TeacherRepository
) {
    suspend operator fun invoke(
        onComplete: (List<User>) -> Unit
    ) = repository.getAllStudents(onComplete)
}