package ar.com.wolox.android.cookbook.tests

import android.support.v4.app.Fragment
import ar.com.wolox.android.cookbook.R
import ar.com.wolox.android.cookbook.WolmoTestActivity
import org.junit.Before

abstract class WolmoFragmentTest(val fragment: Fragment)
    : WolmoActivityTest<WolmoTestActivity>(WolmoTestActivity::class.java) {

    @Before
    fun setupWolmoFragmentTest() {
        activity.supportFragmentManager
                .beginTransaction()
                .replace(R.id.vActivityBaseContent, fragment)
                .commit()
    }
}