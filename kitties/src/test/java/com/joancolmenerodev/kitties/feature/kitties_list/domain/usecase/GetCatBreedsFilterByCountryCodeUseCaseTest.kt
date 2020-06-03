package com.joancolmenerodev.kitties.feature.kitties_list.domain.usecase

import com.joancolmenerodev.kitties.feature.kitties_list.domain.exceptions.KittiesListExceptions
import com.joancolmenerodev.kitties.feature.kitties_list.domain.models.Kittie
import com.joancolmenerodev.kitties.feature.kitties_list.domain.repository.KittiesRepository
import com.joancolmenerodev.library_base.Either
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test


class GetCatBreedsFilterByCountryCodeUseCaseTest {

    private lateinit var useCase: GetCatBreedsFilterByCountryCodeUseCase
    private val repository: KittiesRepository = mockk()

    @Before
    fun setUp() {
        useCase = GetCatBreedsFilterByCountryCodeUseCase(repository)
    }

    @Test
    fun `given we ask for the repository for a list filtered by country code it returns the Either right with the list filtered`() {
        //ASSIGN
        every { repository.sortByCountry(any()) } answers { listFiltered }

        //ACT
        val result = useCase.execute(countryCode = COUNTRY_CODE)

        //ASSERT
        assertEquals(result, Either.Right(listFiltered))
    }

    @Test
    fun `given we ask for the repository for a list filtered by country code it returns empty list it returns Either Left KittiesListException`() {
        //ASSIGN
        every { repository.sortByCountry(any()) } answers { emptyList() }

        //ACT
        val result = useCase.execute(countryCode = COUNTRY_CODE)

        //ASSERT
        assertEquals(result, Either.Left(KittiesListExceptions.NoCatFoundByCountryCode))
    }

    companion object {
        private const val COUNTRY_CODE = "EU"
        val listFiltered = arrayListOf(
            Kittie(
                id = "1",
                name = "kittie",
                description = "description",
                image = "imagecat1.png",
                countryCode = "EU",
                temperament = "temperament",
                wikipediaLink = "wiki.com"
            ),
            Kittie(
                id = "2",
                name = "kitti2",
                description = "descriptio2",
                image = "imagecat2.png",
                countryCode = "EU",
                temperament = "temperament2",
                wikipediaLink = "wiki2.com"
            )
        )
    }
}