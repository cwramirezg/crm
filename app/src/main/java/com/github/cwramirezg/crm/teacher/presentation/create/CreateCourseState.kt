package com.github.cwramirezg.crm.teacher.presentation.create

data class CreateCourseState(
    val uidUser: String = "",
    val name: String = "",
    val description: String = "",
    val isSuccess: Boolean = false,
    val message: String = "",
)