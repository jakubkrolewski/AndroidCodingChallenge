package com.example.otchallenge.bookslist.internal.presenter

import com.example.otchallenge.bookslist.internal.util.logNetworkError
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

// TODO move to common
@Suppress("UNCHECKED_CAST")
sealed class ViewModelState<T> {

    val inProgress get() = this is InProgress

    val successData: T? get() = (this as? CanHaveData<T>)?.data

    data class Success<T>(override val data: T) : ViewModelState<T>(), CanHaveData<T>

    data class InProgress<T>(override val data: T? = null) : ViewModelState<T>(), CanHaveData<T>

    data class Error<T>(val throwable: Throwable) : ViewModelState<T>()
}

private interface CanHaveData<T> {
    val data: T?
}

fun <T> fetchDataAsStates(
    newDataProvider: suspend () -> T,
    actionDescriptionProvider: () -> String,
    lastDataProvider: (() -> T?)? = null,
): Flow<ViewModelState<T>> = flow {
    emit(ViewModelState.InProgress(lastDataProvider?.invoke()))

    emit(
        try {
            ViewModelState.Success(newDataProvider())
        } catch (e: Exception) {
            logNetworkError(e, actionDescriptionProvider)
            ViewModelState.Error(e)
        }
    )
}