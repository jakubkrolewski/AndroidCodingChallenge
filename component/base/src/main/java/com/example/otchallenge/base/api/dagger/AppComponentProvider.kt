package com.example.otchallenge.base.api.dagger

import android.content.Context

object AppComponentProvider {
    fun get(context: Context): AppComponent = (context.applicationContext as HasAppComponent).appComponent
}