package ar.com.wolox.android.cookbook.navigation

import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import ar.com.wolox.android.cookbook.R
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import kotlinx.android.synthetic.main.fragment_navigation1.*

class Navigation1Fragment : WolmoFragment<BasePresenter<Any>>() {

    override fun layout(): Int = R.layout.fragment_navigation1

    override fun init() {
        val navController = Navigation.findNavController(view!!)

        // Fragment1 to Fragment2
        fragment_navigation1__to2_button.setOnClickListener {
            navController.navigate(R.id.action_navigation1Fragment_to_navigation2BFragment, null,
                    NavOptions.Builder()
                            .setEnterAnim(R.anim.slide_in_right)
                            .setExitAnim(R.anim.slide_out_left)
                            .setPopEnterAnim(R.anim.slide_in_left)
                            .setPopExitAnim(R.anim.slide_out_right)
                            .build())
        }

        // Fragment1 to NewActivity
        fragment_navigation1__toNewActivity.setOnClickListener {
            navController.navigate(R.id.action_navigation1Fragment_to_newNavigationFragment)
        }
    }
}
