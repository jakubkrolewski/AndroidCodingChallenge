package com.example.otchallenge.bookslist.internal.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.otchallenge.bookslist.databinding.BooksListItemBinding
import com.example.otchallenge.bookslist.internal.repository.Book
import com.example.otchallenge.common.ui.BaseDiffCallback
import javax.inject.Inject

internal class BooksAdapter @Inject constructor() :
    ListAdapter<Book, BooksAdapter.UserViewHolder>(BaseDiffCallback { isbn10 ?: isbn13 ?: this }) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = BooksListItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    internal class UserViewHolder(private val binding: BooksListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(book: Book) {
            binding.book = book
            binding.executePendingBindings()
        }
    }
}
