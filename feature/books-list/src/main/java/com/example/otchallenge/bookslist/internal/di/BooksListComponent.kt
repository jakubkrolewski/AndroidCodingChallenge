package com.example.otchallenge.bookslist.internal.di

import com.example.otchallenge.base.api.di.FragmentScope
import com.example.otchallenge.bookslist.api.BooksListFragment
import dagger.BindsInstance
import dagger.Subcomponent
import kotlinx.coroutines.CoroutineScope

@FragmentScope
@Subcomponent(
    modules = [
        BooksListModule::class,
    ]
)
interface BooksListComponent {
    fun inject(fragment: BooksListFragment)

    @Subcomponent.Builder
    interface Builder {
        fun build(): BooksListComponent

        @BindsInstance
        fun lifecycleScope(lifecycleScope: CoroutineScope): Builder
    }
}
