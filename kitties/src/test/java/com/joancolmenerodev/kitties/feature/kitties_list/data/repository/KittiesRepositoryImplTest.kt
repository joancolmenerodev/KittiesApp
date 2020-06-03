package com.joancolmenerodev.kitties.feature.kitties_list.data.repository

import com.joancolmenerodev.base_core.exceptions.ClientException
import com.joancolmenerodev.kitties.feature.kitties_list.data.models.*
import com.joancolmenerodev.kitties.feature.kitties_list.data.service.KittiesService
import com.joancolmenerodev.kitties.feature.kitties_list.domain.exceptions.KittiesListExceptions
import com.joancolmenerodev.kitties.feature.kitties_list.domain.models.Kittie
import com.joancolmenerodev.kitties.feature.kitties_list.domain.repository.KittiesRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test


class KittiesRepositoryImplTest {

    private lateinit var repository: KittiesRepository
    private val mockService: KittiesService = mockk()

    @Before
    fun setUp() {
        repository =
            KittiesRepositoryImpl(mockService)
    }

    @Test
    fun `given the list of cats are asked to the service, then it returns the correct list`() {

        //ASSIGN
        coEvery { mockService.getBreeds() } answers { breedsResponse }
        coEvery { mockService.getBreedImage(any()) } returns imageBreedResponse andThen imageBreedResponse2

        //ACT
        val result = runBlocking { repository.getBreeds() }

        //ASSERT
        assertEquals(result, kittiesList)

    }

    @Test(expected = KittiesListExceptions.KittyBreedsNotFound::class)
    fun `given user asks for the list and there's no kitties found then returns KittyBreedsNotFound`() {

        //ASSIGN
        val returnedException = ClientException.NotFound
        coEvery { mockService.getBreeds() } throws returnedException

        //ACT
        runBlocking { repository.getBreeds() }

    }

    @Test(expected = KittiesListExceptions.KittiesNotAvailable::class)
    fun `given user asks for the list and there's no internet connection then returns KittiesNotAvailable`() {

        //ASSIGN
        val returnedException = ClientException.RequestTimeout
        coEvery { mockService.getBreeds() } throws returnedException

        //ACT
        runBlocking { repository.getBreeds() }

    }

    companion object {
        private val breedsResponseItem1 = BreedsResponseItem(
            countryCode = "EU",
            description = "description",
            id = "1",
            temperament = "temperament",
            wikipediaUrl = "wiki.com"
        )
        private val breedsResponseItem2 = BreedsResponseItem(
            countryCode = "FR",
            description = "description2",
            id = "2",
            temperament = "temperament2",
            wikipediaUrl = "wiki2.com"
        )

        private val imageBreedResponseItem1 = ImageBreedResponseItem(
            arrayListOf(
                Breed(
                    countryCode = "EU",
                    description = "description",
                    id = "1",
                    name = "kittie",
                    temperament = "temperament",
                    wikipediaUrl = "wiki.com"
                )
            ),
            url = "imagecat1.png"
        )
        private val imageBreedResponseItem2 = ImageBreedResponseItem(
            arrayListOf(
                Breed(
                    countryCode = "FR",
                    description = "description2",
                    id = "2",
                    name = "kittie2",
                    temperament = "temperament2",
                    wikipediaUrl = "wiki2.com"
                )
            ),
            url = "imagecat2.png"
        )
        private val imageBreedResponse = ImageBreedResponse().apply {
            addAll(
                arrayListOf(imageBreedResponseItem1)
            )
        }
        private val imageBreedResponse2 = ImageBreedResponse().apply {
            addAll(
                arrayListOf(imageBreedResponseItem2)
            )
        }
        private val breedsResponse = BreedsResponse().apply {
            addAll(
                arrayListOf(
                    breedsResponseItem1,
                    breedsResponseItem2
                )
            )
        }
        private val kittie1 = Kittie(
            id = "1",
            name = "kittie",
            description = "description",
            image = "imagecat1.png",
            countryCode = "EU",
            temperament = "temperament",
            wikipediaLink = "wiki.com"
        )
        private val kittie2 = Kittie(
            id = "2",
            name = "kittie2",
            description = "description2",
            image = "imagecat2.png",
            countryCode = "FR",
            temperament = "temperament2",
            wikipediaLink = "wiki2.com"
        )

        private val kittiesList = arrayListOf(kittie1, kittie2)


    }
}