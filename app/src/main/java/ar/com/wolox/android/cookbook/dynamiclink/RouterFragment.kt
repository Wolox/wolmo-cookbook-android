package ar.com.wolox.android.cookbook.dynamiclink

import android.os.Bundle
import ar.com.wolox.essen.R
import ar.com.wolox.essen.recipe.RecipeActivity
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import ar.com.wolox.wolmo.core.util.ToastFactory
import javax.inject.Inject

class RouterFragment : WolmoFragment<RouterPresenter>(), RouterView {

    @Inject
    lateinit var toastFactory: ToastFactory

    override fun layout() = R.layout.fragment_home_feed

    override fun init() {
        val externalId = arguments!!.getString(KEY_EXTERNAL_ID)
        if (externalId != null) {
            presenter.onRecipeFromLink(externalId.toInt())
        }
    }

    override fun goToRecipe() {
        RecipeActivity.start(requireContext())
    }

    companion object {
        fun newInstance(externalId: String) = RouterFragment().apply {
            val bundle = Bundle().apply {
                putString(KEY_EXTERNAL_ID, externalId)
            }
            arguments = bundle
        }
        private const val KEY_EXTERNAL_ID = "external_id"
    }
}

interface RouterView {

    fun goToRecipe()
}