package com.example.otchallenge.base.internal.network

import com.example.otchallenge.base.api.network.NetworkServicesFactory
import com.example.otchallenge.base.internal.jsonparser.JsonParserModule
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

private const val BASE_URL = "https://api.nytimes.com/svc/books/v3/"

@Module(
    includes = [
        JsonParserModule::class,
        OkHttpModule::class,
    ]
)
internal object NetworkServicesModule {

    @Singleton
    @Provides
    fun provideNetworkServicesFactory(
        moshi: Moshi,
        okHttpClientProvider: Lazy<@JvmSuppressWildcards OkHttpClient>
    ): NetworkServicesFactory {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .callFactory { okHttpClientProvider.value.newCall(it) }
            .build()

        return RetrofitNetworkServicesFactory(retrofit)
    }
}