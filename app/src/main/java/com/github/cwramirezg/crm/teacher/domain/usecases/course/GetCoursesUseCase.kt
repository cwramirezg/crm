package com.github.cwramirezg.crm.teacher.domain.usecases.course

import com.github.cwramirezg.crm.core.data.model.Course
import com.github.cwramirezg.crm.teacher.domain.repository.TeacherRepository

class GetCoursesUseCase(
    private val repository: TeacherRepository
) {
    suspend operator fun invoke(
        uid: String,
        onComplete: (List<Course>) -> Unit
    ) = repository.getCourses(uid, onComplete)
}