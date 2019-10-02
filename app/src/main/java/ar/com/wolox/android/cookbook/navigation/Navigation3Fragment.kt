package ar.com.wolox.android.cookbook.navigation

import android.os.Bundle
import ar.com.wolox.android.cookbook.R
import ar.com.wolox.android.cookbook.navigation.one.NavigationPresenter
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import kotlinx.android.synthetic.main.fragment_navigation3.*

class Navigation3Fragment : WolmoFragment<NavigationPresenter>() {

    override fun layout(): Int = R.layout.fragment_navigation3

    override fun init() {

        // Fragment3 to Fragment4 with arguments
        fragment_navigation3_to4_button.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("test_string", fragment3_edit_text.text.toString())

            presenter.onGoTo4ButtonClicked(bundle)
        }
    }
}
