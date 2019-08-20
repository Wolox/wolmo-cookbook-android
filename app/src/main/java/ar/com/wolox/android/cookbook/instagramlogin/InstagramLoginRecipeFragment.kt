package ar.com.wolox.android.cookbook.instagramlogin

import ar.com.wolox.android.cookbook.R
import ar.com.wolox.wolmo.core.fragment.WolmoFragment

class InstagramLoginRecipeFragment : WolmoFragment<InstagramLoginRecipePresenser>(), InstagramLoginRecipeView {

    override fun layout(): Int = R.layout.fragment_instagram_login

    override fun init() {
    }
}