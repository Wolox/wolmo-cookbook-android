package ar.com.wolox.android.cookbook.analytics

import ar.com.wolox.android.cookbook.analytics.core.AnalyticsEvent

/**
 * Define the analytics events for this feature.
 *
 * Note: this is just an example, in the real life some of this events are probably useless.
 */
object EmptyEmailEvent : AnalyticsEvent("empty_email")
object EmptyPasswordEvent : AnalyticsEvent("empty_password")
class AgeRequested(email: String) : AnalyticsEvent("age_requested", "email" to email)
class AgeRequestSuccessful(email: String) : AnalyticsEvent("age_request_successful", "email" to email)
class AgeRequestError(email: String) : AnalyticsEvent("age_request_error", "email" to email)
class AgeRequestServiceUnavailable(email: String) : AnalyticsEvent("age_request_service_unavailable", "email" to email)