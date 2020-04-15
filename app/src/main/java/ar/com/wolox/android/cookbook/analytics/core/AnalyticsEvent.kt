package ar.com.wolox.android.cookbook.analytics.core

import com.google.firebase.analytics.FirebaseAnalytics
import java.lang.Exception

/**
 * This class represents a custom event for Firebase. Using [FirebaseAnalytics.Event] is recommended for optimal reporting.
 *
 * The [name] of the event should contain 1 to 40 alphanumeric characters or underscores.
 * It must start with an alphabetic character.
 * Some event names are reserved. See [FirebaseAnalytics.Event] for the list of reserved event names.
 * The "firebase_", "google_" and "ga_" prefixes are reserved and should not be used.
 * Note that event names are case-sensitive and that logging two events whose names differ only in case will result in two distinct events.
 *
 * The event [parameters] could be empty and are represented by a key and a value.
 * Parameter names can be up to 40 characters long
 * and must start with an alphabetic character
 * and contain only alphanumeric characters and underscores.
 * String, long and double param types are supported. String parameter values can be up to 100 characters long.
 * The "firebase_", "google_" and "ga_" prefixes are reserved and should not be used for parameter names.
 *
 * Most of these validations are performed at the init of the class and will throw an [AnalyticsEventException] if they are not fulfilled.
 */
abstract class AnalyticsEvent(
    val name: String,
    vararg val parameters: Pair<String, Any> = emptyArray()
) {

    init {
        when {
            name.isBlank() ->
                throw InvalidAnalyticsEventNameException("The event name shouldn't be empty")
            name.length > 40 ->
                throw InvalidAnalyticsEventNameException("The event name '$name' can be up to 40 characters")
            !name.first().isLetter() ->
                throw InvalidAnalyticsEventNameException("The event name '$name' should start with an alphabetic character")
            name.filterNot { it.isLetterOrDigit() || it == '_' }.isNotEmpty() ->
                throw InvalidAnalyticsEventNameException("The event name '$name' should only contain alphanumeric characters or underscores")
            name.startsWith("firebase_") || name.startsWith("google_") || name.startsWith("ga_") ->
                throw InvalidAnalyticsEventNameException("The event name '$name' is reserved: it can't start with either 'firebase_' or 'google_' or 'ga_'")
        }

        parameters.forEach {
            val key = it.first
            val value = it.second
            when {
                key.isBlank() ->
                    throw InvalidAnalyticsEventParamException("The $name parameter key shouldn't be empty")
                key.length > 40 ->
                    throw InvalidAnalyticsEventParamException("The $name parameter key '$key' can be up to 40 characters")
                !key.first().isLetter() ->
                    throw InvalidAnalyticsEventParamException("The $name parameter key '$key' should start with an alphabetic character")
                key.filterNot { letter -> letter.isLetterOrDigit() || letter == '_' }.isNotEmpty() ->
                    throw InvalidAnalyticsEventParamException("The $name parameter key '$key' should only contain alphanumeric characters or underscores")
                key.startsWith("firebase_") || key.startsWith("google_") || key.startsWith("ga_") ->
                    throw InvalidAnalyticsEventParamException("The $name parameter key '$key' is reserved: it can't start with either 'firebase_' or 'google_' or 'ga_'")
                value !is String && value !is Long && value !is Double ->
                    throw InvalidAnalyticsEventParamException("The $name parameter '$key' value can only be String, Double or Long")
                value is String && value.length > 100 ->
                    throw InvalidAnalyticsEventParamException("The $name parameter '$key' value can be up to 100 characters")
            }
        }
    }
}

open class AnalyticsEventException(message: String) : Exception(message)
class InvalidAnalyticsEventNameException(message: String) : AnalyticsEventException(message)
class InvalidAnalyticsEventParamException(message: String) : AnalyticsEventException(message)
