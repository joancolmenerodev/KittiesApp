package com.joancolmenerodev.kittiesapp.feature.kitties_detail

import android.content.Context
import androidx.test.espresso.matcher.ViewMatchers
import com.joancolmenerodev.kittiesapp.R
import com.joancolmenerodev.kittiesapp.base.Page
import com.joancolmenerodev.kittiesapp.base.UIElement

class KittyDetailPage(private val context: Context) : Page(context) {


    private val view = UIElement(ViewMatchers.withId(R.id.constraint_main_view_detail))
    private val name = UIElement(ViewMatchers.withId(R.id.tv_kitty_detail_name))
    private val description = UIElement(ViewMatchers.withId(R.id.tv_kitty_detail_description))
    private val temperament = UIElement(ViewMatchers.withId(R.id.tv_kitty_detail_temperament))
    private val wikipediaURL = UIElement(ViewMatchers.withId(R.id.tv_kitty_detail_website))

    override fun at() {
        view.isDisplayed()
    }

    fun withName(catName: String) {
        name.withExactText(catName)
    }

    fun withDescription(catDescription: String) {
        description.withExactText(catDescription)
    }

    fun withTemperament(catTemperament: String) {
        temperament.withExactText(catTemperament)
    }

    fun withWebsite(catWebsite: String) {
        wikipediaURL.withExactText(catWebsite)
    }

    fun openWebsite() {
        wikipediaURL.tap()
    }
}