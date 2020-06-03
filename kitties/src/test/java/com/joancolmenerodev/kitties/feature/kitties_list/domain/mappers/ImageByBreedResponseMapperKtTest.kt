package com.joancolmenerodev.kitties.feature.kitties_list.domain.mappers

import com.joancolmenerodev.kitties.feature.kitties_list.data.models.Breed
import com.joancolmenerodev.kitties.feature.kitties_list.data.models.ImageBreedResponse
import com.joancolmenerodev.kitties.feature.kitties_list.data.models.ImageBreedResponseItem
import com.joancolmenerodev.kitties.feature.kitties_list.domain.models.Kittie
import org.junit.Assert.assertEquals
import org.junit.Test

class ImageByBreedResponseMapperKtTest {

    @Test
    fun `map imageByBreedResponse to a Kittie`() {
        //ASSIGN

        //ACT
        val result = imageBreedResponse.map()

        //ASSERT
        assertEquals(result, kittie)

    }

    companion object {
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
        private val imageBreedResponse = ImageBreedResponse().apply {
            addAll(
                arrayListOf(imageBreedResponseItem1)
            )
        }
        private val kittie = Kittie(
            id = "1",
            name = "kittie",
            description = "description",
            image = "imagecat1.png",
            countryCode = "EU",
            temperament = "temperament",
            wikipediaLink = "wiki.com"
        )
    }


}