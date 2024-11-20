package com.example.otchallenge.bookslist.internal.di

import com.example.otchallenge.base.api.di.AppComponent
import com.example.otchallenge.base.api.di.FragmentScope
import com.example.otchallenge.bookslist.api.BooksListFragment
import dagger.Component
import dagger.Subcomponent

@FragmentScope
@Component(
    dependencies = [
        AppComponent::class,
    ],
    modules = [
        BooksListModule::class,
    ]
)
internal interface BooksListComponent {
    fun inject(fragment: BooksListFragment)
}
