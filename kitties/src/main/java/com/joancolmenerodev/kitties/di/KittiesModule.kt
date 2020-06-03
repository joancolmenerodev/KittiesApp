package com.joancolmenerodev.kitties.di

import com.joancolmenerodev.kitties.feature.kitties_list.presentation.KittiesListActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [KittiesRetrofitServiceModule::class])
abstract class KittiesModule {

    @ContributesAndroidInjector(modules = [KittiesDependenciesModule::class])
    abstract fun bindKittiesListActivity(): KittiesListActivity
}