package com.joancolmenerodev.kitties.feature.kitties_list.presentation

import com.joancolmenerodev.base_core.threading.CoroutineDispatcherProvider
import com.joancolmenerodev.base_presentation.AbstractPresenter
import com.joancolmenerodev.kitties.feature.kitties_list.domain.exceptions.KittiesListExceptions
import com.joancolmenerodev.kitties.feature.kitties_list.domain.models.Kittie
import com.joancolmenerodev.kitties.feature.kitties_list.domain.usecase.GetCatBreedsFilterByCountryCodeUseCase
import com.joancolmenerodev.kitties.feature.kitties_list.domain.usecase.GetCatBreedsOrderedAlphabeticallyUseCase
import kotlinx.coroutines.withContext
import javax.inject.Inject

class KittiesListPresenter @Inject constructor(
    val getCatBreedsOrderedAlphabeticallyUseCase: GetCatBreedsOrderedAlphabeticallyUseCase,
    val getCatBreedsFilterByCountryCodeUseCase: GetCatBreedsFilterByCountryCodeUseCase,
    val uiContextProviderCoroutine: CoroutineDispatcherProvider
) : AbstractPresenter<KittiesListContract.View>(uiContextProviderCoroutine),
    KittiesListContract.Presenter {

    override fun onViewReady(view: KittiesListContract.View) {
        super.onViewReady(view)
        view.showProgress(true)
        launch {
            withContext(uiContextProviderCoroutine.io()) {
                getCatBreedsOrderedAlphabeticallyUseCase.execute()
            }.fold(::handleFailure, ::handleList)
        }
    }

    private fun handleFailure(failure: KittiesListExceptions) {
        view?.showProgress(false)
        when (failure) {
            is KittiesListExceptions.KittyBreedsNotFound -> view?.showKittiesNotFound()
            else -> view?.showServiceUnavailable()
        }

    }

    private fun handleFilterList(kittiesList: List<Kittie>) {
        view?.fillList(kittiesList)
    }

    private fun handleFilterFailure(failure: KittiesListExceptions) {
        when (failure) {
            is KittiesListExceptions.NoCatFoundByCountryCode -> view?.noKittiesWithThatCountryCode()
            else -> view?.showServiceUnavailable()
        }

    }

    private fun handleList(kittiesList: List<Kittie>) {
        view?.showProgress(false)
        view?.fillList(kittiesList)
    }

    override fun onKittieClicked(kittie: Kittie) {
        view?.navigateToDetail(kittie)
    }

    override fun filterList(country: String) {
        getCatBreedsFilterByCountryCodeUseCase.execute(country)
            .fold(::handleFilterFailure, ::handleFilterList)
    }

    override fun showRealList() {
        view?.fillList(getCatBreedsOrderedAlphabeticallyUseCase.getAlphabeticalList())
    }
}