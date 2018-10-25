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
        fragment_navigation3_to4_button.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("test_string", fragment3_edit_text.text.toString())
            Navigation.findNavController(view!!).navigate(R.id.action_navigation3Fragment_to_navigation4Fragment, bundle)
        }
    }
}
