package com.github.cwramirezg.crm.teacher.presentation.student

import com.github.cwramirezg.crm.core.data.model.User

data class StudentState(
    val students: List<User> = emptyList(),
)