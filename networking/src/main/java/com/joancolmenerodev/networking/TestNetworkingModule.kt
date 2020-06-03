package com.joancolmenerodev.networking

import com.joancolmenerodev.retrofit.ErrorInterceptor
import com.joancolmenerodev.retrofit.HeadersInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
object TestNetworkingModule {

    @Provides
    @Singleton
    fun provideTestRetrofit(httpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    @Provides
    @Singleton
    fun provideTestOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        errorInterceptor: ErrorInterceptor,
        headersInterceptor: HeadersInterceptor
    ): OkHttpClient = OkHttpClient()
        .newBuilder()
        .addInterceptor(httpLoggingInterceptor)
        .addInterceptor(errorInterceptor)
        .addInterceptor(headersInterceptor)
        .cache(null)
        .build()

    @Provides
    @Singleton
    fun provideTestHttpLogginInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = when {
            BuildConfig.DEBUG -> HttpLoggingInterceptor.Level.BODY
            else -> HttpLoggingInterceptor.Level.NONE
        }
        return loggingInterceptor
    }

}

private const val BASE_URL = "http://localhost:8681/"