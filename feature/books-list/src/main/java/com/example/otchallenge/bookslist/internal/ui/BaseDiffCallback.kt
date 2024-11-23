package com.example.otchallenge.bookslist.internal.ui

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil

// TODO move to common
internal class BaseDiffCallback<T : Any>(
    private val idExtractor: T.() -> Any?
) : DiffUtil.ItemCallback<T>() {

    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem.idExtractor() == newItem.idExtractor()
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem == newItem
    }
}