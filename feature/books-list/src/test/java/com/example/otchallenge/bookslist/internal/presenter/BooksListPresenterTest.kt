package com.example.otchallenge.bookslist.internal.presenter

import com.example.otchallenge.bookslist.R
import com.example.otchallenge.bookslist.internal.repository.TestBookFactory.createBook
import com.example.otchallenge.bookslist.internal.usecase.GetBooksListUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.doThrow
import org.mockito.kotlin.mock
import org.mockito.kotlin.stub
import org.mockito.kotlin.verify

@OptIn(ExperimentalCoroutinesApi::class)
class BooksListPresenterTest {

    private val getBooksListUseCase: GetBooksListUseCase = mock()
    private val view: BooksListPresenter.View = mock()

    @Test
    fun `given getBooksListUseCase execution is in progress, when view created, then show empty view with progress state`() =
        runTest(StandardTestDispatcher()) {
            // given
            val books = List(3) { createBook(it.toString()) }
            getBooksListUseCase.stub {
                onBlocking { execute() } doReturn books
            }
            val tested = createPresenter(backgroundScope)

            // when
            tested.onViewCreated(view)

            // then
            verify(view).setInProgress(true)
            verify(view).showEmptyView(R.string.books_list_empty_state_loading)
            verify(view).hideBooksList()
        }

    @Test
    fun `given getBooksListUseCase returns valid result, when view created, then show books list`() =
        runTest(UnconfinedTestDispatcher()) {
            // given
            val books = List(3) { createBook(it.toString()) }
            getBooksListUseCase.stub {
                onBlocking { execute() } doReturn books
            }
            val tested = createPresenter(backgroundScope)

            // when
            tested.onViewCreated(view)

            // then
            verify(view).setInProgress(false)
            verify(view).hideEmptyView()
            verify(view).showBooksList(books)
        }

    @Test
    fun `given getBooksListUseCase throws exception, when view created, then show empty state with error`() =
        runTest(UnconfinedTestDispatcher()) {
            // given
            getBooksListUseCase.stub {
                onBlocking { execute() } doThrow RuntimeException()
            }
            val tested = createPresenter(backgroundScope)

            // when
            tested.onViewCreated(view)

            // then
            verify(view).setInProgress(false)
            verify(view).showEmptyView(R.string.books_list_empty_state_error)
            verify(view).hideBooksList()
        }

    @Test
    fun `given getBooksListUseCase returns empty result, when view created, then show no results view`() =
        runTest(UnconfinedTestDispatcher()) {
            // given
            getBooksListUseCase.stub {
                onBlocking { execute() } doReturn emptyList()
            }
            val tested = createPresenter(backgroundScope)

            // when
            tested.onViewCreated(view)

            // then
            verify(view).setInProgress(false)
            verify(view).showEmptyView(R.string.books_list_empty_state_no_results)
            verify(view).hideBooksList()
        }

    @Test
    fun `given getBooksListUseCase returns valid result, when refresh event received, then show books list with refreshed result`() =
        runTest(UnconfinedTestDispatcher()) {
            // given
            val books1 = List(3) { createBook(it.toString()) }
            val books2 = List(5) { createBook(it.toString()) }
            getBooksListUseCase.stub {
                onBlocking { execute() }.thenReturn(books1, books2)
            }
            val tested = createPresenter(backgroundScope)
            tested.onViewCreated(view)
            Mockito.reset(view)

            // when
            tested.onEvent(BooksListEvent.Refresh)

            // then
            verify(view).setInProgress(false)
            verify(view).hideEmptyView()
            verify(view).showBooksList(books2)
        }

    private fun createPresenter(lifecycleScope: CoroutineScope) =
        BooksListPresenter(lifecycleScope, getBooksListUseCase, UnconfinedTestDispatcher())
}