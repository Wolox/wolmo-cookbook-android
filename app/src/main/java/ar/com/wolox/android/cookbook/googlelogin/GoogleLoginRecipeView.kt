package ar.com.wolox.android.cookbook.googlelogin

import ar.com.wolox.android.cookbook.googlelogin.model.GoogleAccount

interface GoogleLoginRecipeView {

    fun showUser(user: GoogleAccount)

    fun showNoUser()

    fun showGoogleLoginError(errorCode: Int)
}
