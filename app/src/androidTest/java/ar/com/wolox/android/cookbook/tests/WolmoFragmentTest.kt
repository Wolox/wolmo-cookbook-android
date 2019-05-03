package ar.com.wolox.android.cookbook.tests

import androidx.fragment.app.Fragment
import androidx.test.annotation.UiThreadTest
import ar.com.wolox.android.cookbook.WolmoTestActivity
import org.junit.Before

abstract class WolmoFragmentTest<V>(val fragment: V)
    : WolmoActivityTest<WolmoTestActivity>(WolmoTestActivity::class.java) {

    @Before
    @UiThreadTest
    fun setupWolmoFragmentTest() {
        activity.initFragment(fragment as Fragment)
    }
}