package ar.com.wolox.android.cookbook.facebooklogin

import android.content.Intent
import ar.com.wolox.android.cookbook.R
import ar.com.wolox.android.cookbook.facebooklogin.helper.FacebookHelper
import ar.com.wolox.android.cookbook.facebooklogin.model.FacebookAccount
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import ar.com.wolox.wolmo.core.util.ToastFactory
import com.facebook.imagepipeline.request.ImageRequestBuilder
import kotlinx.android.synthetic.main.fragment_facebook_login.*
import javax.inject.Inject

/**
 * Follow the step by step from Facebook Developers docs: https://developers.facebook.com/docs/facebook-login/android
 */
class FacebookLoginRecipeFragment : WolmoFragment<FacebookLoginRecipePresenter>(), FacebookLoginRecipeView {

    @Inject
    internal lateinit var toastFactory: ToastFactory
    @Inject
    internal lateinit var facebookHelper: FacebookHelper

    override fun layout(): Int = R.layout.fragment_facebook_login

    override fun init() {
        facebookHelper.setFacebookOriginalButtonAction(vLoginFacebookOriginalLoginBtn, this, presenter, presenter)
        facebookHelper.setFacebookLoginAction(vLoginFacebookLoginBtn, this, presenter)
        facebookHelper.setFacebookLogoutAction(vLoginFacebookLogoutBtn, presenter)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        facebookHelper.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun showUser(user: FacebookAccount) {
        vLoginFacebookUserName.text = user.name
        vLoginFacebookUserEmail.text = user.email
        user.picture?.let {
            vLoginFacebookUserPhoto.setImageRequest(ImageRequestBuilder.newBuilderWithSource(it).build())
        }

        vLoginFacebookLogoutBtn.isEnabled = true
        vLoginFacebookLoginBtn.isEnabled = false
    }

    override fun showNoUser() {
        vLoginFacebookUserName.text = ""
        vLoginFacebookUserEmail.text = ""
        vLoginFacebookUserPhoto.setImageRequest(null)

        vLoginFacebookLogoutBtn.isEnabled = false
        vLoginFacebookLoginBtn.isEnabled = true
    }

    override fun showLoginCancel() = toastFactory.show(R.string.facebook_login_error_cancelled)

    override fun showLoginError() = toastFactory.show(R.string.facebook_login_error_unexpected)
}