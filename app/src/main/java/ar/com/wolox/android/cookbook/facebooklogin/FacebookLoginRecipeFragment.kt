package ar.com.wolox.android.cookbook.facebooklogin

import android.content.Intent
import ar.com.wolox.android.cookbook.R
import ar.com.wolox.android.cookbook.databinding.FragmentFacebookLoginBinding
import ar.com.wolox.android.cookbook.facebooklogin.proxy.FacebookProxy
import ar.com.wolox.android.cookbook.facebooklogin.model.FacebookAccount
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import ar.com.wolox.wolmo.core.util.ToastFactory
import com.facebook.imagepipeline.request.ImageRequestBuilder
import kotlinx.android.synthetic.main.fragment_facebook_login.vLoginFacebookLoginBtn
import kotlinx.android.synthetic.main.fragment_facebook_login.vLoginFacebookLogoutBtn
import kotlinx.android.synthetic.main.fragment_facebook_login.vLoginFacebookOriginalLoginBtn
import kotlinx.android.synthetic.main.fragment_facebook_login.vLoginFacebookUserEmail
import kotlinx.android.synthetic.main.fragment_facebook_login.vLoginFacebookUserName
import kotlinx.android.synthetic.main.fragment_facebook_login.vLoginFacebookUserPhoto
import javax.inject.Inject

/**
 * Follow the step by step from Facebook Developers docs: https://developers.facebook.com/docs/facebook-login/android
 */
class FacebookLoginRecipeFragment :
    WolmoFragment<FragmentFacebookLoginBinding, FacebookLoginRecipePresenter>(),
    FacebookLoginRecipeView {

    @Inject
    internal lateinit var toastFactory: ToastFactory

    @Inject
    internal lateinit var facebookProxy: FacebookProxy

    override fun layout(): Int = R.layout.fragment_facebook_login

    override fun init() {
        facebookProxy.setFacebookOriginalButtonAction(
            vLoginFacebookOriginalLoginBtn,
            this,
            presenter,
            presenter
        )
        facebookProxy.setFacebookLoginAction(vLoginFacebookLoginBtn, this, presenter)
        facebookProxy.setFacebookLogoutAction(vLoginFacebookLogoutBtn, presenter)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        facebookProxy.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun showUser(user: FacebookAccount) {
        vLoginFacebookUserName.text = user.name
        vLoginFacebookUserEmail.text = user.email
        user.picture?.let {
            vLoginFacebookUserPhoto.setImageRequest(
                ImageRequestBuilder.newBuilderWithSource(it).build()
            )
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

    override fun disableLogin() {
        vLoginFacebookLoginBtn.isEnabled = false
    }
}
