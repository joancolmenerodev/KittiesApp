package com.joancolmenerodev.kitties.feature.kitties_list.domain.mappers

import com.joancolmenerodev.kitties.feature.kitties_list.domain.models.Kittie
import com.joancolmenerodev.kitties.feature.kitties_list.data.models.ImageBreedResponse
import com.joancolmenerodev.kitties.feature.kitties_list.data.models.ImageBreedResponseItem

fun ImageBreedResponse.map() = this.first().map()

fun ImageBreedResponseItem.map(): Kittie {
    val breed = this.breeds.first()
    return Kittie(
        breed.id,
        breed.name,
        this.url,
        breed.description,
        breed.countryCode,
        breed.temperament,
        breed.wikipediaUrl
    )
}