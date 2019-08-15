package ar.com.wolox.android.cookbook.twitterlogin.adapter

import androidx.fragment.app.FragmentActivity
import com.twitter.sdk.android.core.Callback
import com.twitter.sdk.android.core.Result
import com.twitter.sdk.android.core.TwitterException
import com.twitter.sdk.android.core.TwitterSession
import com.twitter.sdk.android.core.identity.TwitterAuthClient
import javax.inject.Inject

class TwitterLoginAdapter @Inject constructor() {
    private val client: TwitterAuthClient = TwitterAuthClient()

    fun authorizeClient(context: FragmentActivity, listener: TwitterLoginListener) {
        client.authorize(context, object : Callback<TwitterSession>() {
            override fun success(result: Result<TwitterSession>) {
                listener.onAuthSuccess(result.data)
            }

            override fun failure(e: TwitterException) {
                listener.onAuthError(e.message)
            }
        })
    }

    fun twitterCallback(listener: TwitterLoginListener): Callback<TwitterSession> {
        return object : Callback<TwitterSession>() {
            override fun success(result: Result<TwitterSession>?) {
                if (result != null) {
                    listener.onAuthSuccess(result.data)
                } else {
                    listener.onAuthFail()
                }
            }

            override fun failure(exception: TwitterException?) {
                if (exception != null) {
                    listener.onAuthError(exception.message)
                } else {
                    listener.onAuthFail()
                }
            }
        }
    }
}