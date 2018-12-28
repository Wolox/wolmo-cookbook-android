package ar.com.wolox.android.cookbook.googlelogin

import ar.com.wolox.android.cookbook.googlelogin.helper.GoogleAccountHelper
import ar.com.wolox.android.cookbook.googlelogin.helper.GoogleHelper
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import com.google.android.gms.auth.api.signin.GoogleSignInStatusCodes
import javax.inject.Inject

class GoogleLoginRecipePresenter @Inject constructor(private val googleHelper: GoogleHelper)
    : BasePresenter<GoogleLoginRecipeView>() {

    override fun onViewAttached() {
        super.onViewAttached()

        val signedInAccount = googleHelper.getLastSignedInAccount()
        if (signedInAccount != null) view.showUser(signedInAccount)
    }

    fun onGoogleLogin(googleAccountHelper: GoogleAccountHelper) =
            googleAccountHelper.getAccount(view::showUser, ::handleError)

    private fun handleError(errorCode: Int?) =
            when (errorCode) {
                GoogleSignInStatusCodes.SIGN_IN_CANCELLED -> view.showGoogleLoginErrorCancelled()
                GoogleSignInStatusCodes.SIGN_IN_CURRENTLY_IN_PROGRESS -> view.showGoogleLoginErrorInProgress()
                GoogleSignInStatusCodes.SIGN_IN_FAILED -> view.showGoogleLoginErrorFailed()
                else -> view.showGoogleLoginErrorUnexpected()
            }

    fun onGoogleLogout() = view.showNoUser()
}