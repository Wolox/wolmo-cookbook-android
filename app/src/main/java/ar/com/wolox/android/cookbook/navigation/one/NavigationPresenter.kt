package ar.com.wolox.android.cookbook.navigation.one

import android.os.Bundle
import ar.com.wolox.android.cookbook.navigation.Screens
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class NavigationPresenter @Inject constructor(
    private val router: Router
) : BasePresenter<INavigation1View>() {

    fun onGoTo2ButtonClicked() {
        router.navigateTo(Screens.Navigation2Screen())
    }

    fun onGoToNewActivityClicked() {
        router.navigateTo(Screens.NavigationNewScreen())
    }

    fun onGoBackTo1() {
        router.backTo(Screens.Navigation1Screen())
    }

    fun onGoToNewRootChain3() {
        router.newRootChain(Screens.Navigation3Screen())
    }

    fun onGoTo4ButtonClicked(bundle: Bundle) {
        router.navigateTo(Screens.Navigation4Screen(bundle))
    }
}
