package com.example.otchallenge.bookslist.internal.presenter

import com.example.otchallenge.base.api.di.FragmentScope
import com.example.otchallenge.bookslist.internal.usecase.GetBooksListUseCase
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject

@FragmentScope
internal class BooksListPresenter @Inject constructor(
//    private val lifecycleScope: CoroutineScope,
    private val getBooksListUseCase: GetBooksListUseCase,
    ) {

    interface View {

    }
}