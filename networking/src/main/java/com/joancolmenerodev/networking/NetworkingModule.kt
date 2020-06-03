package com.joancolmenerodev.networking

import android.app.Application
import com.joancolmenerodev.retrofit.CacheInterceptor
import com.joancolmenerodev.retrofit.ErrorInterceptor
import com.joancolmenerodev.retrofit.HeadersInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import javax.inject.Singleton

@Module
object NetworkingModule {

    @Provides
    @Singleton
    fun provideRetrofit(httpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        errorInterceptor: ErrorInterceptor,
        headersInterceptor: HeadersInterceptor,
        cacheInterceptor: CacheInterceptor,
        application: Application
    ): OkHttpClient =
        OkHttpClient()
            .newBuilder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(errorInterceptor)
            .addInterceptor(headersInterceptor)
            .addInterceptor(cacheInterceptor)
            .cache(Cache(File(application.baseContext.cacheDir, "http-cache"), 10 * 1024 * 1024))
            .build()


    @Provides
    @Singleton
    fun provideHttpLogginInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = when {
            BuildConfig.DEBUG -> HttpLoggingInterceptor.Level.HEADERS
            else -> HttpLoggingInterceptor.Level.NONE
        }
        return loggingInterceptor
    }
}

private const val BASE_URL = "https://api.thecatapi.com/v1/"