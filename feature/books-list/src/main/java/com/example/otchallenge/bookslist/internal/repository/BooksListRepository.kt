package com.example.otchallenge.bookslist.internal.repository

import retrofit2.http.GET

internal interface BooksListRepository {

    @GET("lists/current/hardcover-fiction.json?api-key=KoRB4K5LRHygfjCL2AH6iQ7NeUqDAGAB&offset=0")
    suspend fun getBooksList(): BooksListResponse
}