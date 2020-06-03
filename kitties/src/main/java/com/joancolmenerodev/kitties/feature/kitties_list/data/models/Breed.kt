package com.joancolmenerodev.kitties.feature.kitties_list.data.models


import com.google.gson.annotations.SerializedName

data class Breed(
    @SerializedName("country_code")
    val countryCode: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("temperament")
    val temperament: String,
    @SerializedName("wikipedia_url")
    val wikipediaUrl: String?
)