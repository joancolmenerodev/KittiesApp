package com.joancolmenerodev.login.data

import com.joancolmenerodev.login.data.exceptions.LoginFailureException
import com.joancolmenerodev.login.domain.LoginRepository
import com.joancolmenerodev.login.domain.models.UserLogged
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class LoginRepositoryImplTest {

    private lateinit var loginRepository: LoginRepository

    @Before
    fun setUp() {
        loginRepository = LoginRepositoryImpl()
    }

    @Test
    fun `Given we send the correct username and password it will return the userlogged`() {
        //ASSIGN

        //ACT
        val result =
            runBlocking { loginRepository.doLogin(CORRECT_USERNAME, CORRECT_PASSWORD) }

        //ASSERT
        assertEquals(result, userLogged)
    }

    @Test(expected = LoginFailureException::class)
    fun `Given we send a correct username but wrong password it will throw an exception`() {
        //ASSIGN

        //ACT
        runBlocking { loginRepository.doLogin(CORRECT_USERNAME, WRONG_PASSWORD) }
    }

    @Test(expected = LoginFailureException::class)
    fun `Given we send a wrong username but correct password it will throw an exception`() {
        //ASSIGN

        //ACT
        runBlocking { loginRepository.doLogin(WRONG_USERNAME, CORRECT_PASSWORD) }
    }

    companion object {
        private val userLogged =
            UserLogged(1, "Joan Colmenero", "joancolmenero@gmail.com", "1234Token")
        private const val CORRECT_USERNAME = "joancolmenero"
        private const val CORRECT_PASSWORD = "example"
        private const val WRONG_USERNAME = "email"
        private const val WRONG_PASSWORD = "password"
    }
}