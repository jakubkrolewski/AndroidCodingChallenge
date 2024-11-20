package com.example.otchallenge.base.api.di

import android.content.Context

object AppComponentProvider {
    fun get(context: Context): AppComponent = (context.applicationContext as HasAppComponent).appComponent
}