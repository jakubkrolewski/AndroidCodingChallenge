package com.example.otchallenge.bookslist.internal.repository

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class BooksListResponse(val results: BooksListResults?)

@JsonClass(generateAdapter = true)
internal data class BooksListResults(val books: List<Book>?)

@JsonClass(generateAdapter = true)
internal data class Book(
    val title: String?,
    val description: String?,
    @Json(name = "book_image")
    val imageUrl: String?,
)