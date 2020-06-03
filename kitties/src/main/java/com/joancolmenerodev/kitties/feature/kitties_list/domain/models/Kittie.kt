package com.joancolmenerodev.kitties.feature.kitties_list.domain.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Kittie(
    val id: String,
    val name: String,
    val image: String,
    val description: String,
    val countryCode: String,
    val temperament: String,
    val wikipediaLink: String? = ""
) : Parcelable
