package com.example.otchallenge.bookslist.internal.ui

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.otchallenge.bookslist.internal.repository.Book
import com.example.otchallenge.bookslist.internal.util.Logger
import javax.inject.Inject

internal class BooksAdapter @Inject constructor() :
    ListAdapter<Book, BooksAdapter.UserViewHolder>(BaseDiffCallback { isbn10 ?: isbn13 ?: this }) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    internal class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(book: Book) {
            // use the model to bind data to the views
            Logger.d(book.toString())
        }
    }
}
