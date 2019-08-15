package ar.com.wolox.android.cookbook.twitterlogin

import android.content.Intent
import androidx.fragment.app.FragmentActivity
import ar.com.wolox.android.cookbook.R
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import com.twitter.sdk.android.core.Callback
import com.twitter.sdk.android.core.TwitterSession
import kotlinx.android.synthetic.main.fragment_twitter_login.*

class TwitterLoginRecipeFragment : WolmoFragment<TwitterLoginRecipePresenter>(), TwitterLoginRecipeView {

    override fun layout(): Int = R.layout.fragment_twitter_login

    override fun init() {
    }

    override fun setListeners() {
        vCustomLoginBtn.setOnClickListener {
            presenter.onCustomBtnRequest()
        }

        vGetProfileBtn.setOnClickListener {
            presenter.onImageRequest()
        }
    }

    override fun getActivityContext(): FragmentActivity? {
        return activity
    }

    override fun setLoginCallback(callback: Callback<TwitterSession>) {
        vDefaultLoginBtn.callback = callback
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        vDefaultLoginBtn.onActivityResult(requestCode, resultCode, data)
        presenter.onActivityResult(requestCode, requestCode, data)
    }

    override fun showAuthError(message: String) {
        vDetails.text = message
    }

    override fun showAuthFail() {
        vDetails.text = getString(R.string.twitter_auth_error)
    }
}