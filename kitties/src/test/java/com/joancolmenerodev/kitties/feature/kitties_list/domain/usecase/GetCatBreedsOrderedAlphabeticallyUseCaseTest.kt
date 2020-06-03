package com.joancolmenerodev.kitties.feature.kitties_list.domain.usecase

import com.joancolmenerodev.kitties.feature.kitties_list.domain.exceptions.KittiesListExceptions
import com.joancolmenerodev.kitties.feature.kitties_list.domain.models.Kittie
import com.joancolmenerodev.kitties.feature.kitties_list.domain.repository.KittiesRepository
import com.joancolmenerodev.library_base.Either
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetCatBreedsOrderedAlphabeticallyUseCaseTest {

    private lateinit var useCase: GetCatBreedsOrderedAlphabeticallyUseCase
    private val repository: KittiesRepository = mockk()

    @Before
    fun setUp() {
        useCase = GetCatBreedsOrderedAlphabeticallyUseCase(repository)
    }

    @Test
    fun `given we ask to the repository for the list sorted it returns the list sorted`() {

        //ASSIGN
        coEvery { repository.getBreeds() } answers { unsortedList }

        //ACT
        val result = runBlocking { useCase.execute() }

        //ASSERT
        assertEquals(result, Either.Right(sortedList))

    }

    @Test
    fun `given the repository returns a Not Found then the result is an Either Kitties Not Found`() {
        //ASSIGN
        val kittiesNotFound = KittiesListExceptions.KittyBreedsNotFound
        coEvery { repository.getBreeds() } throws kittiesNotFound

        //ACT
        val result = runBlocking { useCase.execute() }

        //ASSERT
        assertEquals(result, Either.Left(kittiesNotFound))

    }

    @Test
    fun `given the repository returns an List not available then the result is an Either List not available`() {
        //ASSIGN
        val kittiesNotAvailable = KittiesListExceptions.KittiesNotAvailable
        coEvery { repository.getBreeds() } throws kittiesNotAvailable

        //ACT
        val result = runBlocking { useCase.execute() }

        //ASSERT
        assertEquals(result, Either.Left(kittiesNotAvailable))

    }

    companion object {
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
        private val unsortedList = arrayListOf(
            kittie2, kittie
        )
        private val sortedList = arrayListOf(
            kittie, kittie2
        )

    }
}