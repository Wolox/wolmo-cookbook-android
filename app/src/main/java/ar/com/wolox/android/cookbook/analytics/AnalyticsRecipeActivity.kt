package ar.com.wolox.android.cookbook.analytics

import ar.com.wolox.android.cookbook.R
import ar.com.wolox.android.cookbook.databinding.ActivityBaseBinding
import ar.com.wolox.wolmo.core.activity.WolmoActivity

/**
 * To use analytics you should download the google-services.json that firebase provides.
 *
 * You can follow the project setup on this link: https://firebase.google.com/docs/android/setup
 */
class AnalyticsRecipeActivity : WolmoActivity<ActivityBaseBinding>() {

    override fun layout(): Int = R.layout.activity_base

    override fun init() = replaceFragment(R.id.vActivityBaseContent, AnalyticsRecipeFragment.newInstance())
}