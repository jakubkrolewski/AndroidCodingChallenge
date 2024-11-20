package com.example.otchallenge.bookslist.api

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.otchallenge.base.api.di.AppComponentProvider
import com.example.otchallenge.bookslist.internal.BooksListPresenter
import javax.inject.Inject
import com.example.otchallenge.bookslist.internal.di.DaggerBooksListComponent

class BooksListFragment : Fragment() {

    @Inject
    internal lateinit var presenter: BooksListPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerBooksListComponent.builder()
            .appComponent(AppComponentProvider.get(requireContext()))
            .build()
            .inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }
}