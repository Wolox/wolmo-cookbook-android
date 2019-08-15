package ar.com.wolox.android.cookbook.twitterlogin

import androidx.fragment.app.FragmentActivity
import ar.com.wolox.android.cookbook.twitterlogin.model.YoutubeEmailResponse
import com.twitter.sdk.android.core.TwitterSession
import com.twitter.sdk.android.core.models.User

interface TwitterLoginRecipeView {

    fun setLoginCallback(callback: com.twitter.sdk.android.core.Callback<TwitterSession>)

    fun getActivityContext(): FragmentActivity?

    fun showLoginData(response: YoutubeEmailResponse)
    fun showPictureData(user: User)

    fun showError(message: String)
    fun showAuthFail()
    fun showEmailFail()
    fun showPictureFail()
}