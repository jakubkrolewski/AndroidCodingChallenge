package com.example.otchallenge.common.util

import timber.log.Timber
import java.io.IOException

typealias Logger = Timber

fun logNetworkError(throwable: Throwable, actionDescription: () -> String) {
    when (throwable) {
        is IOException -> {
            Logger.w(throwable, "Connection problem")
        }

        is Error -> {
            Logger.e(throwable, "Fatal error while performing action: ${actionDescription()}")
        }

        else -> {
            Logger.e(throwable, "${actionDescription()} failed")
        }
    }
}
