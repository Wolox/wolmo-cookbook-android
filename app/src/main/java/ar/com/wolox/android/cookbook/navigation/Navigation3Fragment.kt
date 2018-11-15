package ar.com.wolox.android.cookbook.navigation

import android.os.Bundle
import androidx.navigation.Navigation
import ar.com.wolox.android.cookbook.R
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import kotlinx.android.synthetic.main.fragment_navigation3.fragment_navigation3_to4_button
import kotlinx.android.synthetic.main.fragment_navigation3.fragment3_edit_text

class Navigation3Fragment : WolmoFragment<BasePresenter<Any>>() {

    override fun layout(): Int = R.layout.fragment_navigation3

    override fun init() {

        val navController = Navigation.findNavController(view!!)

        // Fragment3 to Fragment4 with arguments
        fragment_navigation3_to4_button.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("test_string", fragment3_edit_text.text.toString())
            navController.navigate(R.id.action_navigation3Fragment_to_navigation4Fragment, bundle,
                    NavigationUtils.getForwardAnimationOptions())
        }
    }
}
