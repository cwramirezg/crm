package com.github.cwramirezg.crm.teacher.presentation.home

import com.github.cwramirezg.crm.core.data.model.Course

data class HomeTeacherState(
    val courses: List<Course> = emptyList(),
)