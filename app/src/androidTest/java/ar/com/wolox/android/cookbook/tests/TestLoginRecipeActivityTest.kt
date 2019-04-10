package ar.com.wolox.android.cookbook.tests

import androidx.annotation.StringRes
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.hasErrorText
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import ar.com.wolox.android.cookbook.R
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TestLoginRecipeActivityTest {

    @Rule
    @JvmField
    var activityRule = ActivityTestRule<TestLoginRecipeActivity>(TestLoginRecipeActivity::class.java)

    @Before
    fun setup() {
        activityRule.activity.supportFragmentManager.beginTransaction()
    }

    @Test
    fun should_ShowEmailEmptyError_When_EmailInputIsEmpty() {
        onView(withId(R.id.vTestLoginLoginBtn)).perform(click())
        onView(withId(R.id.vTestLoginEmailInput)).check(matches(hasErrorText(R.string.test_login_empty_field)))
    }

    @Test
    fun should_ShowEmailInvalidError_When_EmailIsNotWellFormatted() {
        onView(withId(R.id.vTestLoginEmailInput)).perform(typeText(INVALID_EMAIL))
        onView(withId(R.id.vTestLoginLoginBtn)).perform(click())
        onView(withId(R.id.vTestLoginEmailInput)).check(matches(hasErrorText(R.string.test_login_invalid_email)))
    }

    @Test
    fun should_ShowPasswordEmptyError_When_PasswordInputIsEmpty() {
        onView(withId(R.id.vTestLoginLoginBtn)).perform(click())
        onView(withId(R.id.vTestLoginPasswordInput)).check(matches(hasErrorText(R.string.test_login_empty_field)))
    }

    private fun hasErrorText(@StringRes errorRes: Int) = hasErrorText(activityRule.activity.getString(errorRes))

    companion object {
        const val INVALID_EMAIL = "invalid"
    }
}