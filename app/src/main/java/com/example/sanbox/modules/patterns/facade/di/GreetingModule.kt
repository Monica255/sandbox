package com.example.sanbox.modules.patterns.facade.di

import com.example.sanbox.modules.patterns.facade.Greeting
import com.example.sanbox.modules.patterns.facade.SayHai
import com.example.sanbox.modules.patterns.facade.SayName
import com.example.sanbox.modules.patterns.facade.SayThankyou
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class GreetingModule {
    @Provides
    @Singleton
    fun provideGreeting(
        sayHai: SayHai,
        sayName: SayName,
        sayThankyou: SayThankyou,
    ): Greeting = Greeting(sayHai, sayName, sayThankyou)
}