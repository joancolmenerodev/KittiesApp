package com.joancolmenerodev.retrofit

import com.joancolmenerodev.networking.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class HeadersInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response =
        with(chain.request().newBuilder()) {
            addHeader(HEADER_KEY, API_KEY)
            chain.proceed(this.build())
        }
}

private const val HEADER_KEY = "x-api-key"
private const val API_KEY = BuildConfig.KittiesApiKey