package com.joancolmenerodev.kittiesapp.feature.login.page

import android.content.Context
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.joancolmenerodev.kittiesapp.R
import com.joancolmenerodev.kittiesapp.base.Page
import com.joancolmenerodev.kittiesapp.base.UIElement

class LoginPage(private val context: Context) : Page(context) {

    private val etUsername = UIElement(withId(R.id.et_login_username))
    private val etPassword = UIElement(withId(R.id.et_login_password))
    private val btnLogin = UIElement(withId(R.id.btn_login))
    private val view = UIElement(withId(R.id.constraint_login_view))

    override fun at() {
        view.isDisplayed()
    }

    fun typeUserName(username: String) {
        etUsername.type(username)
    }

    fun typePassword(username: String) {
        etPassword.type(username)
    }

    fun login() {
        btnLogin.tap()
    }

}