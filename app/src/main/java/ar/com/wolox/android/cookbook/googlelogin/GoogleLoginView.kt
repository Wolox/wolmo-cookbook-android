package ar.com.wolox.android.cookbook.googlelogin

import ar.com.wolox.android.cookbook.googlelogin.model.GoogleAccount

interface GoogleLoginView {

    fun setUser(user: GoogleAccount)

    fun removeUser()

    fun showGoogleLoginError()
}
