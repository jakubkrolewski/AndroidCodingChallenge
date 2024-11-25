package com.example.otchallenge.bookslist.internal.presenter

import androidx.annotation.StringRes
import com.example.otchallenge.base.api.di.FragmentScope
import com.example.otchallenge.bookslist.R
import com.example.otchallenge.bookslist.internal.repository.Book
import com.example.otchallenge.bookslist.internal.usecase.GetBooksListUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

internal typealias BooksListState = ViewModelState<List<Book>>

@FragmentScope
internal class BooksListPresenter(
    private val lifecycleScope: CoroutineScope,
    private val getBooksListUseCase: GetBooksListUseCase,
    private val ioDispatcher: CoroutineDispatcher,
) {

    @Inject
    constructor(lifecycleScope: CoroutineScope, getBooksListUseCase: GetBooksListUseCase) :
            this(lifecycleScope, getBooksListUseCase, Dispatchers.IO)

    private var view: View? = null

    private val state: MutableStateFlow<BooksListState> = MutableStateFlow(ViewModelState.InProgress())

    private val loadTriggers =
        MutableSharedFlow<Unit>(replay = 1, onBufferOverflow = BufferOverflow.DROP_LATEST)

    init {
        fetchListOnTrigger()
        triggerLoad()
        observeState()
    }

    private fun triggerLoad() {
        require(loadTriggers.tryEmit(Unit))
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    private fun fetchListOnTrigger() {
        lifecycleScope.launch {
            loadTriggers
                .flatMapLatest { getBooksListState() }
                .flowOn(ioDispatcher)
                .collect {
                    state.value = it
                }
        }
    }

    private fun getBooksListState(): Flow<BooksListState> {
        return fetchDataAsStates(
            newDataProvider = {
                getBooksListUseCase.execute()
            },
            actionDescriptionProvider = { "Getting books list" },
            lastDataProvider = { state.value.successData }
        )
    }

    private fun observeState() {
        lifecycleScope.launch {
            state.collect(::applyStateToView)
        }
    }

    fun onEvent(event: BooksListEvent) {
        when (event) {
            BooksListEvent.Refresh -> triggerLoad()
        }
    }

    fun onViewCreated(view: View) {
        this.view = view
        applyStateToView(state.value)
    }

    fun onViewDestroyed() {
        this.view = null
    }

    private fun applyStateToView(state: BooksListState) {
        val view = view ?: return

        view.setInProgress(state.inProgress)

        val booksList = state.successData
        if (booksList.isNullOrEmpty()) {
            view.hideBooksList()
            val emptyViewMessage = when (state) {
                is ViewModelState.Error -> R.string.books_list_empty_state_error
                is ViewModelState.InProgress -> R.string.books_list_empty_state_loading
                is ViewModelState.Success -> R.string.books_list_empty_state_no_results
            }
            view.showEmptyView(emptyViewMessage)
        } else {
            view.hideEmptyView()
            view.showBooksList(booksList)
        }
    }

    interface View {
        fun setInProgress(inProgress: Boolean)

        fun showBooksList(booksList: List<Book>)

        fun hideBooksList()

        fun showEmptyView(@StringRes messageId: Int)

        fun hideEmptyView()
    }
}
