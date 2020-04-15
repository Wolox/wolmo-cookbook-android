package ar.com.wolox.android.cookbook.analytics.core

import android.content.Context
import com.google.firebase.analytics.FirebaseAnalytics
import dagger.Module
import dagger.Provides

@Module
object AnalyticsModule {

    @Provides
    @JvmStatic
    fun providesFirebaseAnalytics(context: Context) = FirebaseAnalytics.getInstance(context)
}