package ar.com.wolox.android.cookbook.datasync

import ar.com.wolox.android.cookbook.R
import ar.com.wolox.android.cookbook.databinding.ActivityBaseBinding
import ar.com.wolox.wolmo.core.activity.WolmoActivity

class DataSyncRecipeActivity : WolmoActivity<ActivityBaseBinding>() {

    override fun layout() = R.layout.activity_base

    override fun init() {
        replaceFragment(R.id.vActivityBaseContent, PokemonDetailFragment.newInstance())
    }
}
