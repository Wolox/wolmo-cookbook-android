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
import java.lang.Exception
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
                if (result != null) {
                    authListener.onAuthSuccess(result.data)
                } else {
                    authListener.onAuthFail()
                }
            }

            override fun failure(exception: TwitterException?) {
                if (exception != null) {
                    authListener.onAuthError(exception.message)
                } else {
                    authListener.onAuthFail()
                }
            }
        }
    }

    fun requestEmail(twitterSession: TwitterSession, emailListener: TwitterLoginEmailListener) {
        authClient.requestEmail(twitterSession, object : Callback<String>() {
            override fun success(result: Result<String>?) {
                if (result?.data != null) {
                    emailListener.onEmailSuccess(result.data)
                } else {
                    emailListener.onEmailFailure()
                }
            }

            override fun failure(exception: TwitterException?) {
                if (exception?.message != null) {
                    emailListener.onEmailError(exception.toString())
                } else {
                    emailListener.onEmailFailure()
                }
            }
        })
    }

    fun requestProfileImage(pictureListener: TwitterLoginPictureListener) {
        val call: Call<User> = apiClient.accountService.verifyCredentials(true, false, true)
        call.enqueue(object : Callback<User>() {
            override fun success(result: Result<User>?) {
                if (result?.data != null) {
                    pictureListener.onUserSuccess(result.data)
                } else {
                    pictureListener.onUserFail()
                }
            }

            override fun failure(exception: TwitterException?) {
                if (exception?.message != null) {
                    pictureListener.onUserError(exception.toString())
                } else {
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