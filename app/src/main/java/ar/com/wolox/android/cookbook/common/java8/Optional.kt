package ar.com.wolox.android.cookbook.common.java8

/**
 * A container object which may or may not contain a non-null value.
 */
sealed class Optional<out T : Any> {

    companion object {

        fun <T : Any> ofNullable(nullableValue: T?) =
                if (nullableValue == null) {
                    Empty
                } else {
                    Just(nullableValue)
                }
    }
}

class Just<out T : Any>(val value: T) : Optional<T>()

object Empty : Optional<Nothing>()