package ar.com.wolox.android.cookbook.googlelogin

import ar.com.wolox.android.cookbook.googlelogin.model.UserGoogle

interface IGoogleLoginView {

    fun setUser(user: UserGoogle)

    fun removeUser()

    fun showGoogleLoginError()
}
