package io.renan.kotlin.coroutines.data.model

class Result<out T>(val value: T?, val throwable: Throwable?)