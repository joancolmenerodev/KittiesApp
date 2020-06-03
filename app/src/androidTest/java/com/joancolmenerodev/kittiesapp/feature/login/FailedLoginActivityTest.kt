package com.joancolmenerodev.kittiesapp.feature.login

import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.joancolmenerodev.kittiesapp.base.BaseTest
import com.joancolmenerodev.kittiesapp.feature.login.page.LoginPage
import com.joancolmenerodev.login.LoginActivity
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class FailedLoginActivityTest :
    BaseTest<LoginActivity>() {

    override fun getTestActivity() =
        IntentsTestRule(LoginActivity::class.java, true, false)

    override fun startIntentActivity() {
        launchActivity(null)
    }

    private val loginPage = LoginPage(context)

    @Test
    fun loginFailed() {


        at(loginPage)

        loginPage.typeUserName(USERNAME)
        loginPage.typePassword(PASSWORD)
        loginPage.login()

        at(loginPage)
    }


    companion object {
        const val USERNAME = "joan"
        const val PASSWORD = "example"
    }

}