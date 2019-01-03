package ar.com.wolox.android.cookbook.facebooklogin

import ar.com.wolox.android.cookbook.facebooklogin.helper.FacebookHelper
import ar.com.wolox.android.cookbook.facebooklogin.model.FacebookAccount
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import com.facebook.FacebookException
import javax.inject.Inject

class FacebookLoginRecipePresenter @Inject constructor(private val facebookHelper: FacebookHelper)
    : BasePresenter<FacebookLoginRecipeView>(), FacebookHelper.LoginListener, FacebookHelper.LogoutListener {

    override fun onViewAttached() {
        super.onViewAttached()

        val signedInAccount = facebookHelper.getLastSignedInAccount(this)
        if (signedInAccount) view.disableLogin()
    }

    override fun onLoginSuccess(account: FacebookAccount) {
        view.showUser(account)
    }

    override fun onLoginCancel() {
        view.showNoUser()
        view.showLoginCancel()
    }

    override fun onLoginError(exception: FacebookException) {
        view.showNoUser()
        view.showLoginError()
    }

    override fun onLogout() {
        view.showNoUser()
    }
}