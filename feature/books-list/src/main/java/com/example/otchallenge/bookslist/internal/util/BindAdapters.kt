package com.example.otchallenge.bookslist.internal.util

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

// TODO move to common
@BindingAdapter("imageUrl")
fun ImageView.loadImage(imageUrl: String?) {
    Glide.with(this)
        .load(imageUrl)
        .placeholder(ColorDrawable(Color.LTGRAY))
        .error(ColorDrawable(Color.LTGRAY))
        .into(this)
}