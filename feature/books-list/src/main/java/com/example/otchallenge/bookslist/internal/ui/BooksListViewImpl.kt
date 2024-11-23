package com.example.otchallenge.bookslist.internal.ui

import androidx.core.view.isVisible
import com.example.otchallenge.bookslist.databinding.BooksListFragmentBinding
import com.example.otchallenge.bookslist.internal.presenter.BooksListEvent
import com.example.otchallenge.bookslist.internal.presenter.BooksListPresenter
import com.example.otchallenge.bookslist.internal.repository.Book

internal class BooksListViewImpl(
    private val binding: BooksListFragmentBinding,
    private val presenter: BooksListPresenter,
    private val adapter: BooksAdapter,
): BooksListPresenter.View {

    init {
        binding.swipeContainer.setOnRefreshListener { presenter.onEvent(BooksListEvent.Refresh) }
        binding.booksList.adapter = adapter
    }

    override fun setInProgress(inProgress: Boolean) {
        binding.swipeContainer.isRefreshing = inProgress
    }

    override fun showBooksList(booksList: List<Book>) {
        binding.booksList.isVisible = true
        adapter.submitList(booksList)
    }

    override fun hideBooksList() {
        binding.booksList.isVisible = false
    }

    override fun showEmptyView(messageId: Int) {
        binding.emptyView.apply {
            isVisible = true
            text = context.getString(messageId)
        }
    }

    override fun hideEmptyView() {
        binding.emptyView.isVisible = false
    }
}