package com.joancolmenerodev.kitties.feature.kitties_list.domain.usecase

import com.joancolmenerodev.base_core.usecase.BaseUseCase
import com.joancolmenerodev.kitties.feature.kitties_list.domain.exceptions.KittiesListExceptions
import com.joancolmenerodev.kitties.feature.kitties_list.domain.models.Kittie
import com.joancolmenerodev.kitties.feature.kitties_list.domain.repository.KittiesRepository
import com.joancolmenerodev.library_base.Either
import javax.inject.Inject

class GetCatBreedsOrderedAlphabeticallyUseCase @Inject constructor(val kittiesRepository: KittiesRepository) :
    BaseUseCase() {

    suspend fun execute(): Either<KittiesListExceptions, List<Kittie>> =
        toEither { kittiesRepository.getBreeds().sortedBy { it.name } }

    fun getAlphabeticalList() = kittiesRepository.getListAlphabetically()

}