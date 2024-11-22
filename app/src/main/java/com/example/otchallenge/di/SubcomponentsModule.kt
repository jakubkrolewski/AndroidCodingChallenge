package com.example.otchallenge.di

import com.example.otchallenge.bookslist.internal.di.BooksListComponent
import dagger.Module

/***
 * this is a module which will register all the sub-components used in the project
 */
@Module(subcomponents = [BooksListComponent::class])
interface SubcomponentsModule