package com.example.otchallenge.bookslist.internal.repository

internal object TestBookFactory {
    fun createBook(id: String = "0") = Book(
        title = "Title $id",
        description = "Description $id",
        isbn10 = "ISBN10 $id",
        isbn13 = "ISBN13 $id",
        imageUrl = null,
    )
}