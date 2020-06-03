package com.joancolmenerodev.login.presentation

import com.joancolmenerodev.base_core.threading.TestCoroutineDispatcherProvider
import com.joancolmenerodev.library_base.Either
import com.joancolmenerodev.login.domain.exceptions.LoginExceptions
import com.joancolmenerodev.login.domain.models.UserLogged
import com.joancolmenerodev.login.domain.usecase.DoLoginUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.unmockkAll
import io.mockk.verify
import org.junit.After
import org.junit.Before
import org.junit.Test


class LoginPresenterTest {

    private lateinit var presenter: LoginContract.Presenter

    @RelaxedMockK
    private lateinit var mockView: LoginContract.View

    @MockK
    private lateinit var mockUseCase: DoLoginUseCase

    private val testCoroutineDispatcher = TestCoroutineDispatcherProvider()

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        presenter = LoginPresenter(mockUseCase, testCoroutineDispatcher)
    }

    @After
    fun tearDown() {
        presenter.onViewDestroyed()
        unmockkAll()
    }

    @Test
    fun `given user types correct credentials then a welcome message is shown to the user`() {
        //ASSIGN
        coEvery { mockUseCase.execute(any(), any()) } answers { Either.Right(userLogged) }

        //ACT
        presenter.onViewReady(mockView, USERNAME, PASSWORD)

        //ASSERT
        verify(exactly = 1) {
            mockView.showWelcomeMessage(userLogged.name)
            mockView.navigateToKittiesActivity()
        }

    }

    @Test
    fun `given user types wrong credentials then a message is shown to the user`() {
        //ASSIGN
        coEvery {
            mockUseCase.execute(
                any(),
                any()
            )
        } answers { Either.Left(LoginExceptions.LoginFailed) }

        //ACT
        presenter.onViewReady(mockView, USERNAME, PASSWORD)

        //ASSERT
        verify(exactly = 1) {
            mockView.showLoginFailureMessage()
        }
    }

    companion object {
        private const val USERNAME = "username"
        private const val PASSWORD = "password"
        private val userLogged =
            UserLogged(1, "Joan Colmenero", "joancolmenero@gmail.com", "1234Token")
    }
}