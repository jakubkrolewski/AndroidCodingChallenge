package com.example.otchallenge

import android.app.Application
import com.example.otchallenge.base.api.di.AppComponent
import com.example.otchallenge.base.api.di.DaggerAppComponent
import com.example.otchallenge.base.api.di.HasAppComponent

class MyApplication : Application(), HasAppComponent {

	override lateinit var appComponent: AppComponent

	override fun onCreate() {
		super.onCreate()
		appComponent = DaggerAppComponent.builder().build()
	}
}
