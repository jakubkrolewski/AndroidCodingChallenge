package com.example.otchallenge.base.internal.network

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.multibindings.ElementsIntoSet
import okhttp3.Cache
import okhttp3.Interceptor
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
    @ElementsIntoSet
    fun provideDefaultInterceptors(): Set<Interceptor> = emptySet()

    @Provides
    @Singleton
    fun provideOkHttpClient(
        context: Context,
        interceptors: Set<@JvmSuppressWildcards Interceptor>
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .apply { interceptors().addAll(interceptors) }
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