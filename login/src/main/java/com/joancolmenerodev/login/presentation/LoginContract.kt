package com.joancolmenerodev.login.presentation

import com.joancolmenerodev.base_presentation.BasePresenter
import com.joancolmenerodev.base_presentation.PresenterView

interface LoginContract {

    interface View : PresenterView {
        fun showWelcomeMessage(username: String)
        fun showLoginFailureMessage()
        fun navigateToKittiesActivity()

    }

    interface Presenter : BasePresenter<View> {
        fun onViewReady(view: View, username: String, password: String)
    }
}