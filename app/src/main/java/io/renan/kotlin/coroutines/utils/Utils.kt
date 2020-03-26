package io.renan.kotlin.coroutines.utils

import android.util.Log
import kotlin.coroutines.CoroutineContext


fun logCoroutines(methodName : String, coroutineContext: CoroutineContext) {
    Log.d("TestCoroutine", "Thread for is $methodName on ${Thread.currentThread().name}" +
            "in context ${coroutineContext}")
}