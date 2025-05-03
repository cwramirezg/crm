package com.github.cwramirezg.crm.teacher.di

import com.github.cwramirezg.crm.teacher.data.repository.TeacherRepositoryImpl
import com.github.cwramirezg.crm.teacher.domain.repository.TeacherRepository
import com.github.cwramirezg.crm.teacher.domain.usecases.TeacherUseCases
import com.github.cwramirezg.crm.teacher.domain.usecases.create.CreateUseCase
import com.github.cwramirezg.crm.teacher.domain.usecases.get.GetCoursesUseCase
import com.github.cwramirezg.crm.teacher.domain.usecases.students.GetAllStudentsUseCase
import com.github.cwramirezg.crm.teacher.domain.usecases.students.GetStudentsUseCase
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
    fun provideCreateUseCase(repository: TeacherRepository): CreateUseCase {
        return CreateUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideGetCoursesUseCase(repository: TeacherRepository): GetCoursesUseCase {
        return GetCoursesUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideGetStudentsUseCase(repository: TeacherRepository): GetStudentsUseCase {
        return GetStudentsUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideGetAllStudentsUseCase(repository: TeacherRepository): GetAllStudentsUseCase {
        return GetAllStudentsUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideTeacherUseCases(
        createUseCase: CreateUseCase,
        getCoursesUseCase: GetCoursesUseCase,
        getStudentsUseCase: GetStudentsUseCase,
        getAllStudents: GetAllStudentsUseCase,
    ): TeacherUseCases {
        return TeacherUseCases(createUseCase, getCoursesUseCase, getStudentsUseCase, getAllStudents)
    }

    @Singleton
    @Provides
    fun provideTeacherRepository(firestore: FirebaseFirestore): TeacherRepository {
        return TeacherRepositoryImpl(firestore)
    }
}
