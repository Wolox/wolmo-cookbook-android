package ar.com.wolox.android.cookbook.home

import ar.com.wolox.android.cookbook.utils.SharedPreferencesUtils
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import javax.inject.Inject

class HomePresenter @Inject constructor(
    private val sharedPreferencesUtils: SharedPreferencesUtils
) : BasePresenter<HomeView>() {

    fun onContinueButtonClicked() {
        if (sharedPreferencesUtils.hasReadInfo) view!!.goToRecipePickerScreen() else view!!.goToInfoScreen()
    }
}
