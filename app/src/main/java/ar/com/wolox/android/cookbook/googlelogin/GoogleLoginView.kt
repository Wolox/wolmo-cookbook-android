package ar.com.wolox.android.cookbook.googlelogin

import ar.com.wolox.android.cookbook.googlelogin.model.UserGoogle

interface GoogleLoginView {

    fun setUser(user: UserGoogle)

    fun removeUser()

    fun showGoogleLoginError()
}
