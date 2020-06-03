package com.joancolmenerodev.kittiesapp.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.Matcher


class UIElement(matcher: Matcher<View>) {

    private val element: ViewInteraction = onView(matcher)

    fun clickItemAtPosition(position: Int) {
        element.perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                position,
                click()
            )
        )
    }

    fun isDisplayed() {
        element.check(matches(ViewMatchers.isDisplayed()))
    }

    fun withExactText(text: String) {
        element.check(matches(withText(text)))
    }

    fun type(text: String) {
        element.perform(click(), clearText(), typeText(text))
    }

    fun tap() {
        element.perform(click())
    }

}