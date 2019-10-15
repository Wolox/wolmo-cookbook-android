package ar.com.wolox.android.cookbook.googlelogin

import android.content.Intent
import ar.com.wolox.android.cookbook.R
import ar.com.wolox.android.cookbook.googlelogin.helper.GoogleAccountHelper
import ar.com.wolox.android.cookbook.googlelogin.helper.GoogleHelper
import ar.com.wolox.android.cookbook.googlelogin.model.GoogleAccount
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import ar.com.wolox.wolmo.core.util.ToastFactory
import com.facebook.imagepipeline.request.ImageRequestBuilder
import kotlinx.android.synthetic.main.fragment_google_login.*
import javax.inject.Inject

/**
 * Before begin its necessary to configure a Google API Console project and set up your Android Studio project
 * The documentation to do this: https://developers.google.com/identity/sign-in/android/startChildrenCancellation-integrating
 *
 * Check to add to the build.gradle this:
 *  dependencies {
 *      implementation "com.google.android.gms:play-services-auth:$google_play_services_version"
 *  }
 *
 *  Advice to generate SHA1:
 *  Open Gradle projects with the gradle button on the right of the screen: Tasks -> android -> signingReport
 */
class GoogleLoginRecipeFragment : WolmoFragment<GoogleLoginRecipePresenter>(), GoogleLoginRecipeView {

    @Inject
    internal lateinit var toastFactory: ToastFactory
    @Inject
    internal lateinit var googleHelper: GoogleHelper

    override fun layout() = R.layout.fragment_google_login

    override fun init() {
        googleHelper.setGoogleLoginAction(vLoginGoogleBtn, this, GOOGLE_SIGN_IN)
        googleHelper.setGoogleLogoutAction(vLogoutGoogleBtn, this, presenter::onGoogleLogout)
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

    override fun showGoogleLoginErrorCancelled() =
            toastFactory.show(requireContext().getString(R.string.google_login_error_cancelled))

    override fun showGoogleLoginErrorFailed() =
            toastFactory.show(requireContext().getString(R.string.google_login_error_failed))

    override fun showGoogleLoginErrorInProgress() =
            toastFactory.show(requireContext().getString(R.string.google_login_error_in_progress))

    override fun showGoogleLoginErrorUnexpected() =
            toastFactory.show(requireContext().getString(R.string.google_login_error_unexpected))

    companion object {
        // Hard coded code to receive on activity result
        private const val GOOGLE_SIGN_IN = 101
    }
}