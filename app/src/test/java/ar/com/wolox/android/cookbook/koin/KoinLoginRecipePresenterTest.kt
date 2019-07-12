package ar.com.wolox.android.cookbook.koin

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class KoinLoginRecipePresenterTest {

    private lateinit var presenter: KoinLoginRecipePresenter

    @Mock
    lateinit var loginService: KoinLoginRecipeService

    @Mock
    lateinit var view: KoinLoginRecipeView

    @Before
    fun setupWolmoPresenterTest() {
        MockitoAnnotations.initMocks(this)
        presenter = KoinLoginRecipePresenter(view, loginService)
    }

    @Test
    fun `Should show empty email error when email is empty`() {
        presenter.onLoginButtonClick("", "")

        verify(view, times(1)).showEmptyEmailError()
    }

    @Test
    fun `Should show empty password error when password is empty`() {
        presenter.onLoginButtonClick(EMAIL, "")

        verify(view, times(1)).showEmptyPasswordError()
    }

    @Test
    fun `Should show invalid email error when email is bad formatted`() {
        presenter.onLoginButtonClick(INVALID_EMAIL, PASSWORD)

        verify(view, times(1)).showInvalidEmailError()
    }

    @Test
    fun `Should go to next window when login is successful`() {
        val onSuccessParameterPosition = 2
        doAnswer { invocation ->
            invocation.getArgument<(KoinLoginUserModel) -> Unit>(onSuccessParameterPosition)(mock())
        }.whenever(loginService).login(anyString(), anyString(), any(), any())

        presenter.onLoginButtonClick(EMAIL, PASSWORD)

        verify(view, times(1)).goToNextWindow()
    }

    @Test
    fun `Should show error when login is unsuccessful`() {
        val onErrorParameterPosition = 3
        doAnswer { invocation ->
            invocation.getArgument<() -> Unit>(onErrorParameterPosition)()
        }.whenever(loginService).login(anyString(), anyString(), any(), any())

        presenter.onLoginButtonClick(EMAIL, PASSWORD)

        verify(view, times(1)).showLoginError()
    }

    companion object {
        private const val INVALID_EMAIL = "email"
        private const val EMAIL = "email@gmail.com"
        private const val PASSWORD = "1234"
    }
}