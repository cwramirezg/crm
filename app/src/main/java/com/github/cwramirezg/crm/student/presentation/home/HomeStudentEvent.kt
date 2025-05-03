package com.github.cwramirezg.crm.student.presentation.home

sealed interface HomeStudentEvent {
    object getCourses : HomeStudentEvent
}