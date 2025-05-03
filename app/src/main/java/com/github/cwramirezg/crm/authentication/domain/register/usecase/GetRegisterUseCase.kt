package com.github.cwramirezg.crm.authentication.domain.register.usecase

import com.github.cwramirezg.crm.authentication.domain.repository.RegisterRepository

class GetRegisterUseCase(
    private val repository: RegisterRepository
) {
    suspend operator fun invoke(
        name: String,
        email: String,
        password: String,
        role: String,
        onComplete: (Boolean, String?) -> Unit
    ) = repository.register(name, email, password, role, onComplete)
}
