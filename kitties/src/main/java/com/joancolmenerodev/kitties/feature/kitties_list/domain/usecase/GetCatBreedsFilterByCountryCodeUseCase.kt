package com.joancolmenerodev.kitties.feature.kitties_list.domain.usecase

import com.joancolmenerodev.base_core.usecase.BaseUseCase
import com.joancolmenerodev.kitties.feature.kitties_list.domain.exceptions.KittiesListExceptions
import com.joancolmenerodev.kitties.feature.kitties_list.domain.models.Kittie
import com.joancolmenerodev.kitties.feature.kitties_list.domain.repository.KittiesRepository
import com.joancolmenerodev.library_base.Either
import javax.inject.Inject

class GetCatBreedsFilterByCountryCodeUseCase @Inject constructor(private val repository: KittiesRepository) :
    BaseUseCase() {

    fun execute(countryCode: String): Either<KittiesListExceptions, List<Kittie>> {
        return toEither {
            val list = repository.sortByCountry(countryCode)
            if (list.isEmpty()) throw KittiesListExceptions.NoCatFoundByCountryCode
            else list
        }
    }

}