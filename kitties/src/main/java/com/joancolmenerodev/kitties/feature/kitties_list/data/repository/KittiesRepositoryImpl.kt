package com.joancolmenerodev.kitties.feature.kitties_list.data.repository

import com.joancolmenerodev.base_core.exceptions.ClientException
import com.joancolmenerodev.base_core.repository.BaseRepository
import com.joancolmenerodev.kitties.feature.kitties_list.data.service.KittiesService
import com.joancolmenerodev.kitties.feature.kitties_list.domain.exceptions.KittiesListExceptions
import com.joancolmenerodev.kitties.feature.kitties_list.domain.mappers.map
import com.joancolmenerodev.kitties.feature.kitties_list.domain.models.Kittie
import com.joancolmenerodev.kitties.feature.kitties_list.domain.repository.KittiesRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class KittiesRepositoryImpl @Inject constructor(private val service: KittiesService) :
    KittiesRepository, BaseRepository() {

    private val kittiesList: MutableList<Kittie> = mutableListOf()

    override suspend fun getBreeds(): List<Kittie> =
        execute {
            try {
                val result = service.getBreeds().map { breedResponseItem ->
                    service.getBreedImage(breedResponseItem.id)
                }.map { imageBreedResponse -> imageBreedResponse.map() }
                kittiesList.clear()
                kittiesList.addAll(result)
                result
            } catch (exception: Exception) {
                when (exception) {
                    is ClientException.NotFound -> throw KittiesListExceptions.KittyBreedsNotFound
                    else -> throw KittiesListExceptions.KittiesNotAvailable
                }
            }
        }

    override fun sortByCountry(countryCode: String) =
        kittiesList.filter { it.countryCode == countryCode }

    override fun getListAlphabetically(): List<Kittie> = kittiesList.toList()

}
