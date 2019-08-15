package ar.com.wolox.android.cookbook.twitterlogin

import ar.com.wolox.android.cookbook.R
import ar.com.wolox.wolmo.core.fragment.WolmoFragment

class TwitterLoginRecipeFragment : WolmoFragment<TwitterLoginRecipePresenter>(), TwitterLoginRecipeView {

    override fun layout(): Int = R.layout.fragment_twitter_login

    override fun init() {
    }
}