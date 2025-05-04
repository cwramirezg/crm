package com.github.cwramirezg.crm.teacher.di

import com.github.cwramirezg.crm.teacher.data.repository.TeacherRepositoryImpl
import com.github.cwramirezg.crm.teacher.domain.repository.TeacherRepository
import com.github.cwramirezg.crm.teacher.domain.usecases.TeacherUseCases
import com.github.cwramirezg.crm.teacher.domain.usecases.course.CreateCourseUseCase
import com.github.cwramirezg.crm.teacher.domain.usecases.course.GetCoursesUseCase
import com.github.cwramirezg.crm.teacher.domain.usecases.student.AddStudentToCourseUseCase
import com.github.cwramirezg.crm.teacher.domain.usecases.student.GetAllStudentsUseCase
import com.github.cwramirezg.crm.teacher.domain.usecases.student.GetStudentsInCourseUseCase
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TeacherModule {

    @Singleton
    @Provides
    fun provideCreateCourseUseCase(repository: TeacherRepository): CreateCourseUseCase {
        return CreateCourseUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideGetCoursesUseCase(repository: TeacherRepository): GetCoursesUseCase {
        return GetCoursesUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideGetStudentsUseCase(repository: TeacherRepository): GetStudentsInCourseUseCase {
        return GetStudentsInCourseUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideGetAllStudentsUseCase(repository: TeacherRepository): GetAllStudentsUseCase {
        return GetAllStudentsUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideAddStudentToCourseUseCase(repository: TeacherRepository): AddStudentToCourseUseCase {
        return AddStudentToCourseUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideTeacherUseCases(
        createCourseUseCase: CreateCourseUseCase,
        getCoursesUseCase: GetCoursesUseCase,
        getStudentsUseCase: GetStudentsInCourseUseCase,
        getAllStudents: GetAllStudentsUseCase,
        addStudentToCourseUseCase: AddStudentToCourseUseCase
    ): TeacherUseCases {
        return TeacherUseCases(
            createCourseUseCase,
            getCoursesUseCase,
            getStudentsUseCase,
            getAllStudents,
            addStudentToCourseUseCase
        )
    }

    @Singleton
    @Provides
    fun provideTeacherRepository(firestore: FirebaseFirestore): TeacherRepository {
        return TeacherRepositoryImpl(firestore)
    }
}
