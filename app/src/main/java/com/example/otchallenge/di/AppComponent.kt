package com.example.otchallenge.di

import com.example.otchallenge.base.internal.di.BaseModule
import com.example.otchallenge.bookslist.internal.di.BooksListComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        BaseModule::class,
        SubcomponentsModule::class,
    ]
)
interface AppComponent {
    fun booksListComponent(): BooksListComponent.Builder
}
