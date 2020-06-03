package com.joancolmenerodev.kitties.feature.kitties_list.domain.repository

import com.joancolmenerodev.kitties.feature.kitties_list.domain.models.Kittie

interface KittiesRepository {

    suspend fun getBreeds(): List<Kittie>

    fun sortByCountry(countryCode: String): List<Kittie>

    fun getListAlphabetically(): List<Kittie>
}