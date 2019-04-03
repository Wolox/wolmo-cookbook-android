package ar.com.wolox.android.cookbook.tests

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@RunWith(AndroidJUnit4::class)
class TestLoginRecipeFragmentTest {

    @Mock
    private lateinit var presenter: TestLoginRecipePresenter

    private lateinit var fragment: TestLoginRecipeFragment

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(true)
        fragment = TestLoginRecipeFragment()
    }

    @Test
    fun testRandom() {
        assertThat(true, `is`(true))
        //        presenter
    }
}