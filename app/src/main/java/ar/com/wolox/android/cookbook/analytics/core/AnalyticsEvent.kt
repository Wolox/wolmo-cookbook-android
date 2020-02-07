package ar.com.wolox.android.cookbook.analytics.core

abstract class AnalyticsEvent(val name: String, val params: List<Pair<String, String>> = emptyList())
