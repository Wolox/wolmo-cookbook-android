package ar.com.wolox.android.cookbook.tests

import android.view.View
import androidx.annotation.StringRes
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import ar.com.wolox.android.cookbook.R
import ar.com.wolox.android.cookbook.datasync.DataSyncRecipeActivity
import org.hamcrest.Matcher
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TestLoginRecipeFragmentTest {

    private lateinit var scenario: FragmentScenario<TestLoginRecipeFragment>

    @Before
    fun prepareFragment() {
        Intents.init()
        scenario = launchFragmentInContainer<TestLoginRecipeFragment>()
    }

    @After
    fun clean() {
        Intents.release()
    }

    @Test
    fun should_ShowErrorMessage_When_ShowEmptyEmailError() {
        scenario.onFragment {
            it.showEmptyEmailError()
        }
        onView(withId(R.id.vTestLoginEmailInput)).check(matches(hasErrorText(R.string.test_login_empty_field)))
    }

    @Test
    fun should_ShowErrorMessage_When_ShowInvalidEmailError() {
        scenario.onFragment {
            it.showInvalidEmailError()
        }
        onView(withId(R.id.vTestLoginEmailInput)).check(matches(hasErrorText(R.string.test_login_invalid_email)))
    }

    @Test
    fun should_ShowErrorMessage_When_ShowEmptyPasswordError() {
        scenario.onFragment {
            it.showEmptyPasswordError()
        }
        onView(withId(R.id.vTestLoginPasswordInput)).check(matches(hasErrorText(R.string.test_login_empty_field)))
    }

    @Test
    fun should_GoToDataSyncRecipe_When_GoToNextWindow() {
        scenario.onFragment {
            it.goToNextWindow()
        }
        intended(hasComponent(DataSyncRecipeActivity::class.java.name))
    }

    private fun hasErrorText(@StringRes errorRes: Int): Matcher<View> {
        lateinit var string: String
        scenario.onFragment {
            string = it.requireActivity().getString(errorRes)
        }
        return ViewMatchers.hasErrorText(string)
    }
}