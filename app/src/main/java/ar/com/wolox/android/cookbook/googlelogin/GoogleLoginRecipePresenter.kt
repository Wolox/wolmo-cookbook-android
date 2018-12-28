package ar.com.wolox.android.cookbook.googlelogin

import ar.com.wolox.android.cookbook.R
import ar.com.wolox.android.cookbook.googlelogin.helper.GoogleAccountHelper
import ar.com.wolox.android.cookbook.googlelogin.helper.GoogleHelper
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import com.google.android.gms.auth.api.signin.GoogleSignInStatusCodes
import javax.inject.Inject

class GoogleLoginRecipePresenter @Inject constructor(val googleHelper: GoogleHelper)
    : BasePresenter<GoogleLoginRecipeView>() {

    override fun onViewAttached() {
        super.onViewAttached()

        val signedInAccount = googleHelper.getLastSignedInAccount()
        if (signedInAccount != null) view.showUser(signedInAccount)
    }

    fun onGoogleLogin(googleAccountHelper: GoogleAccountHelper) =
            googleAccountHelper.getAccount(view::showUser, ::handleError)



    private fun handleError(errorCode: Int?) =
            view.showGoogleLoginError(when (errorCode) {
                GoogleSignInStatusCodes.SIGN_IN_CANCELLED -> R.string.google_login_error_cancelled
                GoogleSignInStatusCodes.SIGN_IN_CURRENTLY_IN_PROGRESS -> R.string.google_login_error_in_progress
                GoogleSignInStatusCodes.SIGN_IN_FAILED -> R.string.google_login_error_failed
                else -> R.string.google_login_error_unexpected
            })

    fun onGoogleLogout() = view.showNoUser()
}