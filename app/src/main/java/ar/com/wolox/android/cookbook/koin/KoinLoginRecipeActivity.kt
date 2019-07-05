package ar.com.wolox.android.cookbook.koin

import ar.com.wolox.android.cookbook.R
import ar.com.wolox.android.cookbook.koin.core.BaseActivity
import org.koin.android.ext.android.inject

class KoinLoginRecipeActivity : BaseActivity() {

    private val fragment: KoinLoginRecipeFragment by inject()

    override fun layout(): Int = R.layout.activity_base

    override fun init() = replaceFragment(R.id.vActivityBaseContent, fragment)
}