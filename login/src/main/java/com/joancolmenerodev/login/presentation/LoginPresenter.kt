package com.joancolmenerodev.login.presentation

import com.joancolmenerodev.base_core.threading.CoroutineDispatcherProvider
import com.joancolmenerodev.base_presentation.AbstractPresenter
import com.joancolmenerodev.login.domain.exceptions.LoginExceptions
import com.joancolmenerodev.login.domain.models.UserLogged
import com.joancolmenerodev.login.domain.usecase.DoLoginUseCase
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LoginPresenter @Inject constructor(
    val doLoginUseCase: DoLoginUseCase,
    val uiContextProviderCoroutine: CoroutineDispatcherProvider
) : AbstractPresenter<LoginContract.View>(uiContextProviderCoroutine), LoginContract.Presenter {
    override fun onViewReady(view: LoginContract.View, username: String, password: String) {
        onViewReady(view)
        launch {
            withContext(uiContextProviderCoroutine.io()) {
                doLoginUseCase.execute(username, password)
            }.fold(::handleFailure, ::handleLogin)
        }
    }

    private fun handleFailure(failure: LoginExceptions) {
        when (failure) {
            is LoginExceptions.LoginFailed -> {
                view?.showLoginFailureMessage()
            }
        }
    }

    private fun handleLogin(user: UserLogged) {
        view?.showWelcomeMessage(username = user.name)
        view?.navigateToKittiesActivity()
    }
}