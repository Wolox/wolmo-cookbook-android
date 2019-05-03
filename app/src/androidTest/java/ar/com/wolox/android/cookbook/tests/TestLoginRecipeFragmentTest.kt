package ar.com.wolox.android.cookbook.tests

import androidx.fragment.app.Fragment
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.annotation.UiThreadTest
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.RootMatchers.withDecorView
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import ar.com.wolox.android.cookbook.R
import org.hamcrest.CoreMatchers.not
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TestLoginRecipeFragmentTest : WolmoFragmentTest<TestLoginRecipeView>(TestLoginRecipeFragment()) {

    private var fragment1: Class<out Fragment>? = null

    @Before
    @UiThreadTest
    fun prepareFragment() {
        val scenario = launchFragmentInContainer<TestLoginRecipeFragment>()
    }

    @Test
    @UiThreadTest
    fun should_ShowErrorMessage_When_ShowEmptyEmailError() {
        fragment.showEmptyEmailError()
        onView(withId(R.id.vTestLoginEmailInput)).check(matches(hasErrorText(R.string.test_login_empty_field)))
    }

    @Test
    @UiThreadTest
    fun should_ShowErrorMessage_When_ShowInvalidEmailError() {
        fragment.showInvalidEmailError()
        onView(withId(R.id.vTestLoginEmailInput)).check(matches(hasErrorText(R.string.test_login_invalid_email)))
    }

    @Test
    @UiThreadTest
    fun should_ShowErrorMessage_When_ShowEmptyPasswordError() {
        fragment.showEmptyPasswordError()
        onView(withId(R.id.vTestLoginPasswordInput)).check(matches(hasErrorText(R.string.test_login_empty_field)))
    }

    @Test
    @UiThreadTest
    fun should_ShowErrorToast_When_ShowLoginError() {
        fragment.showLoginError()
        onView(withText(R.string.test_login_login_error))
                .inRoot(withDecorView(not((activity.window.decorView)))).check(matches(isDisplayed()))
    }
}