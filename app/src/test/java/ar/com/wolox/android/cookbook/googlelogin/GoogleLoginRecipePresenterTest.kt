package ar.com.wolox.android.cookbook.googlelogin

import ar.com.wolox.android.cookbook.googlelogin.helper.GoogleHelper
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.never
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class GoogleLoginRecipePresenterTest {

    private lateinit var presenter: GoogleLoginRecipePresenter

    @Mock
    lateinit var googleHelper: GoogleHelper

    @Mock
    lateinit var view: GoogleLoginRecipeView

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        presenter = GoogleLoginRecipePresenter(googleHelper)
    }

    @Test
    fun isASignedInAccount_True_ShowUser() {
        whenever(googleHelper.getLastSignedInAccount()).thenReturn(mock())
        presenter.attachView(view)

        verify(view, times(1)).showUser(any())
    }

    @Test
    fun isASignedInAccount_False_DoNotShowUser() {
        whenever(googleHelper.getLastSignedInAccount()).thenReturn(null)
        presenter.attachView(view)

        verify(view, never()).showUser(any())
    }

    @Test
    fun onGoogleLogout_ShowNoUser() {
        presenter.attachView(view)
        presenter.onGoogleLogout()
        verify(view, times(1)).showNoUser()
    }
}