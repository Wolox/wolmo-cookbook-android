package ar.com.wolox.android.cookbook.datasync

import ar.com.wolox.android.cookbook.R
import ar.com.wolox.wolmo.core.fragment.WolmoFragment

class PokemonSearchFragment : WolmoFragment<PokemonSearchPresenter>() {

    override fun layout() = R.layout.fragment_pokemon_search

    override fun init() {}

    companion object {
        fun newInstance() = PokemonSearchFragment()
    }
}