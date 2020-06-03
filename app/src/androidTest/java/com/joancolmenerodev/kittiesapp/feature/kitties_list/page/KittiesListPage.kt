package com.joancolmenerodev.kittiesapp.feature.kitties_list.page

import android.content.Context
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.joancolmenerodev.kittiesapp.R
import com.joancolmenerodev.kittiesapp.base.Page
import com.joancolmenerodev.kittiesapp.base.UIElement

class KittiesListPage(private val context: Context) : Page(context) {

    private val recyclerView = UIElement(withId(R.id.rv_breed_kitties))
    private val view = UIElement(withId(R.id.kitties_list_main_view))
    private val filterButton = UIElement(withId(R.id.fab_filter_by_country))

    override fun at() {
        view.isDisplayed()
    }

    fun filterByCountryCode() {
        filterButton.tap()
    }

    fun isRecyclerViewFilled() {
        recyclerView.clickItemAtPosition(FIRST_POSITION_RECYCLER_VIEW)
    }

    companion object {
        const val FIRST_POSITION_RECYCLER_VIEW = 0
    }
}