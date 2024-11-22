package com.example.otchallenge.bookslist.api

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.otchallenge.bookslist.internal.presenter.BooksListPresenter
import javax.inject.Inject

class BooksListFragment : Fragment() {

    @Inject
    internal lateinit var presenter: BooksListPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        BooksListComponentProvider.provideFromContext(requireContext())
            .lifecycleScope(lifecycleScope)
            .build()
            .inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }
}