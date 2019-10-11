package ar.com.wolox.android.cookbook.coroutines.core

import kotlinx.coroutines.isActive
import kotlin.coroutines.coroutineContext

val <T> T.unit: Unit
    get() = Unit

suspend inline fun <T, R> Iterable<T>.mapCooperative(transform: (T) -> R): List<R>? = run {
    map {
        if (!coroutineContext.isActive) {
            return@run null
        }
        transform(it)
    }
}
