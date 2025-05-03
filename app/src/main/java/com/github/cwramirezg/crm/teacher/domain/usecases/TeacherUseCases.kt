package com.github.cwramirezg.crm.teacher.domain.usecases

import com.github.cwramirezg.crm.teacher.domain.usecases.create.CreateUseCase
import com.github.cwramirezg.crm.teacher.domain.usecases.get.GetCoursesUseCase
import com.github.cwramirezg.crm.teacher.domain.usecases.students.GetAllStudentsUseCase
import com.github.cwramirezg.crm.teacher.domain.usecases.students.GetStudentsUseCase

data class TeacherUseCases(
    val create: CreateUseCase,
    val getCourses: GetCoursesUseCase,
    val getStudentsInCourse: GetStudentsUseCase,
    val getAllStudents: GetAllStudentsUseCase,
)
