package com.github.cwramirezg.crm.teacher.domain.usecases

import com.github.cwramirezg.crm.teacher.domain.usecases.course.CreateCourseUseCase
import com.github.cwramirezg.crm.teacher.domain.usecases.course.GetCoursesUseCase
import com.github.cwramirezg.crm.teacher.domain.usecases.student.AddStudentToCourseUseCase
import com.github.cwramirezg.crm.teacher.domain.usecases.student.GetAllStudentsUseCase
import com.github.cwramirezg.crm.teacher.domain.usecases.student.GetStudentsInCourseUseCase

data class TeacherUseCases(
    val createCourse: CreateCourseUseCase,
    val getCourses: GetCoursesUseCase,
    val getStudentsInCourse: GetStudentsInCourseUseCase,
    val getAllStudents: GetAllStudentsUseCase,
    val addStudentToCourse: AddStudentToCourseUseCase,
)
