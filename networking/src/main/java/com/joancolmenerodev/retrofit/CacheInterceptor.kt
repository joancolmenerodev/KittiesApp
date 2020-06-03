package com.joancolmenerodev.retrofit

import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response
import java.util.concurrent.TimeUnit
import javax.inject.Inject


class CacheInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val cacheControl = CacheControl.Builder()
            .maxStale(FIFTEEN_MINUTES, TimeUnit.MINUTES) // 15 minutes cache
            .build()

        request = request.newBuilder()
            .removeHeader(HEADER_PRAGMA)
            .removeHeader(HEADER_CACHE_CONTROL)
            .cacheControl(cacheControl)
            .build()
        return chain.proceed(request)
    }

    companion object {
        const val HEADER_CACHE_CONTROL = "Cache-Control"
        const val HEADER_PRAGMA = "Pragma"
        const val FIFTEEN_MINUTES = 15
    }

}