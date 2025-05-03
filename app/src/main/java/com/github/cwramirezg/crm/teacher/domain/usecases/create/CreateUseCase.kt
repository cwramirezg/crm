package com.github.cwramirezg.crm.teacher.domain.usecases.create

import com.github.cwramirezg.crm.teacher.domain.repository.TeacherRepository

class CreateUseCase(
    private val repository: TeacherRepository
) {
    suspend operator fun invoke(
        name: String,
        description: String,
        createdBy: String,
        onComplete: (Boolean, String) -> Unit
    ) = repository.create(name, description, createdBy, onComplete)
}
