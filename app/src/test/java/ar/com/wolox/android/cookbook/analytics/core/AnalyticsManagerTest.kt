package ar.com.wolox.android.cookbook.analytics.core

import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics
import com.nhaarman.mockitokotlin2.argumentCaptor
import com.nhaarman.mockitokotlin2.verify
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner
import javax.inject.Provider

@RunWith(RobolectricTestRunner::class)
class AnalyticsManagerTest {

    private lateinit var analyticsManager: AnalyticsManager

    @Mock
    lateinit var firebaseAnalytics: FirebaseAnalytics

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        analyticsManager = AnalyticsManager(Provider<FirebaseAnalytics> { firebaseAnalytics })
    }

    @Test
    fun `given a event when logging it then the firebase analytics should be called`() {

        val stringCaptor = argumentCaptor<String>()
        val bundleCaptor = argumentCaptor<Bundle>()

        // GIVEN
        val name = "test_event"
        val params = listOf("param_key" to 12.toDouble(), "other_param" to "test")
        val event = object : AnalyticsEvent(name, *params.toTypedArray()) {}

        // WHEN
        analyticsManager.logEvent(event)

        // THEN
        verify(firebaseAnalytics).logEvent(stringCaptor.capture(), bundleCaptor.capture())
        assertEquals(name, stringCaptor.firstValue)
        assertEquals(params[0].second, bundleCaptor.firstValue.getDouble(params[0].first))
        assertEquals(params[1].second, bundleCaptor.firstValue.getString(params[1].first))
    }
}