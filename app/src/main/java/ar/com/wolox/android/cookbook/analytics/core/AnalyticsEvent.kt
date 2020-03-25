package ar.com.wolox.android.cookbook.analytics.core

abstract class AnalyticsEvent(val name: String, vararg val params: Pair<String, String> = emptyArray())
