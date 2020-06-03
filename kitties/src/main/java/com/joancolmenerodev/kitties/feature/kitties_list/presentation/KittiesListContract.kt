package com.joancolmenerodev.kitties.feature.kitties_list.presentation

import com.joancolmenerodev.base_presentation.BasePresenter
import com.joancolmenerodev.base_presentation.PresenterView
import com.joancolmenerodev.kitties.feature.kitties_list.domain.models.Kittie

interface KittiesListContract {
    interface View : PresenterView {
        fun fillList(kittiesList: List<Kittie>)
        fun showProgress(visible: Boolean)
        fun showKittiesNotFound()
        fun noKittiesWithThatCountryCode()
        fun showServiceUnavailable()
        fun navigateToDetail(kittie: Kittie)

    }

    interface Presenter : BasePresenter<View> {
        fun filterList(country: String)
        fun showRealList()
        fun onKittieClicked(kittie: Kittie)
    }
}