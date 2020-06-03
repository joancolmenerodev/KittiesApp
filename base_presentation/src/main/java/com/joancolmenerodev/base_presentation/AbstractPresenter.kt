package com.joancolmenerodev.base_presentation

import com.joancolmenerodev.base_core.threading.CoroutineDispatcherProvider
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

abstract class AbstractPresenter<T : PresenterView>(private val uiContext: CoroutineDispatcherProvider) :
    BasePresenter<T> {

    private val job = Job()

    var view: T? = null

    override fun onViewDestroyed() {
        this.view = null
        this.job.cancel()
    }

    override fun onViewReady(view: T) {
        this.view = view
    }

    protected fun launch(
        context: CoroutineContext = uiContext.main(),
        block: suspend CoroutineScope.(T) -> Unit
    ): Job = view?.let {
        CoroutineScope(context + job + CoroutineExceptionHandler { _, error -> throw error })
            .launch { block(it) }

    } ?: throw NotImplementedError("View not attached correctly")

}