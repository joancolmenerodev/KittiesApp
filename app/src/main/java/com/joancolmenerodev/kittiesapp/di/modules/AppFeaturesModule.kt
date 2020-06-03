package com.joancolmenerodev.kittiesapp.di.modules

import com.joancolmenerodev.kitties.di.KittiesModule
import com.joancolmenerodev.login.di.LoginModule
import dagger.Module

@Module(includes = [LoginModule::class, KittiesModule::class])
object AppFeaturesModule