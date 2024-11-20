package com.example.otchallenge.bookslist.internal.di

import com.example.otchallenge.base.api.di.FragmentScope
import com.example.otchallenge.base.api.network.NetworkServicesFactory
import com.example.otchallenge.bookslist.internal.repository.BooksListRepository
import dagger.Module
import dagger.Provides

@Module
internal class BooksListModule {
    @Provides
    @FragmentScope
    fun provideBooksListRepository(networkServicesFactory: NetworkServicesFactory): BooksListRepository {
        return networkServicesFactory.create(BooksListRepository::class)
    }
}
