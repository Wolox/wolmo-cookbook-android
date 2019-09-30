package ar.com.wolox.android.cookbook.navigation.one

import ar.com.wolox.android.cookbook.navigation.Screens
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class Navigation1Presenter @Inject constructor(
    private val router: Router
) : BasePresenter<INavigation1View>() {

    fun onGoTo2ButtonClicked() {
        router.navigateTo(Screens.Navigation2Screen())
    }

    fun onGoToNewActivityClicked() {
        router.navigateTo(Screens.NavigationNewScreen())
    }
}
