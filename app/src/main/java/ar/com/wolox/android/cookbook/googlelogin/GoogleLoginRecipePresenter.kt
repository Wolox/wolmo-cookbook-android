package ar.com.wolox.android.cookbook.googlelogin

import ar.com.wolox.android.cookbook.googlelogin.helper.GoogleAccountHelper
import ar.com.wolox.android.cookbook.googlelogin.model.GoogleAccount
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import javax.inject.Inject

class GoogleLoginRecipePresenter @Inject constructor() : BasePresenter<GoogleLoginRecipeView>() {

    fun onGoogleLogin(googleAccountHelper: GoogleAccountHelper) =
            googleAccountHelper.getAccount(view::showUser, view::showGoogleLoginError)

    fun onGoogleLogged(user: GoogleAccount) = view.showUser(user)

    fun onGoogleLogout() = view.showNoUser()
}