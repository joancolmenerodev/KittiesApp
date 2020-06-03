package com.joancolmenerodev.login.di

import com.joancolmenerodev.login.LoginActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class LoginModule {

    @ContributesAndroidInjector(modules = [LoginDependenciesModule::class])
    abstract fun bindLoginActivity(): LoginActivity
}