package com.joancolmenerodev.kitties.feature.kitties_list.presentation

import com.joancolmenerodev.base_core.threading.TestCoroutineDispatcherProvider
import com.joancolmenerodev.kitties.feature.kitties_list.domain.exceptions.KittiesListExceptions
import com.joancolmenerodev.kitties.feature.kitties_list.domain.models.Kittie
import com.joancolmenerodev.kitties.feature.kitties_list.domain.usecase.GetCatBreedsFilterByCountryCodeUseCase
import com.joancolmenerodev.kitties.feature.kitties_list.domain.usecase.GetCatBreedsOrderedAlphabeticallyUseCase
import com.joancolmenerodev.library_base.Either
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.unmockkAll
import org.junit.After
import org.junit.Before
import org.junit.Test

class KittiesListPresenterTest {

    private lateinit var presenter: KittiesListContract.Presenter

    @RelaxedMockK
    private lateinit var mockView: KittiesListContract.View

    @MockK
    private lateinit var mockOrderAlphabeticalUseCase: GetCatBreedsOrderedAlphabeticallyUseCase

    @MockK
    private lateinit var mockFilterUseCase: GetCatBreedsFilterByCountryCodeUseCase

    private val testCoroutineDispatcher = TestCoroutineDispatcherProvider()

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        presenter = KittiesListPresenter(
            mockOrderAlphabeticalUseCase,
            mockFilterUseCase,
            testCoroutineDispatcher
        )
    }

    @After
    fun tearDown() {
        presenter.onViewDestroyed()
        unmockkAll()
    }

    @Test
    fun `given user starts the app then loads a list of cats`() {
        //ASSIGN
        coEvery { mockOrderAlphabeticalUseCase.execute() } answers {
            Either.Right(
                kittiesList
            )
        }

        //ACT
        presenter.onViewReady(mockView)

        //ASSERT
        coVerify(exactly = 1) {
            mockView.showProgress(true)
            mockView.fillList(kittiesList)
            mockView.showProgress(false)
        }
    }

    @Test
    fun `given user starts the app loads a list of cats then it return not available`() {
        //ASSIGN
        coEvery { mockOrderAlphabeticalUseCase.execute() } answers {
            Either.Left(
                KittiesListExceptions.KittiesNotAvailable
            )
        }

        //ACT
        presenter.onViewReady(mockView)

        //ASSERT
        coVerify(exactly = 1) {
            mockView.showProgress(true)
            mockView.showServiceUnavailable()
            mockView.showProgress(false)
        }
    }

    @Test
    fun `given user starts the app loads a list of cats then it return not found`() {
        //ASSIGN
        coEvery { mockOrderAlphabeticalUseCase.execute() } answers {
            Either.Left(
                KittiesListExceptions.KittyBreedsNotFound
            )
        }

        //ACT
        presenter.onViewReady(mockView)

        //ASSERT
        coVerify(exactly = 1) {
            mockView.showProgress(true)
            mockView.showKittiesNotFound()
            mockView.showProgress(false)
        }
    }

    @Test
    fun `given user starts the app loads a list of cats and tries to filter by country`() {
        //ASSIGN
        coEvery { mockFilterUseCase.execute(any()) } answers {
            Either.Right(
                kittiesList
            )
        }

        //ACT
        presenter.onViewReady(mockView)
        presenter.filterList(COUNTRY_CODE)

        //ASSERT
        coVerify(exactly = 1) {
            mockView.fillList(kittiesList)
        }
    }

    @Test
    fun `given user starts the app loads a list of cats and tries to filter by country but there's no cat with that filter then returns no cat by country code`() {
        //ASSIGN
        coEvery { mockFilterUseCase.execute(any()) } answers {
            Either.Left(
                KittiesListExceptions.NoCatFoundByCountryCode
            )
        }

        //ACT
        presenter.onViewReady(mockView)
        presenter.filterList(COUNTRY_CODE)

        //ASSERT
        coVerify {
            mockView.noKittiesWithThatCountryCode()
        }
    }

    companion object {
        private const val COUNTRY_CODE = "EU"
        private val kittie = Kittie(
            id = "1",
            name = "ABC",
            description = "description",
            image = "imagecat1.png",
            countryCode = "EU",
            temperament = "temperament",
            wikipediaLink = "wiki.com"
        )
        private val kittie2 = Kittie(
            id = "1",
            name = "DBA",
            description = "description",
            image = "imagecat1.png",
            countryCode = "EU",
            temperament = "temperament",
            wikipediaLink = "wiki.com"
        )
        private val kittiesList = arrayListOf(
            kittie, kittie2
        )
    }
}