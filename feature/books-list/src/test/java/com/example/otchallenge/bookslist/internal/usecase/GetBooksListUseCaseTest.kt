package com.example.otchallenge.bookslist.internal.usecase

import com.example.otchallenge.bookslist.internal.repository.BooksListRepository
import com.example.otchallenge.bookslist.internal.repository.BooksListResponse
import com.example.otchallenge.bookslist.internal.repository.BooksListResults
import com.example.otchallenge.bookslist.internal.repository.TestBookFactory.createBook
import kotlinx.coroutines.test.runTest
import org.amshove.kluent.`should be equal to`
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.stub

class GetBooksListUseCaseTest {
    private val repository: BooksListRepository = mock()
    private val tested = GetBooksListUseCase(repository)

    @Test
    fun `given repository returns response with not null books list, when executed, then return books list from repository`() =
        runTest {
            // given
            val booksList = List(3) {
                createBook(it.toString())
            }
            repository.stub {
                onBlocking { getBooksList() } doReturn BooksListResponse(BooksListResults(booksList))
            }

            // when
            val result = tested.execute()

            // then
            result `should be equal to` booksList
        }

    @Test
    fun `given repository returns response with null books list, when executed, then return empty list`() =
        runTest {
            // given
            repository.stub {
                onBlocking { getBooksList() } doReturn BooksListResponse(BooksListResults(null))
            }

            // when
            val result = tested.execute()

            // then
            result `should be equal to` emptyList()
        }
}