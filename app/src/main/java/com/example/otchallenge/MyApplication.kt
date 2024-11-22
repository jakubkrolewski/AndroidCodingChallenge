package com.example.otchallenge

import android.app.Application
import com.example.otchallenge.di.AppComponent
import com.example.otchallenge.bookslist.api.BooksListComponentProvider
import com.example.otchallenge.bookslist.internal.di.BooksListComponent
import com.example.otchallenge.di.AppModule
import com.example.otchallenge.di.DaggerAppComponent

class MyApplication : Application(), BooksListComponentProvider {

	private lateinit var appComponent: AppComponent

	override fun onCreate() {
		super.onCreate()
		appComponent = DaggerAppComponent.builder()
			.appModule(AppModule(this))
			.build()
	}

	override fun provideBooksListComponent(): BooksListComponent.Builder =
		appComponent.booksListComponent()
}
