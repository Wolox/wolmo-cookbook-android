package ar.com.wolox.android.cookbook.twitterlogin

import android.app.Application
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import ar.com.wolox.android.cookbook.twitterlogin.adapter.TwitterLoginAdapter
import ar.com.wolox.android.cookbook.twitterlogin.adapter.TwitterLoginAuthListener
import ar.com.wolox.android.cookbook.twitterlogin.adapter.TwitterLoginCredentialsListener
import ar.com.wolox.android.cookbook.twitterlogin.adapter.TwitterLoginEmailListener
import ar.com.wolox.android.cookbook.twitterlogin.adapter.TwitterLoginPictureListener
import ar.com.wolox.android.cookbook.twitterlogin.model.TypeErrorMessage
import ar.com.wolox.android.cookbook.twitterlogin.model.YoutubeEmailResponse
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import com.twitter.sdk.android.core.TwitterCore
import com.twitter.sdk.android.core.TwitterSession
import com.twitter.sdk.android.core.models.User
import javax.inject.Inject

class TwitterLoginRecipePresenter @Inject constructor(
    private val twitterAdapter: TwitterLoginAdapter,
    private val application: Application
) : BasePresenter<TwitterLoginRecipeView>() {

    override fun onViewAttached() {
        super.onViewAttached()

        initLoginButton()
        defaultTwitterLogin()
    }

    /**
     * Attach callback to TwitterLoginButton is mandatory. When user click btn, if callback
     * isn't attached, the app throws an exception (null callback)
     **/
    private fun initLoginButton() {
        view.setLoginCallback(twitterAdapter.twitterCallback(object : TwitterLoginAuthListener {
            override fun onAuthSuccess(result: TwitterSession) {
                fetchTwitterEmail(result)
            }

            override fun onAuthError(message: String?) {
                message?.let { view.showError(it) } ?: run { view.showApiError(TypeErrorMessage.AUTH) }
            }

            override fun onAuthFail() {
                view.showApiError(TypeErrorMessage.AUTH)
            }
        }))
    }

    /**
     * Login button provided by twitter sdk
     */
    fun doTwitterLogin() {
        if (isNetworkAvailable()) {
            getTwitterSession()?.let { fetchTwitterEmail(it) } ?: run { defaultTwitterLogin() }
        } else {
            view.showNetworkUnavailableError()
        }
    }

    /**
     *Other way to login with Twitter is work directly with API and avoid te button provided by the
     * sdk. Both ways ends in the same point.
     */
    fun doTwitterLoginWithApi() {

        if (isNetworkAvailable()) {
            view.requireActivity()?.let { it ->
                getTwitterSession()?.let { twitterSession ->
                    fetchTwitterEmail(twitterSession)
                } ?: run {
                    twitterAdapter.authorizeClient(it, object : TwitterLoginAuthListener {
                        override fun onAuthSuccess(result: TwitterSession) {
                            fetchTwitterEmail(result)
                        }

                        override fun onAuthError(message: String?) {
                            message?.let { lambda -> view.showError(lambda) } ?: run { view.showApiError(TypeErrorMessage.AUTH) }
                        }

                        override fun onAuthFail() {
                            view.showApiError(TypeErrorMessage.AUTH)
                        }
                    })
                }
            }
        } else {
            view.showNetworkUnavailableError()
        }
    }

    /**
     * "com.twitter.sdk.android.core.models.User" Contains personal data from twitter, (like profile
     * picture, followers, description, creation date, profile background picture, ...), but needs
     * an user logged to works with code 200
     */
    fun doFetchData() {
        if (isNetworkAvailable()) {
            getTwitterSession()?.let {
                twitterAdapter.requestProfileImage(object : TwitterLoginPictureListener {
                    override fun onUserSuccess(user: User) {
                        view.showPictureData(user)
                    }

                    override fun onUserError(message: String) {
                        view.showError(message)
                    }

                    override fun onUserFail() {
                        view.showApiError(TypeErrorMessage.PICTURE)
                    }
                })
            } ?: run {
                view.showApiError(TypeErrorMessage.UN_AUTH)
            }
        } else {
            view.showNetworkUnavailableError()
        }
    }

    fun doLogoutOnTwitter() {
        if (isNetworkAvailable()) {
            getTwitterSession()?.let {
                twitterAdapter.logoutSession(object : TwitterLoginCredentialsListener {
                    override fun onClearCredentialsSuccess() {
                        view.showCredentialsCleared()
                        view.toggleLoginButtonState(true)
                    }

                    override fun onClearCredentialsError() {
                        view.showApiError(TypeErrorMessage.CREDENTIALS)
                    }
                })
            }
        } else {
            view.showNetworkUnavailableError()
        }
    }

    fun onActivityResultFinished(requestCode: Int, resultCode: Int, data: Intent?) {
        twitterAdapter.onResultActivity(requestCode, resultCode, data)
    }

    /**
     * TwitterSession and TwitterAuthToken classes contains all data stored in device from login,
     * like UserID, Username, token, secret, ...
     * Example: TwitterAuthToken(session.authToken.token, session.authToken.secret)
     */
    private fun getTwitterSession(): TwitterSession? = TwitterCore.getInstance().sessionManager.activeSession

    private fun defaultTwitterLogin() = getTwitterSession()?.let { fetchTwitterEmail(it) }

    private fun fetchTwitterEmail(twitterSession: TwitterSession?) {
        twitterSession?.let {
            twitterAdapter.requestEmail(it, object : TwitterLoginEmailListener {
                override fun onEmailSuccess(response: String) {
                    val emailResponse = YoutubeEmailResponse(it.userId.toString(),
                            response,
                            it.userName)
                    view.showLoginData(emailResponse)
                    view.toggleLoginButtonState(false)
                }

                override fun onEmailError(message: String) {
                    view.showError(message)
                }

                override fun onEmailFailure() {
                    view.showApiError(TypeErrorMessage.EMAIL)
                }
            })
        } ?: run {
            view.showApiError(TypeErrorMessage.INTERNAL)
        }
    }

    private fun isNetworkAvailable(): Boolean {

        val connectivityManager = application.applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE)
        return if (connectivityManager is ConnectivityManager) {
            val networkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo
            networkInfo?.isConnected ?: false
        } else false
    }
}