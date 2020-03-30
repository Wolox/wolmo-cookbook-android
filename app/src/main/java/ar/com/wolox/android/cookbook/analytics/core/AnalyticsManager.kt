package ar.com.wolox.android.cookbook.analytics.core

import android.app.Activity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.google.firebase.analytics.FirebaseAnalytics
import javax.inject.Inject
import javax.inject.Provider

/**
 * This class is responsible of connecting to Firebase and logging [AnalyticsEvent]s.
 */
class AnalyticsManager @Inject constructor(private val firebaseAnalytics: Provider<FirebaseAnalytics>) {

    private fun logEvent(name: String, vararg params: Pair<String, Any>) {
        firebaseAnalytics.get().logEvent(name, bundleOf(*params))
    }

    fun logEvent(event: AnalyticsEvent) = logEvent(event.name, *event.parameters)

    fun setCurrentScreen(fragment: Fragment) = setCurrentScreen(fragment.requireActivity(), fragment::class.java.simpleName)

    fun setCurrentScreen(activity: Activity, screenName: String) = firebaseAnalytics.get().setCurrentScreen(activity, screenName, null)
}