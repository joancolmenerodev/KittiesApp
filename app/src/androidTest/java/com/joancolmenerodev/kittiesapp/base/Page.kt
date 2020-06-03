package com.joancolmenerodev.kittiesapp.base

import android.content.Context

abstract class Page(private val context: Context) {
    abstract fun at()
}