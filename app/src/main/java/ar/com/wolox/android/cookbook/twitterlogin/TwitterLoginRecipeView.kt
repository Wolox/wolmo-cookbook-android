package ar.com.wolox.android.cookbook.twitterlogin

import androidx.fragment.app.FragmentActivity
import com.twitter.sdk.android.core.TwitterSession

interface TwitterLoginRecipeView {

    fun setLoginCallback(callback: com.twitter.sdk.android.core.Callback<TwitterSession>)

    fun getActivityContext(): FragmentActivity?

    fun showAuthError(message: String)
    fun showAuthFail()
}