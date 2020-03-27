package ar.com.wolox.android.cookbook.analytics.core

import android.graphics.Point
import org.junit.Test

class AnalyticsEventTest {

    @Test(expected = InvalidAnalyticsEventNameException::class)
    fun `given an empty name when event init then it should throw a name exception`() {

        // GIVEN
        val name = "   "

        // WHEN
        val event = object : AnalyticsEvent(name) {}

        // THEN
        // exception is thrown
    }

    @Test(expected = InvalidAnalyticsEventNameException::class)
    fun `given a name with more than 40 characters when event init then it should throw a name exception`() {

        // GIVEN
        val name = "it_is_a_name_with_more_than_40_characters_"

        // WHEN
        val event = object : AnalyticsEvent(name) {}

        // THEN
        // exception is thrown
    }

    @Test(expected = InvalidAnalyticsEventNameException::class)
    fun `given a name that starts with a number when event init then it should throw a name exception`() {

        // GIVEN
        val name = "1_event"

        // WHEN
        val event = object : AnalyticsEvent(name) {}

        // THEN
        // exception is thrown
    }

    @Test(expected = InvalidAnalyticsEventNameException::class)
    fun `given a name with invalid characters when event init then it should throw a name exception`() {

        // GIVEN
        val name = "invalid-characters"

        // WHEN
        val event = object : AnalyticsEvent(name) {}

        // THEN
        // exception is thrown
    }

    @Test(expected = InvalidAnalyticsEventNameException::class)
    fun `given a name with a reserved starting when event init then it should throw a name exception`() {

        // GIVEN
        val name = "google_test"

        // WHEN
        val event = object : AnalyticsEvent(name) {}

        // THEN
        // exception is thrown
    }

    @Test(expected = InvalidAnalyticsEventParamException::class)
    fun `given an empty param key when event init then it should throw a param exception`() {

        // GIVEN
        val name = "event_name"
        val params = "     " to "test"

        // WHEN
        val event = object : AnalyticsEvent(name, params) {}

        // THEN
        // exception is thrown
    }

    @Test(expected = InvalidAnalyticsEventParamException::class)
    fun `given a param key with more than 40 characters when event init then it should throw a param exception`() {

        // GIVEN
        val name = "event_name"
        val params = "it_is_a_name_with_more_than_40_characters_" to "test"

        // WHEN
        val event = object : AnalyticsEvent(name, params) {}

        // THEN
        // exception is thrown
    }

    @Test(expected = InvalidAnalyticsEventParamException::class)
    fun `given a param key that starts with a number when event init then it should throw a param exception`() {

        // GIVEN
        val name = "event_name"
        val params = "1_key" to "test"

        // WHEN
        val event = object : AnalyticsEvent(name, params) {}

        // THEN
        // exception is thrown
    }

    @Test(expected = InvalidAnalyticsEventParamException::class)
    fun `given a param key with invalid characters when event init then it should throw a param exception`() {

        // GIVEN
        val name = "event_name"
        val params = "invalid-characters" to "123"

        // WHEN
        val event = object : AnalyticsEvent(name, params) {}

        // THEN
        // exception is thrown
    }

    @Test(expected = InvalidAnalyticsEventParamException::class)
    fun `given a param key with a reserved starting when event init then it should throw a param exception`() {

        // GIVEN
        val name = "event_name"
        val params = "firebase_test" to "123"

        // WHEN
        val event = object : AnalyticsEvent(name, params) {}

        // THEN
        // exception is thrown
    }

    @Test(expected = InvalidAnalyticsEventParamException::class)
    fun `given an invalid param value when event init then it should throw a param exception`() {

        // GIVEN
        val name = "event_name"
        val params = "param_key" to Point(123, 321)

        // WHEN
        val event = object : AnalyticsEvent(name, params) {}

        // THEN
        // exception is thrown
    }

    @Test(expected = InvalidAnalyticsEventParamException::class)
    fun `given an string param value with more than 100 characters when event init then it should throw a param exception`() {

        // GIVEN
        val name = "event_name"
        val params = "param_key" to "it_is_a_really_long_param_value_key_with_more_than_100_characters_which_is_the_limit_that_firebase_set_to_string_values"

        // WHEN
        val event = object : AnalyticsEvent(name, params) {}

        // THEN
        // exception is thrown
    }
}