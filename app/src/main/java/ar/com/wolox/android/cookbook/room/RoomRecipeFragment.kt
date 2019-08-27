package ar.com.wolox.android.cookbook.room

import ar.com.wolox.android.cookbook.R
import ar.com.wolox.wolmo.core.fragment.WolmoFragment

class RoomRecipeFragment : WolmoFragment<RoomRecipePresenter>(), RoomRecipeView {

    override fun layout(): Int = R.layout.fragment_room

    override fun init() {
    }
}