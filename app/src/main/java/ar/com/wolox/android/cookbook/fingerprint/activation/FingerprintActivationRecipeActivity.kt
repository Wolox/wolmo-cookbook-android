package ar.com.wolox.android.cookbook.fingerprint.activation

import ar.com.wolox.android.cookbook.R
import ar.com.wolox.wolmo.core.activity.WolmoActivity

class FingerprintActivationRecipeActivity : WolmoActivity() {

    override fun layout() = R.layout.activity_base

    override fun init() {
        replaceFragment(R.id.vActivityBaseContent, FingerprintActivationRecipeFragment.newInstance())
    }
}
