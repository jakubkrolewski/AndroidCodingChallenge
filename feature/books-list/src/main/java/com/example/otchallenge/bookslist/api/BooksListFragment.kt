package com.example.otchallenge.bookslist.api

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.otchallenge.bookslist.databinding.BooksListFragmentBinding
import com.example.otchallenge.bookslist.internal.presenter.BooksListPresenter
import com.example.otchallenge.bookslist.internal.ui.BooksAdapter
import com.example.otchallenge.bookslist.internal.ui.BooksListViewImpl
import javax.inject.Inject

class BooksListFragment : Fragment() {

    @Inject
    internal lateinit var presenter: BooksListPresenter

    @Inject
    internal lateinit var booksAdapter: BooksAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        BooksListComponentProvider.provideFromContext(requireContext())
            .lifecycleScope(lifecycleScope)
            .build()
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = BooksListFragmentBinding.inflate(inflater, container, false)
        presenter.onViewCreated(BooksListViewImpl(binding, presenter, booksAdapter))
        return binding.root
    }

    override fun onDestroyView() {
        presenter.onViewDestroyed()
        super.onDestroyView()
    }
}