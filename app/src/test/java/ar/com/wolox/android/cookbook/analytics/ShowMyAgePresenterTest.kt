package ar.com.wolox.android.cookbook.analytics

import ar.com.wolox.android.cookbook.analytics.core.AnalyticsEvent
import ar.com.wolox.android.cookbook.analytics.core.AnalyticsManager
import ar.com.wolox.wolmo.core.tests.CoroutineTestRule
import ar.com.wolox.wolmo.core.tests.WolmoPresenterTest
import com.nhaarman.mockitokotlin2.argumentCaptor
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock

class ShowMyAgePresenterTest : WolmoPresenterTest<ShowMyAgeView, ShowMyAgePresenter>() {

    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutineTestRule = CoroutineTestRule(runOnAllTests = true)

    @Mock
    lateinit var userRepository: UserRepository

    @Mock
    lateinit var analyticsManager: AnalyticsManager

    override fun getPresenterInstance() = ShowMyAgePresenter(userRepository, analyticsManager)

    @Test
    fun `given an empty email when age is requested then empty email event is logged`() = runBlocking {

        // GIVEN
        val email = ""
        val password = "1234"

        // WHEN
        presenter.onAgeRequestButtonClicked(email, password)?.join()

        // THEN
        with(argumentCaptor<AnalyticsEvent>()) {
            verify(analyticsManager).logEvent(capture())
            assertEquals(firstValue, EmptyEmailEvent)
        }
    }

    @Test
    fun `given an empty password when age is requested then empty password event is logged`() = runBlocking {

        // GIVEN
        val email = "email@gmail.com"
        val password = ""

        // WHEN
        presenter.onAgeRequestButtonClicked(email, password)?.join()

        // THEN
        with(argumentCaptor<AnalyticsEvent>()) {
            verify(analyticsManager).logEvent(capture())
            assertEquals(firstValue, EmptyPasswordEvent)
        }
    }

    @Test
    fun `given a service unavailable when age is requested then age request service unavailable event is logged`() = runBlocking {

        // GIVEN
        val email = "email@gmail.com"
        val password = "1234"
        whenever(userRepository.getUser(anyString(), anyString())).thenThrow(ServiceUnavailableException)

        // WHEN
        presenter.onAgeRequestButtonClicked(email, password)?.join()

        // THEN
        with(argumentCaptor<AnalyticsEvent>()) {
            verify(analyticsManager).logEvent(capture())
            assertThat(firstValue, instanceOf(AgeRequestServiceUnavailable::class.java))
        }
    }

    @Test
    fun `given an invalid user when age is requested then age request error event is logged`() = runBlocking {

        // GIVEN
        val email = "email@gmail.com"
        val password = "1234"
        whenever(userRepository.getUser(anyString(), anyString())).thenReturn(null)

        // WHEN
        presenter.onAgeRequestButtonClicked(email, password)?.join()

        // THEN
        with(argumentCaptor<AnalyticsEvent>()) {
            verify(analyticsManager).logEvent(capture())
            assertThat(firstValue, instanceOf(AgeRequestError::class.java))
        }
    }

    @Test
    fun `given a valid user when age is requested then age request successful event is logged`() = runBlocking {

        // GIVEN
        val email = "email@gmail.com"
        val password = "1234"
        whenever(userRepository.getUser(anyString(), anyString())).thenReturn(mock())

        // WHEN
        presenter.onAgeRequestButtonClicked(email, password)?.join()

        // THEN
        with(argumentCaptor<AnalyticsEvent>()) {
            verify(analyticsManager).logEvent(capture())
            assertThat(firstValue, instanceOf(AgeRequestSuccessful::class.java))
        }
    }

    @Test
    fun `when view is visible then set current screen`() = runBlocking {

        // GIVEN

        // WHEN
        presenter.onVisible()

        // THEN
        verify(view).setCurrentScreen(eq(analyticsManager))
    }
}