package com.example.otchallenge.base.api.dagger

import com.example.otchallenge.base.internal.dagger.BaseModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
	modules = [
		BaseModule::class,
	]
)
interface AppComponent
