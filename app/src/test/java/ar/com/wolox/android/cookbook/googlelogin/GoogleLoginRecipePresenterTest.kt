package ar.com.wolox.android.cookbook.googlelogin

import ar.com.wolox.android.cookbook.googlelogin.helper.GoogleHelper
import ar.com.wolox.android.cookbook.googlelogin.model.GoogleAccount
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
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
    fun `Should show user on attach when there is a last signed in account`() {
        val user = mock<GoogleAccount>()
        doReturn(user).whenever(googleHelper).getLastSignedInAccount()

        presenter.attachView(view)

        verify(view, times(1)).showUser(user)
    }

    @Test
    fun `Should not show user on attach when there is not a last signed in account`() {
        doReturn(null).whenever(googleHelper).getLastSignedInAccount()

        presenter.attachView(view)

        verify(view, never()).showUser(any())
    }

    @Test
    fun `Should show no user on google logout`() {
        presenter.attachView(view)

        presenter.onGoogleLogout()

        verify(view, times(1)).showNoUser()
    }
}