package ar.com.wolox.android.cookbook.googlelogin

import android.content.Intent
import ar.com.wolox.android.cookbook.googlelogin.model.UserGoogle
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import javax.inject.Inject

class GoogleLoginPresenter @Inject constructor() : BasePresenter<IGoogleLoginView>() {

    fun onGoogleLogin(data: Intent?) {
        // Get the signed account from the data received by intent
        GoogleHelper.getSignedInAccountFromIntent(
                data,
                view::setUser,
                view::showGoogleLoginError
        )
    }

    fun onGoogleLogged(user: UserGoogle) = view.setUser(user)

    fun onGoogleLogout() = view.removeUser()
}