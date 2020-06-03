package com.joancolmenerodev.login.domain

import com.joancolmenerodev.login.domain.models.UserLogged

interface LoginRepository {

    suspend fun doLogin(username: String, password: String): UserLogged
}