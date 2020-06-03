package com.joancolmenerodev.login.domain.usecase

import com.joancolmenerodev.library_base.Either
import com.joancolmenerodev.login.data.models.Data
import com.joancolmenerodev.login.data.models.LoginResponse
import com.joancolmenerodev.login.data.models.User
import com.joancolmenerodev.login.domain.LoginRepository
import com.joancolmenerodev.login.domain.exceptions.LoginExceptions
import com.joancolmenerodev.login.domain.models.UserLogged
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test


class DoLoginUseCaseTest {

    private lateinit var useCase: DoLoginUseCase
    private val repository: LoginRepository = mockk()

    @Before
    fun setUp() {
        useCase = DoLoginUseCase(repository)
    }

    @Test
    fun `given the repository returns a user Logged then returns Either right with that user`() {
        //ASSIGN
        coEvery { repository.doLogin(any(), any()) } answers { userLogged }

        //ACT
        val result = runBlocking { useCase.execute(USERNAME, PASSWORD) }

        //ASSERT
        assertEquals(result, Either.Right(userLogged))

    }

    @Test
    fun `given the repository returns an exception because the user is not correct then returns Either left with LoginException`() {
        //ASSIGN
        val exception = LoginExceptions.LoginFailed
        coEvery { repository.doLogin(any(), any()) } throws exception

        //ACT
        val result = runBlocking { useCase.execute(USERNAME, PASSWORD) }

        //ASSERT
        assertEquals(result, Either.Left(exception))

    }


    companion object {
        private const val USERNAME = "username"
        private const val PASSWORD = "password"
        private val loginResponse = LoginResponse(
            data = Data(
                token = "1234Token",
                user = User(
                    email = "joancolmenero@gmail.com",
                    id = 1,
                    name = "Joan Colmenero"
                )
            ),
            message = "Login successfully",
            success = true
        )
        private val userLogged =
            UserLogged(1, "Joan Colmenero", "joancolmenero@gmail.com", "1234Token")
    }
}