package ar.com.wolox.android.cookbook.signup

import ar.com.wolox.android.cookbook.R
import ar.com.wolox.wolmo.core.fragment.WolmoFragment

class SignUpRecipeFragment : WolmoFragment<SignUpRecipePresenter>(), SignUpView {

    override fun layout(): Int = R.layout.fragment_sign_up

    override fun init() {
    }
}