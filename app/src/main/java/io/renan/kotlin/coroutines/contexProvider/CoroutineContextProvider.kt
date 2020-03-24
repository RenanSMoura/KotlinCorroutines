package io.renan.kotlin.coroutines.contexProvider

import kotlin.coroutines.CoroutineContext

interface CoroutineContextProvider {

    fun context(): CoroutineContext
}