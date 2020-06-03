package com.joancolmenerodev.base_core.threading

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestCoroutineDispatcher

interface CoroutineDispatcherProvider {

    fun main(): CoroutineDispatcher = Dispatchers.Main
    fun default(): CoroutineDispatcher = Dispatchers.Default
    fun io(): CoroutineDispatcher = Dispatchers.IO
    fun unconfined(): CoroutineDispatcher = Dispatchers.Unconfined

}

class DefaultCoroutineDispatcherProvider : CoroutineDispatcherProvider

class TestCoroutineDispatcherProvider : CoroutineDispatcherProvider {
    override fun default(): CoroutineDispatcher {
        return TestCoroutineDispatcher()
    }

    override fun io(): CoroutineDispatcher {
        return TestCoroutineDispatcher()
    }

    override fun main(): CoroutineDispatcher {
        return TestCoroutineDispatcher()
    }
}
