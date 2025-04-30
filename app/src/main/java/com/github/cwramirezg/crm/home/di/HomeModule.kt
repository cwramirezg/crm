package com.github.cwramirezg.crm.home.di

import com.github.cwramirezg.crm.home.data.repository.HomeRepositoryImpl
import com.github.cwramirezg.crm.home.domain.home.usecase.GetRolUseCase
import com.github.cwramirezg.crm.home.domain.home.usecase.HomeUseCases
import com.github.cwramirezg.crm.home.domain.repository.HomeRepository
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HomeModule {

    @Singleton
    @Provides
    fun provideGetRolUseCase(repository: HomeRepository): GetRolUseCase {
        return GetRolUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideHomeUseCases(getRolUseCase: GetRolUseCase): HomeUseCases {
        return HomeUseCases(getRolUseCase)
    }

    @Singleton
    @Provides
    fun provideHomeRepository(firestore: FirebaseFirestore): HomeRepository {
        return HomeRepositoryImpl(firestore)
    }

}