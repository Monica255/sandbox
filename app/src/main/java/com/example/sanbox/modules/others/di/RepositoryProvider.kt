package com.example.sanbox.modules.others.di

import com.example.sanbox.modules.others.data.OthersRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryProvider {
    @Provides
    @Singleton
    fun provideRepository() = OthersRepository()
}