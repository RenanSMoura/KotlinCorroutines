package io.renan.kotlin.coroutines.di

import io.renan.kotlin.coroutines.presentation.MoviesPresenter
import io.renan.kotlin.coroutines.presentation.MoviesPresenterImpl
import org.koin.dsl.module

fun presenterModule() = module {
    single { MoviesPresenterImpl(get()) as MoviesPresenter }
}