package com.github.cwramirezg.crm.teacher.presentation.create

sealed interface CreateEvent {
    data class updateName(val name: String) : CreateEvent
    data class updateDescription(val description: String) : CreateEvent
    object createCourse : CreateEvent
}