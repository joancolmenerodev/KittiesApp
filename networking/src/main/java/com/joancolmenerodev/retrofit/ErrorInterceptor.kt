package com.joancolmenerodev.retrofit

import com.joancolmenerodev.base_core.exceptions.ClientException
import com.joancolmenerodev.base_core.exceptions.NoInternetConnection
import com.joancolmenerodev.base_core.exceptions.ServerException
import com.joancolmenerodev.base_core.exceptions.ServiceException
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import java.net.HttpURLConnection
import javax.inject.Inject

class ErrorInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val response =
            try {
                chain.proceed(chain.request())
            } catch (error: IOException) {
                throw NoInternetConnection(error.cause)
            }
        if (!response.isSuccessful) {
            when (response.code) {
                HttpURLConnection.HTTP_UNAVAILABLE -> throw ServerException.ServiceUnavailable
                HttpURLConnection.HTTP_NOT_FOUND -> throw ClientException.NotFound
                HttpURLConnection.HTTP_CLIENT_TIMEOUT -> throw ClientException.RequestTimeout
                else -> throw ServiceException(
                    IllegalStateException("The status code ${response.code} was received but not handled!")
                )
            }
        }
        return response
    }

}