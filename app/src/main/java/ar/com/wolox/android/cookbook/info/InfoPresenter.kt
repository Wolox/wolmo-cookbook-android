package ar.com.wolox.android.cookbook.info

import ar.com.wolox.android.cookbook.utils.SharedPreferencesUtils
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import javax.inject.Inject

class InfoPresenter @Inject constructor(
    private val sharedPreferencesUtils: SharedPreferencesUtils
) : BasePresenter<InfoView>() {

    fun onInfoButtonClicked() {
        sharedPreferencesUtils.hasReadInfo = true
        view?.goToRecipePickerScreen()
    }
}
