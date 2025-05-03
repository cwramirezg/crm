package com.github.cwramirezg.crm.teacher.presentation.home

sealed interface HomeTeacherEvent {
    object getCourses : HomeTeacherEvent
}