package com.joancolmenerodev.kittiesapp.di.modules

import com.joancolmenerodev.base_core.threading.CoroutineDispatcherProvider
import com.joancolmenerodev.base_core.threading.DefaultCoroutineDispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.Reusable

@Module
object CoroutineDispatcherModule {

    @Provides
    @Reusable
    fun provideDefaultCoroutineContextProvider(): CoroutineDispatcherProvider =
        DefaultCoroutineDispatcherProvider()
}