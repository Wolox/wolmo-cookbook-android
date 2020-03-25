package ar.com.wolox.android.cookbook.analytics.core

import android.content.Context
import androidx.core.os.bundleOf
import com.google.firebase.analytics.FirebaseAnalytics
import javax.inject.Inject

/**
 * This class is responsible of connecting to Firebase and logging [AnalyticsEvent]s.
 */
class AnalyticsManager @Inject constructor(context: Context) {

    private val firebaseAnalytics: FirebaseAnalytics by lazy {
        FirebaseAnalytics.getInstance(context)
    }

    private fun logEvent(name: String, vararg params: Pair<String, Any>) {
        firebaseAnalytics.logEvent(name, bundleOf(*params))
    }

    fun logEvent(event: AnalyticsEvent) = logEvent(event.name, *event.parameters)
}