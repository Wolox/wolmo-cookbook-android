package ar.com.wolox.android.cookbook.tests

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock

class TestLoginRecipePresenterTest : WolmoPresenterTest<TestLoginRecipeView, TestLoginRecipePresenter>() {

    @Mock
    lateinit var loginService: TestLoginRecipeService

    override fun getPresenterInstance() = TestLoginRecipePresenter(loginService)

    @Test
    fun `Should go to next window when login is successful`() {
        val onSuccessParameterPosition = 2
        doAnswer { invocation ->
            invocation.getArgument<(TestLoginUserModel) -> Unit>(onSuccessParameterPosition)(mock())
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
        private const val EMAIL = "email@gmail.com"
        private const val PASSWORD = "1234"
    }
}