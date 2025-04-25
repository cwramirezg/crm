package com.github.cwramirezg.crm.authentication.domain.login.usecase

import com.github.cwramirezg.crm.authentication.domain.repository.LoginRepository

class GetLoginUseCase(
    private val repository: LoginRepository
) {
    suspend operator fun invoke(username: String, password: String) =
        repository.login(username, password)
}