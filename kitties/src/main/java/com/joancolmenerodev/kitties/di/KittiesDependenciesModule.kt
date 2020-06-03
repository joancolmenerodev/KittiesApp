package com.joancolmenerodev.kitties.di

import com.joancolmenerodev.kitties.feature.kitties_list.data.repository.KittiesRepositoryImpl
import com.joancolmenerodev.kitties.feature.kitties_list.data.service.KittiesService
import com.joancolmenerodev.kitties.feature.kitties_list.domain.repository.KittiesRepository
import com.joancolmenerodev.kitties.feature.kitties_list.presentation.KittiesListContract
import com.joancolmenerodev.kitties.feature.kitties_list.presentation.KittiesListPresenter
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.Reusable
import retrofit2.Retrofit

@Module
abstract class KittiesDependenciesModule {

    @Binds
    abstract fun bindLoginPresenter(presenter: KittiesListPresenter): KittiesListContract.Presenter

    @Binds
    abstract fun bindLoginRepository(repository: KittiesRepositoryImpl): KittiesRepository

}

@Module
object KittiesRetrofitServiceModule {

    @Provides
    @Reusable
    fun provideKittiesService(retrofit: Retrofit): KittiesService {
        return retrofit.create(KittiesService::class.java)
    }

}