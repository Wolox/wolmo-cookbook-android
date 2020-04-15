package ar.com.wolox.android.cookbook.analytics

import ar.com.wolox.android.cookbook.analytics.core.AnalyticsEvent

/**
 * Define the analytics events for this feature.
 *
 * Note: this is just an example, in the real life some of this events are probably useless.
 */
object EmptyEmailEvent : AnalyticsEvent("empty_email")
object EmptyPasswordEvent : AnalyticsEvent("empty_password")

class AgeRequestSuccessful(email: String) : AnalyticsEvent("age_request_successful", "email" to email)
class AgeRequestError(email: String) : AnalyticsEvent("age_request_error", "email" to email)

object OpenHelp : AnalyticsEvent("open_help")
