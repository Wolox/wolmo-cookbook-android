package ar.com.wolox.android.cookbook.navigation

import androidx.navigation.Navigation
import ar.com.wolox.android.cookbook.R
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import kotlinx.android.synthetic.main.fragment_navigation1.fragment_navigation_to2a_button
import kotlinx.android.synthetic.main.fragment_navigation1.fragment_navigation1__to2b_button
import kotlinx.android.synthetic.main.fragment_navigation1.fragment_navigation1__toGoogle

class Navigation1Fragment : WolmoFragment<BasePresenter<Any>>() {

    override fun layout(): Int = R.layout.fragment_navigation1

    override fun init() {
        fragment_navigation_to2a_button.setOnClickListener {
            Navigation.findNavController(view!!).navigate(R.id.action_navigation1Fragment_to_navigation2AFragment)
        }

        fragment_navigation1__to2b_button.setOnClickListener {
            Navigation.findNavController(view!!).navigate(R.id.action_navigation1Fragment_to_navigation2BFragment)
        }

        fragment_navigation1__toGoogle.setOnClickListener {
            Navigation.findNavController(view!!).createDeepLink()
                    .setDestination(R.id.action_navigation1Fragment_to_navigationDeepLink)
                    .createPendingIntent()
//           Navigation.findNavController(view!!).navigate(R.id.action_navigation1Fragment_to_navigationDeepLink)
        }
    }
}
