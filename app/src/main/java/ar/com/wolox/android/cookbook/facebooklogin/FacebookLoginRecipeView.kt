package ar.com.wolox.android.cookbook.facebooklogin

import ar.com.wolox.android.cookbook.facebooklogin.model.FacebookAccount

interface FacebookLoginRecipeView {

    fun showUser(user: FacebookAccount)

    fun showNoUser()

    fun showLoginCancel()

    fun showLoginError()
}
