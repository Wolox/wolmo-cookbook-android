package ar.com.wolox.android.cookbook.navigation

import ar.com.wolox.android.cookbook.R
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import kotlinx.android.synthetic.main.fragment_navigation1.*

class Navigation1Fragment : WolmoFragment<BasePresenter<Any>>() {

    override fun layout(): Int = R.layout.fragment_navigation1

    override fun init() {

        // Fragment1 to Fragment2
        fragment_navigation1__to2_button.setOnClickListener {
            requireNavController().navigate(R.id.action_navigation1Fragment_to_navigation2BFragment, null,
                    createOptionsWithDefaultAnimations())
        }

        // Fragment1 to NewActivity
        fragment_navigation1__toNewActivity.setOnClickListener {
            requireNavController().navigate(R.id.action_navigation1Fragment_to_newNavigationFragment, null,
                    createOptionsWithDefaultAnimations())
        }
    }
}
