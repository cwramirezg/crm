package com.github.cwramirezg.crm.teacher.presentation.course

import com.github.cwramirezg.crm.core.data.model.User

data class CourseState(
    val courseId: String = "",
    val students: List<User> = emptyList(),
)