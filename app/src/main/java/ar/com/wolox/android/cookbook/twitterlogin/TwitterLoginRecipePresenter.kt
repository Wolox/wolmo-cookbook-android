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
                message?.let { view.showError(it) } ?: run { view.showAuthFail() }
            }

            override fun onAuthFail() {
                view.showAuthFail()
            }
        }))
    }

    fun onDefaultButtonClicked() {
        if (isNetworkAvailable()) {
            val session = getTwitterSession()
            if (session == null) {
                defaultTwitterLogin()
            } else {
                fetchTwitterEmail(session)
            }
        } else {
            view.showNetworkUnavailableError()
        }
    }

    /**
     * Custom login button don't use the interface provided by twitter (default login button),
     * instead, it use API directly.
     */
    fun onCustomButtonClicked() {

        if (isNetworkAvailable()) {
            view.requireActivity()?.let {
                val session = getTwitterSession()
                if (session == null) {
                    twitterAdapter.authorizeClient(it, object : TwitterLoginAuthListener {
                        override fun onAuthSuccess(result: TwitterSession) {
                            fetchTwitterEmail(result)
                        }

                        override fun onAuthError(message: String?) {
                            if (message != null) {
                                view.showError(message)
                            } else {
                                view.showAuthFail()
                            }
                        }

                        override fun onAuthFail() {
                            view.showAuthFail()
                        }
                    })
                } else {
                    fetchTwitterEmail(session)
                }
            }
        } else {
            view.showNetworkUnavailableError()
        }
    }

    /**
     * "com.twitter.sdk.android.core.models.User" Contains personal data from twitter, needs an
     * user logged to works with code 200
     */
    fun onFetchDataButtonClicked() {
        if (isNetworkAvailable()) {
            // USER contains all personal data from logged user, like profile picture, followers,
            // description, creation date, profile background picture, etc...
            val session = getTwitterSession()
            if (session != null) {
                twitterAdapter.requestProfileImage(object : TwitterLoginPictureListener {
                    override fun onUserSuccess(user: User) {
                        view.showPictureData(user)
                    }

                    override fun onUserError(message: String) {
                        view.showError(message)
                    }

                    override fun onUserFail() {
                        view.showPictureFail()
                    }
                })
            } else {
                view.showUnAuthError()
            }
        } else {
            view.showNetworkUnavailableError()
        }
    }

    fun onLogoutButtonClicked() {
        if (isNetworkAvailable()) {
            val session = getTwitterSession()
            if (session != null) {
                twitterAdapter.logoutSession(object : TwitterLoginCredentialsListener {
                    override fun onClearCredentialsSuccess() {
                        view.showCredentialsCleared()
                        view.toggleLoginButtonState(true)
                    }

                    override fun onClearCredentialsError() {
                        view.showCredentialsFail()
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
        if (twitterSession != null) {
            twitterAdapter.requestEmail(twitterSession, object : TwitterLoginEmailListener {
                override fun onEmailSuccess(response: String) {
                    val emailResponse = YoutubeEmailResponse(twitterSession.userId.toString(),
                            response,
                            twitterSession.userName)
                    view.showLoginData(emailResponse)
                    view.toggleLoginButtonState(false)
                }

                override fun onEmailError(message: String) {
                    view.showError(message)
                }

                override fun onEmailFailure() {
                    view.showEmailFail()
                }
            })
        } else {
            view.showInternalError()
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