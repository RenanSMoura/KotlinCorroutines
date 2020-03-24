package io.renan.kotlin.coroutines.contexProvider

import kotlin.coroutines.CoroutineContext

class CoroutineContextProviderImpl(
    private val coroutineContext: CoroutineContext
) : CoroutineContextProvider {

    override fun context() = coroutineContext
}