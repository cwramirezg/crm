package com.github.cwramirezg.crm.authentication.di

import com.github.cwramirezg.crm.authentication.data.repository.LoginRepositoryImpl
import com.github.cwramirezg.crm.authentication.domain.login.usecase.GetLoginUseCase
import com.github.cwramirezg.crm.authentication.domain.login.usecase.LoginUseCases
import com.github.cwramirezg.crm.authentication.domain.repository.LoginRepository
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LoginModule {

    @Singleton
    @Provides
    fun provideGetLoginUseCase(repository: LoginRepository): GetLoginUseCase {
        return GetLoginUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideLoginUseCase(getLoginUseCase: GetLoginUseCase): LoginUseCases {
        return LoginUseCases(getLoginUseCase)
    }

    @Singleton
    @Provides
    fun provideLoginRepository(auth: FirebaseAuth): LoginRepository {
        return LoginRepositoryImpl(auth)
    }

    @Singleton
    @Provides
    fun provideFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

    @Singleton
    @Provides
    fun provideFirebaseFirestore(): FirebaseFirestore {
        return Firebase.firestore
    }
}