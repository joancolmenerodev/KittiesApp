package com.joancolmenerodev.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.joancolmenerodev.kitties.feature.kitties_list.presentation.KittiesListActivity
import com.joancolmenerodev.login.presentation.LoginContract
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject

class LoginActivity : AppCompatActivity(), LoginContract.View {

    @Inject
    lateinit var presenter: LoginContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        inject()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initClickListener()

    }

    private fun inject() {
        AndroidInjection.inject(this)
    }

    private fun initClickListener() {
        btn_login.setOnClickListener {
            val username = et_login_username.text.toString()
            val password = et_login_password.text.toString()
            presenter.onViewReady(this, username, password)
        }
    }

    override fun showWelcomeMessage(username: String) {
        Toast.makeText(
            this,
            getString(R.string.welcome_login_text, username),
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun showLoginFailureMessage() {
        Toast.makeText(
            this,
            getString(R.string.login_fail_text),
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun navigateToKittiesActivity() {
        startActivity(Intent(this, KittiesListActivity::class.java))
    }
}
