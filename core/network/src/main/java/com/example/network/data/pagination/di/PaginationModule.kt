package com.example.network.data.pagination.di

import com.example.network.data.pagination.repo.PagingRepository
import com.example.network.data.pagination.repo.PagingRepositoryImpl
import com.example.network.data.pagination.service.PaginationService
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class PaginationModule {

    @Binds
    @Singleton
    abstract fun bindRepository(pagingRepositoryImpl: PagingRepositoryImpl): PagingRepository

    companion object{
        @Provides
        @Singleton
        fun providePagingService(@Named("paging") retrofit: Retrofit) : PaginationService {
            return retrofit.create(PaginationService::class.java)
        }
    }
}