package com.example.network.data.animal.di

import com.example.network.data.animal.repository.AnimalRepository
import com.example.network.data.animal.repository.AnimalRepositoryImpl
import com.example.network.data.animal.service.AnimalFactService
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AnimalModule {

    @Binds
    @Singleton
    abstract fun bindRepository(impl: AnimalRepositoryImpl): AnimalRepository

    companion object {
        @Provides
        @Singleton
        fun providesApiService(retrofit: Retrofit): AnimalFactService = retrofit.create(
            AnimalFactService::class.java
        )
    }
}