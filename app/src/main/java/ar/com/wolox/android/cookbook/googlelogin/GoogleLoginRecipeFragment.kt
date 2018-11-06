package ar.com.wolox.android.cookbook.googlelogin

import android.content.Intent
import ar.com.wolox.android.cookbook.R
import ar.com.wolox.android.cookbook.googlelogin.helper.GoogleAccountHelper
import ar.com.wolox.android.cookbook.googlelogin.helper.setGoogleLoginAction
import ar.com.wolox.android.cookbook.googlelogin.helper.setGoogleLogoutAction
import ar.com.wolox.android.cookbook.googlelogin.model.GoogleAccount
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import ar.com.wolox.wolmo.core.util.ToastFactory
import com.facebook.imagepipeline.request.ImageRequestBuilder
import kotlinx.android.synthetic.main.fragment_login.*
import javax.inject.Inject

/**
 * Before begin its necessary to configure a Google API Console project and set up your Android Studio project
 * The documentation to do this: https://developers.google.com/identity/sign-in/android/start-integrating
 *
 * Check to add to the build.gradle this:
 *  dependencies {
 *      implementation "com.google.firebase:firebase-core:$google_firebase_version"
 *      implementation "com.google.android.gms:play-services-auth:$google_play_services_version"
 *  }
 *
 *  apply plugin: 'com.google.gms.google-services'
 *
 *  Its also important to add a project and an android app here: https://console.firebase.google.com/u/0/
 *  This will generate a 'google-services.json' that is important to copy in app level
 *
 *  If there's a problem with connection to Firebase its possible to connect inside Android Studio with:
 *  Tools > Firebase > Realtime Database > Save and retrieve data > Connect to Firebase
 *
 *  Advice to generate SHA1:
 *  Open Gradle projects with the gradle button on the right of the screen: Tasks -> android -> signingReport
 */
class GoogleLoginRecipeFragment : WolmoFragment<GoogleLoginRecipePresenter>(), GoogleLoginRecipeView {

    @Inject
    internal lateinit var toastFactory: ToastFactory

    override fun layout(): Int = R.layout.fragment_login

    override fun init() {
        val loggedAccount = vLoginGoogleBtn.setGoogleLoginAction(this, GOOGLE_SIGN_IN)
        if (loggedAccount != null) presenter.onGoogleLogged(loggedAccount)

        vLogoutGoogleBtn.setGoogleLogoutAction(this, presenter::onGoogleLogout)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == GOOGLE_SIGN_IN) presenter.onGoogleLogin(GoogleAccountHelper(data))
    }

    override fun showUser(user: GoogleAccount) {
        vLoginUserName.text = user.displayName
        vLoginUserEmail.text = user.email

        if (user.picture != null) {
            vLoginUserPhoto.setImageRequest(ImageRequestBuilder.newBuilderWithSource(user.picture).build())
        }

        vLogoutGoogleBtn.isEnabled = true
        vLoginGoogleBtn.isEnabled = false
    }

    override fun showNoUser() {
        vLoginUserName.text = ""
        vLoginUserEmail.text = ""
        vLoginUserPhoto.setImageRequest(null)

        vLogoutGoogleBtn.isEnabled = false
        vLoginGoogleBtn.isEnabled = true
    }

    override fun showGoogleLoginError() = toastFactory.show(R.string.google_login_error)

    companion object {
        // Hard coded code to receive on activity result
        private const val GOOGLE_SIGN_IN = 101
    }
}