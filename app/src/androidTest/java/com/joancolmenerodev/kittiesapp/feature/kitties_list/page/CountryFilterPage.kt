package com.joancolmenerodev.kittiesapp.feature.kitties_list.page

import android.content.Context
import androidx.test.espresso.matcher.ViewMatchers
import com.joancolmenerodev.kittiesapp.R
import com.joancolmenerodev.kittiesapp.base.Page
import com.joancolmenerodev.kittiesapp.base.UIElement

class CountryFilterPage(private val context: Context) : Page(context) {

    private val search = UIElement(ViewMatchers.withId(R.id.country_code_picker_search))
    private val listViewCountry = UIElement(ViewMatchers.withId(R.id.country_code_picker_listview))

    override fun at() {
        listViewCountry.isDisplayed()
    }

    fun searchCountry(country: String) {
        search.type(country)
    }

    fun selectCountry() {
        listViewCountry.tap()
    }
}