package com.joancolmenerodev.kittiesapp.base

import android.content.Context

object JsonFileToString {

    fun loadJSONFromAssets(context: Context, fileName: String): String {
        return context.applicationContext.assets.open(fileName).bufferedReader().use {
            it.readText()
        }

    }

}