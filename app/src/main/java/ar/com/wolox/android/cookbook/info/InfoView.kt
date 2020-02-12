package ar.com.wolox.android.cookbook.info

import android.content.Intent
import ar.com.wolox.android.cookbook.R
import ar.com.wolox.android.cookbook.recipepicker.RecipePickerActivity
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import kotlinx.android.synthetic.main.fragment_info.*

class InfoFragment : WolmoFragment<InfoPresenter>(), InfoView {

    override fun layout(): Int = R.layout.fragment_info

    override fun init() {}

    override fun setListeners() {
        super.setListeners()
        vInfoButton.setOnClickListener { presenter.onInfoButtonClicked() }
    }

    override fun goToRecipePickerScreen() {
        val intent = Intent(requireContext(), RecipePickerActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        startActivity(intent)
    }
}

interface InfoView {

    fun goToRecipePickerScreen()
}
