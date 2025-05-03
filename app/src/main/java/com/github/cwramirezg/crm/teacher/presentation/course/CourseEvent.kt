package com.github.cwramirezg.crm.teacher.presentation.course

sealed interface CourseEvent {
    object getStudentsInCourse : CourseEvent
}
