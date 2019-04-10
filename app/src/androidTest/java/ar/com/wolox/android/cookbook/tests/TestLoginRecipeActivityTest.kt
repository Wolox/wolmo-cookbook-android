package ar.com.wolox.android.cookbook.tests

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import ar.com.wolox.android.cookbook.R
import org.junit.Test

class TestLoginRecipeActivityTest : WolmoActivityTest<TestLoginRecipeActivity>() {

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

    companion object {
        const val INVALID_EMAIL = "invalid"
    }
}