package ar.com.wolox.android.cookbook.facebooklogin

import android.content.Intent
import ar.com.wolox.android.cookbook.R
import ar.com.wolox.android.cookbook.facebooklogin.helper.FacebookAccountHelper
import ar.com.wolox.android.cookbook.facebooklogin.helper.FacebookHelper
import ar.com.wolox.android.cookbook.facebooklogin.model.FacebookAccount
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import ar.com.wolox.wolmo.core.util.ToastFactory
import com.facebook.imagepipeline.request.ImageRequestBuilder
import kotlinx.android.synthetic.main.fragment_login.*
import javax.inject.Inject

/**
 * Before begin its necessary to configure a Facebook API Console project and set up your Android Studio project
 * The documentation to do this: https://developers.facebook.com/identity/sign-in/android/start-integrating
 *
 * Check to add to the build.gradle this:
 *  dependencies {
 *      implementation "com.facebook.firebase:firebase-core:$facebook_firebase_version"
 *      implementation "com.facebook.android.gms:play-services-auth:$facebook_play_services_version"
 *  }
 *
 *  apply plugin: 'com.facebook.gms.facebook-services'
 *
 *  Its also important to add a project and an android app here: https://console.firebase.facebook.com/u/0/
 *  This will generate a 'facebook-services.json' that is important to copy in app level
 *
 *  If there's a problem with connection to Firebase its possible to connect inside Android Studio with:
 *  Tools > Firebase > Realtime Database > Save and retrieve data > Connect to Firebase
 *
 *  Advice to generate SHA1:
 *  Open Gradle projects with the gradle button on the right of the screen: Tasks -> android -> signingReport
 */
class FacebookLoginRecipeFragment : WolmoFragment<FacebookLoginRecipePresenter>(), FacebookLoginRecipeView {

    @Inject
    internal lateinit var toastFactory: ToastFactory

    override fun layout(): Int = R.layout.fragment_login

    override fun init() {
        FacebookHelper.setFacebookLoginAction(vLoginFacebookBtn, this, GOOGLE_SIGN_IN)
        FacebookHelper.setFacebookLogoutAction(vLogoutFacebookBtn, this, presenter::onFacebookLogout)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == GOOGLE_SIGN_IN) presenter.onFacebookLogin(FacebookAccountHelper(data))
    }

    override fun showUser(user: FacebookAccount) {
        vLoginUserName.text = user.displayName
        vLoginUserEmail.text = user.email

        if (user.picture != null) {
            vLoginUserPhoto.setImageRequest(ImageRequestBuilder.newBuilderWithSource(user.picture).build())
        }

        vLogoutFacebookBtn.isEnabled = true
        vLoginFacebookBtn.isEnabled = false
    }

    override fun showNoUser() {
        vLoginUserName.text = ""
        vLoginUserEmail.text = ""
        vLoginUserPhoto.setImageRequest(null)

        vLogoutFacebookBtn.isEnabled = false
        vLoginFacebookBtn.isEnabled = true
    }

    override fun showFacebookLoginError() = toastFactory.show(R.string.facebook_login_error)

    companion object {
        // Hard coded code to receive on activity result
        private const val GOOGLE_SIGN_IN = 101
    }
}