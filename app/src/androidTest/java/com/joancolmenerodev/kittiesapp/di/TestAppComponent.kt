package com.joancolmenerodev.kittiesapp.di


import com.joancolmenerodev.kittiesapp.App
import com.joancolmenerodev.kittiesapp.di.modules.AppFeaturesModule
import com.joancolmenerodev.kittiesapp.di.modules.CoroutineDispatcherModule
import com.joancolmenerodev.networking.TestNetworkingModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        TestNetworkingModule::class,
        AppFeaturesModule::class,
        CoroutineDispatcherModule::class]
)
interface TestAppComponent : AppComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: App): TestAppComponent
    }
}