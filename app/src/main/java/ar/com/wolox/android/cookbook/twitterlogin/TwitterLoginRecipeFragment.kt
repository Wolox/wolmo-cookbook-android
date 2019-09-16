package ar.com.wolox.android.cookbook.twitterlogin

import android.content.Intent
import android.net.Uri
import android.view.View
import android.widget.Toast
import ar.com.wolox.android.cookbook.R
import ar.com.wolox.android.cookbook.twitterlogin.model.TypeErrorMessage
import ar.com.wolox.android.cookbook.twitterlogin.model.YoutubeEmailResponse
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import com.twitter.sdk.android.core.Callback
import com.twitter.sdk.android.core.TwitterSession
import com.twitter.sdk.android.core.models.User
import kotlinx.android.synthetic.main.fragment_twitter_login.*

class TwitterLoginRecipeFragment : WolmoFragment<TwitterLoginRecipePresenter>(), TwitterLoginRecipeView {

    override fun layout(): Int = R.layout.fragment_twitter_login

    override fun init() {
    }

    override fun setListeners() {
        vTwitterLoginBtn.setOnClickListener {
            presenter.onTwitterLoginButtonClicked()
        }

        vApiTwitterLoginBtn.setOnClickListener {
            presenter.onLoginWithTwitterApiButtonClicked()
        }

        vGetProfileBtn.setOnClickListener {
            presenter.onFetchDataButtonClicked()
        }

        vLogoutBtn.setOnClickListener {
            presenter.onLogoutButtonClicked()
        }
    }

    override fun showNetworkUnavailableError() {
        Toast.makeText(context, getString(R.string.twitter_error_network_unavailable), Toast.LENGTH_LONG).show()
    }

    override fun setLoginCallback(callback: Callback<TwitterSession>) {
        vTwitterLoginBtn.callback = callback
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        vTwitterLoginBtn.onActivityResult(requestCode, resultCode, data)
        presenter.onActivityResultFinished(requestCode, requestCode, data)
    }

    override fun showError(message: String) {
        vDetails.text = message
    }

    override fun showApiError(type: TypeErrorMessage) {
        when (type) {
            TypeErrorMessage.AUTH -> { vDetails.text = getString(R.string.twitter_auth_error) }
            TypeErrorMessage.EMAIL -> { vDetails.text = getString(R.string.twitter_email_error) }
            TypeErrorMessage.PICTURE -> { vDetails.text = getString(R.string.twitter_picture_error) }
            TypeErrorMessage.CREDENTIALS -> { vDetails.text = getString(R.string.twitter_credentials_error) }
            TypeErrorMessage.UN_AUTH -> { vDetails.text = getString(R.string.twitter_un_auth_error) }
            else -> { vDetails.text = getString(R.string.twitter_internal_error) }
        }
    }

    override fun toggleLoginButtonState(status: Boolean) {
        vTwitterLoginBtn.isClickable = status
    }

    override fun showLoginData(response: YoutubeEmailResponse) {
        val message = getString(R.string.twitter_login_data,
                response.userId,
                response.username,
                response.email)
        vDetails.text = message
    }

    override fun showPictureData(user: User) {
        val message = getString(R.string.twitter_picture_data,
                user.id,
                user.screenName,
                user.email,
                user.description)
        vDetails.text = message

        val uri = Uri.parse(user.profileImageUrl)
        vProfileImg.visibility = View.VISIBLE
        vProfileImg.setImageURI(uri)
    }

    override fun showCredentialsCleared() {
        vDetails.text = getString(R.string.twitter_logout)
        vProfileImg.visibility = View.INVISIBLE
    }
}