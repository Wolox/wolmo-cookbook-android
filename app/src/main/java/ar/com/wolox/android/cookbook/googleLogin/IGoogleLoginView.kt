package ar.com.wolox.android.cookbook.googleLogin

import ar.com.wolox.android.cookbook.googleLogin.model.UserGoogle

interface IGoogleLoginView {

    fun setUser(user: UserGoogle)

    fun removeUser()

    fun showGoogleLoginError()
}
