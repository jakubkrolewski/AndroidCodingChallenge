package com.example.otchallenge.bookslist.internal.usecase

import com.example.otchallenge.base.api.di.FragmentScope
import com.example.otchallenge.bookslist.internal.repository.Book
import com.example.otchallenge.bookslist.internal.repository.BooksListRepository
import javax.inject.Inject

@FragmentScope
internal class GetBooksListUseCase @Inject constructor(private val repository: BooksListRepository) {

    suspend fun execute(): List<Book> {
        return repository.getBooksList().results?.books.orEmpty()
    }
}