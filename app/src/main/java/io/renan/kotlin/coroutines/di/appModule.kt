package io.renan.kotlin.coroutines.di

import io.renan.kotlin.coroutines.contexProvider.CoroutineContextProvider
import io.renan.kotlin.coroutines.contexProvider.CoroutineContextProviderImpl
import io.renan.kotlin.coroutines.data.database.MovieDatabase
import io.renan.kotlin.coroutines.domain.repository.MovieRepository
import io.renan.kotlin.coroutines.domain.repository.MovieRepositoryImpl
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

fun appModule() = module {

    single { MovieDatabase.create(androidContext()) } // database

    single { get<MovieDatabase>().movieDao() } // dao

    single { MovieRepositoryImpl(get(), get()) as MovieRepository } // repository

    single { CoroutineContextProviderImpl(Dispatchers.IO) as CoroutineContextProvider }
}