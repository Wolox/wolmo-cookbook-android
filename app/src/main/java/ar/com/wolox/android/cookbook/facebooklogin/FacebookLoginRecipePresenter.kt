package ar.com.wolox.android.cookbook.facebooklogin

import ar.com.wolox.android.cookbook.facebooklogin.helper.FacebookAccountHelper
import ar.com.wolox.android.cookbook.facebooklogin.helper.FacebookHelper
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import javax.inject.Inject

class FacebookLoginRecipePresenter @Inject constructor(val facebookHelper: FacebookHelper)
    : BasePresenter<FacebookLoginRecipeView>() {

    override fun onViewAttached() {
        super.onViewAttached()

        val signedInAccount = facebookHelper.getLastSignedInAccount()
        if (signedInAccount != null) view.showUser(signedInAccount)
    }

    fun onFacebookLogin(facebookAccountHelper: FacebookAccountHelper) =
            facebookAccountHelper.getAccount(view::showUser, view::showFacebookLoginError)

    fun onFacebookLogout() = view.showNoUser()
}