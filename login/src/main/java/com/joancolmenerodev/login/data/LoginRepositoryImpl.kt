package com.joancolmenerodev.login.data

import com.joancolmenerodev.login.data.exceptions.LoginFailureException
import com.joancolmenerodev.login.data.mapper.map
import com.joancolmenerodev.login.data.models.Data
import com.joancolmenerodev.login.data.models.LoginResponse
import com.joancolmenerodev.login.data.models.User
import com.joancolmenerodev.login.domain.LoginRepository
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor() : LoginRepository {
    override suspend fun doLogin(username: String, password: String) =
        if (username == FAKE_USERNAME && password == FAKE_PASSWORD) {
            fakeResponse.map()
        } else {
            throw LoginFailureException()
        }

    companion object {
        private const val FAKE_USERNAME = "joancolmenero"
        private const val FAKE_PASSWORD = "example"
        private val fakeResponse = LoginResponse(
            data = Data(
                token = "1234Token",
                user = User(
                    email = "joancolmenero@gmail.com",
                    id = 1,
                    name = "Joan Colmenero"
                )
            ),
            message = "Login successfully",
            success = true
        )
    }
}