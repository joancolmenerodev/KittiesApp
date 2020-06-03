package com.joancolmenerodev.kitties.feature.kitties_list.domain.exceptions

sealed class KittiesListExceptions : Exception() {
    object KittyBreedsNotFound : KittiesListExceptions()
    object NoCatFoundByCountryCode : KittiesListExceptions()
    object KittiesNotAvailable : KittiesListExceptions()
}