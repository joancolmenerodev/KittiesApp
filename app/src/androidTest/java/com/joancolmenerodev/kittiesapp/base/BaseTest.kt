package com.joancolmenerodev.kittiesapp.base

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.RootMatchers.withDecorView
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.hamcrest.Matchers.not
import org.junit.After
import org.junit.Before
import org.junit.Rule


abstract class BaseTest<T : Activity> {


    lateinit var mockWebServer: MockWebServer

    val context: Context = InstrumentationRegistry.getInstrumentation().targetContext

    fun at(page: Page) {
        page.at()
    }

    abstract fun getTestActivity(): IntentsTestRule<T>
    abstract fun startIntentActivity()

    @Rule
    @JvmField
    var activityTestRule = this.getTestActivity()

    fun launchActivity(intent: Intent?) {
        getTestActivity().launchActivity(intent)
    }

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        mockWebServer.start(MOCK_WEB_SERVER_PORT)
        startIntentActivity()
    }

    fun dispatch(dispatcher: Dispatcher) {
        mockWebServer.dispatcher = dispatcher
    }

    fun enqueue(mockResponse: MockResponse) = mockWebServer.enqueue(mockResponse)

    protected fun verifyToastMessageWithStringResource(
        id: Int
    ) {
        onView(withText(id)).inRoot(withDecorView(not(this.getTestActivity().activity.window.decorView)))
            .check(matches(isDisplayed()))
    }

    @After
    fun tearDown() {
        activityTestRule.finishActivity()
        mockWebServer.shutdown()
    }

    companion object {
        private const val MOCK_WEB_SERVER_PORT = 8681
    }

}