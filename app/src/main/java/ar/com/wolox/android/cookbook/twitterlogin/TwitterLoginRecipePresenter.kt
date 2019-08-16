package ar.com.wolox.android.cookbook.twitterlogin

import android.content.Intent
import ar.com.wolox.android.cookbook.twitterlogin.adapter.TwitterLoginAdapter
import ar.com.wolox.android.cookbook.twitterlogin.adapter.TwitterLoginAuthListener
import ar.com.wolox.android.cookbook.twitterlogin.adapter.TwitterLoginEmailListener
import ar.com.wolox.android.cookbook.twitterlogin.adapter.TwitterLoginPictureListener
import ar.com.wolox.android.cookbook.twitterlogin.model.YoutubeEmailResponse
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import com.twitter.sdk.android.core.TwitterCore
import com.twitter.sdk.android.core.TwitterSession
import com.twitter.sdk.android.core.models.User
import javax.inject.Inject

class TwitterLoginRecipePresenter @Inject constructor(
    private val twitterAdapter: TwitterLoginAdapter
) : BasePresenter<TwitterLoginRecipeView>() {

    override fun onViewAttached() {
        super.onViewAttached()
        defaultTwitterLogin()
    }

    fun onDefaultBtnRequest() {
        val session = getTwitterSession()
        if (session == null) {
            defaultTwitterLogin()
        } else {
            fetchTwitterEmail(session)
        }
    }

    fun onCustomBtnRequest() {
        val context = view.getActivityContext()
        if (context != null) {
            val session = getTwitterSession()
            if (session == null) {
                twitterAdapter.authorizeClient(context, object : TwitterLoginAuthListener {
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
    }

    fun onImageRequest() {

        //USER contains all personal data from logged user, like profile picture, followers,
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
    }

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        twitterAdapter.onResultActivity(requestCode, resultCode, data)
    }

    private fun getTwitterSession(): TwitterSession? {

//        val session = TwitterCore.getInstance().sessionManager.activeSession
//        if (session != null) {
//            // TwitterSession and TwitterAuthToken contains all data stored in device from login
//            // (UserID, Username, token, secret)
//            val authToken = TwitterAuthToken(session.authToken.token, session.authToken.secret)
//        }

        return TwitterCore.getInstance().sessionManager.activeSession
    }

    private fun defaultTwitterLogin() {
        if (getTwitterSession() == null) {
            view.setLoginCallback(twitterAdapter.twitterCallback(object : TwitterLoginAuthListener {
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
            }))
        } else {
            fetchTwitterEmail(getTwitterSession())
        }
    }

    private fun fetchTwitterEmail(twitterSession: TwitterSession?) {
        if (twitterSession != null) {
            twitterAdapter.requestEmail(twitterSession, object : TwitterLoginEmailListener {
                override fun onEmailSuccess(response: String) {
                    val emailResponse = YoutubeEmailResponse(twitterSession.userId.toString(),
                            response,
                            twitterSession.userName)
                    view.showLoginData(emailResponse)
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
}