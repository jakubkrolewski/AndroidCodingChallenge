package com.example.otchallenge.base.internal.network

import com.example.otchallenge.base.api.network.NetworkServicesFactory
import retrofit2.Retrofit
import kotlin.reflect.KClass

internal class RetrofitNetworkServicesFactory(private val retrofit: Retrofit) : NetworkServicesFactory {

    override fun <T : Any> create(service: KClass<T>): T {
        return retrofit.create(service.java)
    }
}