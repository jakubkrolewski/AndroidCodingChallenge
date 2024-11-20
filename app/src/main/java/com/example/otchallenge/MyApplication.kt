package com.example.otchallenge

import android.app.Application
import com.example.otchallenge.base.api.dagger.AppComponent
import com.example.otchallenge.base.api.dagger.DaggerAppComponent
import com.example.otchallenge.base.api.dagger.HasAppComponent

class MyApplication : Application(), HasAppComponent {

	override lateinit var appComponent: AppComponent

	override fun onCreate() {
		super.onCreate()
		appComponent = DaggerAppComponent.builder().build()
	}
}
