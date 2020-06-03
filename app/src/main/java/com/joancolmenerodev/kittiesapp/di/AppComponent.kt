package com.joancolmenerodev.kittiesapp.di

import android.app.Application
import com.joancolmenerodev.kittiesapp.App
import com.joancolmenerodev.kittiesapp.di.modules.AppFeaturesModule
import com.joancolmenerodev.kittiesapp.di.modules.CoroutineDispatcherModule
import com.joancolmenerodev.networking.NetworkingModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        CoroutineDispatcherModule::class,
        AppFeaturesModule::class,
        NetworkingModule::class
    ]
)
interface AppComponent : AndroidInjector<App> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): AppComponent
    }
}