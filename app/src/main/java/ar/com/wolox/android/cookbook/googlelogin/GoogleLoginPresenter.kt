package ar.com.wolox.android.cookbook.googlelogin

import android.content.Intent
import ar.com.wolox.android.cookbook.googlelogin.model.GoogleAccount
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import javax.inject.Inject

class GoogleLoginPresenter @Inject constructor() : BasePresenter<GoogleLoginView>() {

    fun onGoogleLogin(data: Intent?) {
        // Get the signed account from the data received by intent
        GoogleHelper.getSignedInAccountFromIntent(
                data,
                view::setUser,
                view::showGoogleLoginError
        )
    }

    fun onGoogleLogged(user: GoogleAccount) = view.setUser(user)

    fun onGoogleLogout() = view.removeUser()
}