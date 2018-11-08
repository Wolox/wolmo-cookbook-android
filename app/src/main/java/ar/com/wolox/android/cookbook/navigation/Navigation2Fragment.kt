package ar.com.wolox.android.cookbook.navigation

import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import ar.com.wolox.android.cookbook.R
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import kotlinx.android.synthetic.main.fragment_navigation2.fragment_navigation2_to1_button
import kotlinx.android.synthetic.main.fragment_navigation2.fragment_navigation2_to3_button

class Navigation2Fragment : WolmoFragment<BasePresenter<Any>>() {

    override fun layout(): Int = R.layout.fragment_navigation2

    override fun init() {
        val navController = Navigation.findNavController(view!!)

        // Goingback programatically
        fragment_navigation2_to1_button.setOnClickListener {
            navController.popBackStack()
        }

        // Fragment2B to Fragment3 / When clicking back on Fragment3 it will return to Fragment1
        fragment_navigation2_to3_button.setOnClickListener {
            navController.navigate(R.id.action_navigation2Fragment_to_navigation3Fragment, null,
                    NavOptions.Builder()
                            .setEnterAnim(R.anim.slide_in_right)
                            .setExitAnim(R.anim.slide_out_left)
                            .setPopEnterAnim(R.anim.slide_in_left)
                            .setPopExitAnim(R.anim.slide_out_right)
                            .setPopUpTo(R.id.navigation2Fragment, true)
                            .build())
        }
    }
}
