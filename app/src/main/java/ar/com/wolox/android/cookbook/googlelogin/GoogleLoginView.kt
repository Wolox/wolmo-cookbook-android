package ar.com.wolox.android.cookbook.googlelogin

import ar.com.wolox.android.cookbook.googlelogin.model.GoogleAccount

interface GoogleLoginView {

    fun showUser(user: GoogleAccount)

    fun showNoUser()

    fun showGoogleLoginError()
}
