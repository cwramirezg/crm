package com.github.cwramirezg.crm.teacher.presentation.create

sealed interface CreateCourseEvent {
    data class updateName(val name: String) : CreateCourseEvent
    data class updateDescription(val description: String) : CreateCourseEvent
    object createCourse : CreateCourseEvent
}