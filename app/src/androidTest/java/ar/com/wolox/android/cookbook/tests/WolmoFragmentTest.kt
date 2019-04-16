package ar.com.wolox.android.cookbook.tests

import android.support.v4.app.Fragment
import ar.com.wolox.android.cookbook.WolmoTestActivity
import org.junit.Before

abstract class WolmoFragmentTest<V>(val fragment: V)
    : WolmoActivityTest<WolmoTestActivity>(WolmoTestActivity::class.java) {

    @Before
    fun setupWolmoFragmentTest() {
        activity.initFragment(fragment as Fragment)
    }
}