package ar.com.wolox.android.cookbook.tests

import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class TestLoginRecipePresenterTest {

    private lateinit var presenter: TestLoginRecipePresenter

    @Mock
    lateinit var loginService: TestLoginRecipeService

    @Mock
    lateinit var view: TestLoginRecipeView

    @Mock
    lateinit var userModel: TestLoginUserModel

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        presenter = TestLoginRecipePresenter(loginService)
        presenter.attachView(view)
    }

    @Test
    fun `Should go to next window when login is successful`() {
        doReturn(userModel).whenever(loginService).login(anyString(), anyString())

        presenter.onLoginButtonClick(EMAIL, PASSWORD)

        verify(view, times(1)).goToNextWindow()
    }

    @Test
    fun `Should show error when login is unsuccessful`() {
        doReturn(null).whenever(loginService).login(anyString(), anyString())

        presenter.onLoginButtonClick(EMAIL, PASSWORD)

        verify(view, times(1)).showLoginError()
    }

    companion object {
        private const val EMAIL = "email@gmail.com"
        private const val PASSWORD = "1234"
    }
}