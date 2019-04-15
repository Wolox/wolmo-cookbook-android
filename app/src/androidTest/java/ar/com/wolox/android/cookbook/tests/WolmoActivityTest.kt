package ar.com.wolox.android.cookbook.tests

import android.view.View
import androidx.annotation.StringRes
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.rule.ActivityTestRule
import ar.com.wolox.wolmo.core.activity.WolmoActivity
import org.hamcrest.Matcher
import org.junit.Before
import org.junit.Rule

abstract class WolmoActivityTest<A : WolmoActivity>(activityClass: Class<A>) {

    @Rule
    @JvmField
    var activityRule = ActivityTestRule(activityClass)

    lateinit var activity: A

    @Before
    fun setupWolmoActivityTest() {
        activity = activityRule.activity
    }

    protected fun hasErrorText(@StringRes errorRes: Int): Matcher<View> =
            ViewMatchers.hasErrorText(activityRule.activity.getString(errorRes))
}