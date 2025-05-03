package com.github.cwramirezg.crm.teacher.presentation.student

sealed interface StudentEvent {
    data class addStudentToCourse(val studentId: String) : StudentEvent
    object getStudents : StudentEvent
}