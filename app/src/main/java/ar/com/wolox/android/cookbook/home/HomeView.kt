package ar.com.wolox.android.cookbook.home

import android.content.Intent
import ar.com.wolox.android.cookbook.R
import ar.com.wolox.android.cookbook.info.InfoActivity
import ar.com.wolox.android.cookbook.recipepicker.RecipePickerActivity
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : WolmoFragment<HomePresenter>(), HomeView {

    override fun layout(): Int = R.layout.fragment_home

    override fun init() {
        vHomeDraweeView.setImageURI(ANDROID_CHEF_URL)
        vHomeContinueButton.setOnClickListener {
            presenter.onContinueButtonClicked()
        }
    }

    override fun goToRecipePickerScreen() {
        val intent = Intent(requireContext(), RecipePickerActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        startActivity(intent)
    }

    override fun goToInfoScreen() {
        startActivity(Intent(requireContext(), InfoActivity::class.java))
    }

    companion object {
        private const val ANDROID_CHEF_URL = "https://i.imgur.com/oatemPt.png"
    }
}

interface HomeView {

    fun goToInfoScreen()

    fun goToRecipePickerScreen()
}
