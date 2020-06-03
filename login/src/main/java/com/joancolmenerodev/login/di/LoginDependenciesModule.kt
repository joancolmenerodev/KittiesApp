package com.joancolmenerodev.login.di

import com.joancolmenerodev.login.data.LoginRepositoryImpl
import com.joancolmenerodev.login.domain.LoginRepository
import com.joancolmenerodev.login.presentation.LoginContract
import com.joancolmenerodev.login.presentation.LoginPresenter
import dagger.Binds
import dagger.Module

@Module
abstract class LoginDependenciesModule {

    @Binds
    abstract fun bindLoginPresenter(presenter: LoginPresenter): LoginContract.Presenter

    @Binds
    abstract fun bindLoginRepository(repository: LoginRepositoryImpl): LoginRepository
}