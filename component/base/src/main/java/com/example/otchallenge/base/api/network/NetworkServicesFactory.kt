package com.example.otchallenge.base.api.network

import kotlin.reflect.KClass

interface NetworkServicesFactory {

    fun <T : Any> create(service: KClass<T>): T
}