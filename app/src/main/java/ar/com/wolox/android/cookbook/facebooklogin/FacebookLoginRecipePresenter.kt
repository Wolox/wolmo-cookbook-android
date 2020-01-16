package ar.com.wolox.android.cookbook.facebooklogin

import ar.com.wolox.android.cookbook.facebooklogin.model.FacebookAccount
import ar.com.wolox.android.cookbook.facebooklogin.proxy.FacebookProxy
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import com.facebook.FacebookException
import javax.inject.Inject

class FacebookLoginRecipePresenter @Inject constructor(private val facebookProxy: FacebookProxy)
    : BasePresenter<FacebookLoginRecipeView>(), FacebookProxy.LoginListener, FacebookProxy.LogoutListener {

    override fun onViewAttached() {
        super.onViewAttached()

        if (facebookProxy.isFacebookAccountPresent()) {
            view?.disableLogin()
            facebookProxy.getLastSignedInAccount(this)
        }
    }

    override fun onLoginSuccess(account: FacebookAccount) {
        view?.showUser(account)
    }

    override fun onLoginCancel() {
        view?.showNoUser()
        view?.showLoginCancel()
    }

    override fun onLoginError(exception: FacebookException) {
        view?.showNoUser()
        view?.showLoginError()
    }

    override fun onLogout() {
        view?.showNoUser()
    }
}