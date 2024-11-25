package com.example.otchallenge.base.internal.network

import android.content.Context
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

private const val NETWORK_CACHE_SIZE_BYTES: Long = 10 * 1024 * 1024
private const val NETWORK_CACHE_FILE_NAME = "http-cache"
private const val CALL_TIMEOUT_SECONDS = 30L

@Module
internal object OkHttpModule {

    @Provides
    @Singleton
    fun provideOkHttpClientLazy(
        context: Context,
    ): Lazy<OkHttpClient> {
        return lazy {
            OkHttpClient.Builder()
                .cache(
                    Cache(
                        File(context.cacheDir, NETWORK_CACHE_FILE_NAME),
                        NETWORK_CACHE_SIZE_BYTES
                    )
                )
                .callTimeout(CALL_TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .build()
        }
    }
}