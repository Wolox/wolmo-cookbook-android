package ar.com.wolox.android.cookbook.twitterlogin

import android.content.Intent
import android.util.Log
import ar.com.wolox.android.cookbook.twitterlogin.adapter.TwitterLoginAdapter
import ar.com.wolox.android.cookbook.twitterlogin.adapter.TwitterLoginListener
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import com.twitter.sdk.android.core.TwitterAuthToken
import com.twitter.sdk.android.core.TwitterCore
import com.twitter.sdk.android.core.TwitterSession
import com.twitter.sdk.android.core.identity.TwitterAuthClient
import javax.inject.Inject

class TwitterLoginRecipePresenter @Inject constructor(
    private val twitterAdapter: TwitterLoginAdapter
) : BasePresenter<TwitterLoginRecipeView>() {

    private val client: TwitterAuthClient = TwitterAuthClient()
    private lateinit var authToken: TwitterAuthToken

    override fun onViewAttached() {
        super.onViewAttached()
        defaultTwitterLogin()
    }

    fun onCustomBtnRequest() {
        val context = view.getActivityContext()
        if (context != null) {
            twitterAdapter.authorizeClient(context, object : TwitterLoginListener {
                override fun onAuthSuccess(result: TwitterSession) {
                    fetchTwitterEmail(result)
                }

                override fun onAuthError(message: String?) {
                    if (message != null) {
                        view.showAuthError(message)
                    } else {
                        view.showAuthFail()
                    }
                }

                override fun onAuthFail() {
                    view.showAuthFail()
                }
            })
        }
    }

    fun onImageRequest() {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        client.onActivityResult(requestCode, resultCode, data)
    }

    private fun getTwitterSession(): TwitterSession? {
        val session: TwitterSession? = TwitterCore.getInstance().sessionManager.activeSession

        if (session != null) {
            authToken = TwitterAuthToken(session.authToken.token, session.authToken.secret)
        }

        return session
    }

    private fun defaultTwitterLogin() {
        if (getTwitterSession() == null) {
            view.setLoginCallback(twitterAdapter.twitterCallback(object : TwitterLoginListener {
                override fun onAuthSuccess(result: TwitterSession) {
                    fetchTwitterEmail(result)
                }

                override fun onAuthError(message: String?) {
                    if (message != null) {
                        view.showAuthError(message)
                    } else {
                        view.showAuthFail()
                    }
                }

                override fun onAuthFail() {
                    view.showAuthFail()
                }
            }))
        } else {
            fetchTwitterEmail(getTwitterSession())
        }
    }

    private fun fetchTwitterEmail(twitterSession: TwitterSession?) {
        Log.e("FedeLog", "fetchTwitterEmail().......................")
    }
}