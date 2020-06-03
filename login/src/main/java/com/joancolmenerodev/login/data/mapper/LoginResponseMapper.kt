package com.joancolmenerodev.login.data.mapper

import com.joancolmenerodev.login.data.models.LoginResponse
import com.joancolmenerodev.login.domain.models.UserLogged

fun LoginResponse.map() = UserLogged(
    id = this.data.user.id,
    name = this.data.user.name,
    email = this.data.user.email,
    token = this.data.token
)