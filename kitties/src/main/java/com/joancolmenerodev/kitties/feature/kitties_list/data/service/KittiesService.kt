package com.joancolmenerodev.kitties.feature.kitties_list.data.service

import com.joancolmenerodev.kitties.feature.kitties_list.data.models.BreedsResponse
import com.joancolmenerodev.kitties.feature.kitties_list.data.models.ImageBreedResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface KittiesService {

    @GET("breeds")
    suspend fun getBreeds(): BreedsResponse

    @GET("images/search")
    suspend fun getBreedImage(
        @Query("breed_id") breedId: String
    ): ImageBreedResponse
}