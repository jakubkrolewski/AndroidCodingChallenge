package com.example.otchallenge.bookslist.internal.presenter

internal sealed class BooksListEvent {
    data object Refresh : BooksListEvent()
}