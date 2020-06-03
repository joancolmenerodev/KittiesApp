package com.joancolmenerodev.login.domain.usecase

import com.joancolmenerodev.base_core.usecase.BaseUseCase
import com.joancolmenerodev.library_base.Either
import com.joancolmenerodev.login.domain.LoginRepository
import com.joancolmenerodev.login.domain.exceptions.LoginExceptions
import com.joancolmenerodev.login.domain.models.UserLogged
import javax.inject.Inject

class DoLoginUseCase @Inject constructor(val loginRepository: LoginRepository) :
    BaseUseCase() {

    suspend fun execute(username: String, password: String): Either<LoginExceptions, UserLogged> =
        toEither {
            try {
                loginRepository.doLogin(username, password)
            } catch (e: Exception) {
                throw LoginExceptions.LoginFailed
            }
        }
}