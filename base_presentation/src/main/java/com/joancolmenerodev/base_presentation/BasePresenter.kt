package com.joancolmenerodev.base_presentation

interface BasePresenter<T> {
    fun onViewReady(view: T)
    fun onViewDestroyed()
}