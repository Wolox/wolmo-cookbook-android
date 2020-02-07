package ar.com.wolox.android.cookbook.analytics.core

import android.content.Context
import androidx.core.os.bundleOf
import com.google.firebase.analytics.FirebaseAnalytics
import javax.inject.Inject

class AnalyticsManager @Inject constructor(context: Context) {

    val firebaseAnalytics: FirebaseAnalytics by lazy {
        FirebaseAnalytics.getInstance(context)
    }

    fun logEvent(id: String, vararg params: Pair<String, String>) {
        firebaseAnalytics.logEvent(id, bundleOf(*params))
    }

    fun logEvent(event: AnalyticsEvent) {
        firebaseAnalytics.logEvent(event.name, bundleOf(*event.params.toTypedArray()))
    }
}