package com.example.otchallenge.bookslist.api

import android.content.Context
import com.example.otchallenge.bookslist.internal.di.BooksListComponent

interface BooksListComponentProvider {

    fun provideBooksListComponent(): BooksListComponent.Builder

    companion object {
        internal fun provideFromContext(context: Context): BooksListComponent.Builder =
            (context.applicationContext as BooksListComponentProvider).provideBooksListComponent()
    }
}