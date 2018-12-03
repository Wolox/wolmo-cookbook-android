package ar.com.wolox.android.cookbook.navigation

import android.os.Bundle
import ar.com.wolox.android.cookbook.R
import ar.com.wolox.android.cookbook.R.id.action_navigation3Fragment_to_navigation1Fragment2
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import kotlinx.android.synthetic.main.fragment_navigation3.*

class Navigation3Fragment : WolmoFragment<BasePresenter<Any>>() {

    override fun layout(): Int = R.layout.fragment_navigation3

    override fun init() {

        // Fragment3 to Fragment4 with arguments
        fragment_navigation3_to4_button.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("test_string", fragment3_edit_text.text.toString())
            requireNavController().navigate(R.id.action_navigation3Fragment_to_navigation4Fragment, bundle,
                    createOptionsWithDefaultAnimations())
        }
    }

    override fun onBackPressed(): Boolean {
        // Going back to Navigation1Fragment and popup backstack
        requireNavController().navigate(action_navigation3Fragment_to_navigation1Fragment2, null,
                    getBackwardOptions(R.id.navigation1Fragment, true))
        return true
    }
}
