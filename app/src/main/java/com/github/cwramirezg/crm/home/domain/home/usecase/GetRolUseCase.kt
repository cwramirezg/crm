package com.github.cwramirezg.crm.home.domain.home.usecase

import com.github.cwramirezg.crm.home.domain.repository.HomeRepository

class GetRolUseCase(
    private val repository: HomeRepository
) {
    suspend operator fun invoke(
        uid: String,
        onComplete: (Boolean, String) -> Unit
    ) = repository.fetchUserRole(uid, onComplete)
}