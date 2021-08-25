package ar.com.wolox.android.cookbook.lottie

import ar.com.wolox.android.cookbook.R
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import ar.com.wolox.wolmo.core.presenter.BasePresenter

class LottieRecipeFragment : WolmoFragment<BasePresenter<Any>>(), LottieRecipeView {

    override fun init() {
    }

    override fun layout(): Int = R.layout.fragment_lottie
}