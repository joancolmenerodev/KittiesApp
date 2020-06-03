package com.joancolmenerodev.login.domain.exceptions

sealed class LoginExceptions : Exception() {
    object LoginFailed : LoginExceptions()
}