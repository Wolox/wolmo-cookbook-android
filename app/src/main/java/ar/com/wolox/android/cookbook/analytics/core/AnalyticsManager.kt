package ar.com.wolox.android.cookbook.analytics.core

import android.app.Activity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.crashlytics.android.Crashlytics
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

    /** Login a custom [event] to Firebase Analytics. */
    fun logEvent(event: AnalyticsEvent) = logEvent(event.name, *event.parameters)

    /**
     * Login a crash [throwable] to Crashlytics.
     * It's a non-fatal error so to be able to see this on the Firebase Console,
     * the "Event type = Crashes" filter should be removed.
     */
    fun logCrash(throwable: Throwable) = Crashlytics.logException(throwable)

    /**
     * Set the current screen for the case there're more than one [fragment] per activity.
     * (Firebase just log the activity changes)
     */
    fun setCurrentScreen(fragment: Fragment) =
        setCurrentScreen(fragment.requireActivity(), fragment::class.java.simpleName)

    private fun setCurrentScreen(activity: Activity, screenName: String) =
        firebaseAnalytics.get().setCurrentScreen(activity, screenName, null)
}