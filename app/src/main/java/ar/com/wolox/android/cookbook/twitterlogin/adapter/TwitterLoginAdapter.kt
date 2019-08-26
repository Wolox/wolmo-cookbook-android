package ar.com.wolox.android.cookbook.twitterlogin.adapter

import android.content.Intent
import androidx.fragment.app.FragmentActivity
import com.twitter.sdk.android.core.Callback
import com.twitter.sdk.android.core.Result
import com.twitter.sdk.android.core.TwitterApiClient
import com.twitter.sdk.android.core.TwitterCore
import com.twitter.sdk.android.core.TwitterException
import com.twitter.sdk.android.core.TwitterSession
import com.twitter.sdk.android.core.identity.TwitterAuthClient
import com.twitter.sdk.android.core.models.User
import retrofit2.Call
import javax.inject.Inject

class TwitterLoginAdapter @Inject constructor() {
    private val authClient: TwitterAuthClient = TwitterAuthClient()
    private val apiClient: TwitterApiClient = TwitterCore.getInstance().apiClient

    fun onResultActivity(requestCode: Int, resultCode: Int, data: Intent?) {
        authClient.onActivityResult(requestCode, resultCode, data)
    }

    fun authorizeClient(context: FragmentActivity, authListener: TwitterLoginAuthListener) {
        authClient.authorize(context, object : Callback<TwitterSession>() {
            override fun success(result: Result<TwitterSession>) {
                authListener.onAuthSuccess(result.data)
            }

            override fun failure(e: TwitterException) {
                authListener.onAuthError(e.message)
            }
        })
    }

    fun twitterCallback(authListener: TwitterLoginAuthListener): Callback<TwitterSession> {
        return object : Callback<TwitterSession>() {
            override fun success(result: Result<TwitterSession>?) {
                result?.let {
                    authListener.onAuthSuccess(result.data)
                }?:run {
                    authListener.onAuthFail()
                }
            }

            override fun failure(exception: TwitterException?) {
                exception?.let {
                    authListener.onAuthError(exception.message)
                }?:run {
                    authListener.onAuthFail()
                }
            }
        }
    }

    fun requestEmail(twitterSession: TwitterSession, emailListener: TwitterLoginEmailListener) {
        authClient.requestEmail(twitterSession, object : Callback<String>() {
            override fun success(result: Result<String>?) {
                result?.let {
                    emailListener.onEmailSuccess(result.data)
                }?:run {
                    emailListener.onEmailFailure()
                }
            }

            override fun failure(exception: TwitterException?) {
                exception?.let {
                    emailListener.onEmailError(exception.toString())
                }?:run {
                    emailListener.onEmailFailure()
                }
            }
        })
    }

    fun requestProfileImage(pictureListener: TwitterLoginPictureListener) {
        val call: Call<User> = apiClient.accountService.verifyCredentials(true, false, true)
        call.enqueue(object : Callback<User>() {
            override fun success(result: Result<User>?) {
                result?.let {
                    pictureListener.onUserSuccess(result.data)
                }?:run {
                    pictureListener.onUserFail()
                }
            }

            override fun failure(exception: TwitterException?) {
                exception?.let {
                    pictureListener.onUserError(exception.toString())
                }?:run {
                    pictureListener.onUserFail()
                }
            }
        })
    }

    fun logoutSession(credentialsListener: TwitterLoginCredentialsListener) {
        try {
            TwitterCore.getInstance().sessionManager.clearActiveSession()
            credentialsListener.onClearCredentialsSuccess()
        } catch (e: Exception) {
            e.printStackTrace()
            credentialsListener.onClearCredentialsError()
        }
    }
}