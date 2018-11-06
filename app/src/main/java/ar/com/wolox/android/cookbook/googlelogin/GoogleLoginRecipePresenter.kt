package ar.com.wolox.android.cookbook.googlelogin

import ar.com.wolox.android.cookbook.googlelogin.helper.GoogleAccountHelper
import ar.com.wolox.android.cookbook.googlelogin.helper.GoogleHelper
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import javax.inject.Inject

class GoogleLoginRecipePresenter @Inject constructor(val googleHelper: GoogleHelper)
    : BasePresenter<GoogleLoginRecipeView>() {

    override fun onViewAttached() {
        super.onViewAttached()

        val signedInAccount = googleHelper.getLastSignedInAccount()
        if (signedInAccount != null) view.showUser(signedInAccount)
    }

    fun onGoogleLogin(googleAccountHelper: GoogleAccountHelper) =
            googleAccountHelper.getAccount(view::showUser, view::showGoogleLoginError)

    fun onGoogleLogout() = view.showNoUser()
}