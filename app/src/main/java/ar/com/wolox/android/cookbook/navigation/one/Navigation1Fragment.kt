package ar.com.wolox.android.cookbook.navigation.one

import ar.com.wolox.android.cookbook.R
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import kotlinx.android.synthetic.main.fragment_navigation1.*

class Navigation1Fragment : WolmoFragment<Navigation1Presenter>() {

    override fun layout(): Int = R.layout.fragment_navigation1

    override fun init() {

        // Fragment1 to Fragment2
        fragment_navigation1__to2_button.setOnClickListener {
            presenter.onGoTo2ButtonClicked()
        }

        // Fragment1 to NewActivity
        fragment_navigation1__toNewActivity.setOnClickListener {
            presenter.onGoToNewActivityClicked()
        }
    }
}
