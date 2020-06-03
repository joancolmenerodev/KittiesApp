package com.joancolmenerodev.base_core.exceptions

import java.io.IOException

open class ServiceException(override val cause: Exception? = null) : IOException(cause)
open class NoInternetConnection(override val cause: Throwable?) : IOException(cause)

sealed class ServerException : ServiceException() {
    object ServiceUnavailable : ServerException()
}

sealed class ClientException : ServiceException() {
    object NotFound : ClientException()
    object RequestTimeout : ClientException()
}