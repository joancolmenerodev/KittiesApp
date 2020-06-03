package com.joancolmenerodev.kittiesapp.feature.login

import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.joancolmenerodev.kittiesapp.base.BaseTest
import com.joancolmenerodev.kittiesapp.feature.kitties_list.page.KittiesListPage
import com.joancolmenerodev.kittiesapp.feature.login.page.LoginPage
import com.joancolmenerodev.login.LoginActivity
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class SuccessfullyLoginActivityTest :
    BaseTest<LoginActivity>() {

    override fun getTestActivity() =
        IntentsTestRule(LoginActivity::class.java, true, false)

    override fun startIntentActivity() {
        launchActivity(null)
    }

    private val loginPage = LoginPage(context)
    private val kittiesListPage = KittiesListPage(context)

    @Test
    fun loginSuccessfully() {


        at(loginPage)

        loginPage.typeUserName(USERNAME)
        loginPage.typePassword(PASSWORD)
        loginPage.login()

        at(kittiesListPage)
    }


    companion object {
        const val USERNAME = "joancolmenero"
        const val PASSWORD = "example"
    }

}