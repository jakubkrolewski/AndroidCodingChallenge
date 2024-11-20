package com.example.otchallenge.base.api

import com.example.otchallenge.base.internal.jsonparser.JsonParserModule
import com.example.otchallenge.base.internal.network.NetworkServicesModule
import dagger.Module

@Module(includes = [
    JsonParserModule::class,
    NetworkServicesModule::class,
])
interface BaseModule