package com.joancolmenerodev.kitties.feature.kitties_list.data.models


import com.google.gson.annotations.SerializedName

data class ImageBreedResponseItem(
    @SerializedName("breeds")
    val breeds: List<Breed>,
    @SerializedName("url")
    val url: String
)