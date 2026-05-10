package com.example.sanbox.core.di

import com.example.network.interceptor.NetworkHandler
import com.example.sanbox.core.NetworkHandlerImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class NetworkHandlerModule {
    @Binds
    @Singleton
    abstract fun bindNetworkHandler(impl: NetworkHandlerImpl): NetworkHandler
}