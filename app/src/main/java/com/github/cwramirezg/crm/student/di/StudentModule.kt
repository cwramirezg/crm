package com.github.cwramirezg.crm.student.di

import com.github.cwramirezg.crm.student.data.repository.StudentRepositoryImpl
import com.github.cwramirezg.crm.student.domain.repository.StudentRepository
import com.github.cwramirezg.crm.student.domain.usecases.StudentUseCases
import com.github.cwramirezg.crm.student.domain.usecases.get.GetCoursesUseCase
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object StudentModule {

    @Singleton
    @Provides
    fun provideGetCoursesUseCase(repository: StudentRepository): GetCoursesUseCase {
        return GetCoursesUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideStudentUseCases(getCoursesUseCase: GetCoursesUseCase): StudentUseCases {
        return StudentUseCases(getCoursesUseCase)
    }

    @Singleton
    @Provides
    fun provideStudentRepository(firestore: FirebaseFirestore): StudentRepository {
        return StudentRepositoryImpl(firestore)
    }
}