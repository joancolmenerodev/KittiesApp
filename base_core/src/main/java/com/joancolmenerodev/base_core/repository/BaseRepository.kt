package com.joancolmenerodev.base_core.repository

import com.joancolmenerodev.base_core.exceptions.ServerException
import com.joancolmenerodev.base_core.exceptions.ServiceException
import com.joancolmenerodev.base_core.exceptions.SomethingWentWrongException

open class BaseRepository {

    inline fun <T> execute(block: () -> T): T =
        try {
            block()
        } catch (error: ServiceException) {
            when (error) {
                is ServerException.ServiceUnavailable -> throw SomethingWentWrongException(error)
                else -> throw error
            }
        }
}