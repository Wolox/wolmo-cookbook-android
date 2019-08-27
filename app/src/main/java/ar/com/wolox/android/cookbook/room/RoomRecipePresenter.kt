package ar.com.wolox.android.cookbook.room

import ar.com.wolox.wolmo.core.presenter.BasePresenter
import javax.inject.Inject

class RoomRecipePresenter @Inject constructor() : BasePresenter<RoomRecipeView>() {

    private var userName: String? = null

    fun onSessionButtonClicked(user: String) {

        userName?.let {
            userName = null
            view.logout()
        } ?: run {
            if (user.isNotEmpty()) {
                userName = user
                view.loginSuccess()
            } else {
                view.loginError()
            }
        }
    }

    fun onAddButtonClicked(data: String) {
    }

    fun onClearButtonClicked() {
    }
}