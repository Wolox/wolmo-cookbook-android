package ar.com.wolox.android.cookbook.analytics

abstract class AnalyticsEvent(val params: List<Pair<String, String>>) {

    val name = javaClass.simpleName.toLowerSnakeCase()
}
