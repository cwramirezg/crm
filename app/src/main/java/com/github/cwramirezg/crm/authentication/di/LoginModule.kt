package com.github.cwramirezg.crm.authentication.di

import com.github.cwramirezg.crm.authentication.data.repository.LoginRepositoryImpl
import com.github.cwramirezg.crm.authentication.data.repository.RegisterRepositoryImpl
import com.github.cwramirezg.crm.authentication.domain.login.usecase.GetLoginUseCase
import com.github.cwramirezg.crm.authentication.domain.login.usecase.LoginUseCases
import com.github.cwramirezg.crm.authentication.domain.register.usecase.GetRegisterUseCase
import com.github.cwramirezg.crm.authentication.domain.register.usecase.RegisterUseCases
import com.github.cwramirezg.crm.authentication.domain.repository.LoginRepository
import com.github.cwramirezg.crm.authentication.domain.repository.RegisterRepository
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
    fun provideGetRegisterUseCase(repository: RegisterRepository): GetRegisterUseCase {
        return GetRegisterUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideRegisterUseCase(getRegisterUseCase: GetRegisterUseCase): RegisterUseCases {
        return RegisterUseCases(getRegisterUseCase)
    }

    @Singleton
    @Provides
    fun provideLoginRepository(auth: FirebaseAuth): LoginRepository {
        return LoginRepositoryImpl(auth)
    }

    @Singleton
    @Provides
    fun provideRegisterRepository(
        auth: FirebaseAuth,
        firestore: FirebaseFirestore
    ): RegisterRepository {
        return RegisterRepositoryImpl(auth, firestore)
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
