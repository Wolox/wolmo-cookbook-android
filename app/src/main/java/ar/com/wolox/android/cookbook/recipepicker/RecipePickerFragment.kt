package ar.com.wolox.android.cookbook.recipepicker

import android.content.Intent
import ar.com.wolox.android.cookbook.R
import ar.com.wolox.android.cookbook.datasync.DataSyncRecipeActivity
import ar.com.wolox.android.cookbook.facebooklogin.FacebookLoginRecipeActivity
import ar.com.wolox.android.cookbook.googlelogin.GoogleLoginRecipeActivity
import ar.com.wolox.android.cookbook.koin.KoinLoginRecipeActivity
import ar.com.wolox.android.cookbook.navigation.NavigationActivity
import ar.com.wolox.android.cookbook.tests.TestLoginRecipeActivity
import ar.com.wolox.android.cookbook.notifications.NotificationActivity
import ar.com.wolox.android.cookbook.scanqr.ScanQrActivity
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import kotlinx.android.synthetic.main.fragment_recipe_picker.*

class RecipePickerFragment : WolmoFragment<RecipePickerPresenter>(), RecipePickerView {

    override fun layout() = R.layout.fragment_recipe_picker

    override fun init() {}

    override fun showRecipes(recipes: List<Recipe>) {
        vRecipePickerSelectionViewPager.apply {
            adapter = RecipeViewPager(mapRecipesToItems(recipes)) {
                presenter.onRecipeClicked(it)
            }
            setPageTransformer(false, CarouselEffectTransformer())
            pageMargin = resources.getDimensionPixelSize(R.dimen.spacing_medium_more)
        }
    }

    private fun mapRecipesToItems(recipes: List<Recipe>): List<RecipeItem> {
        // Create a RecipeItem with the desired image & text for it inside the 'when' statement
        return recipes.map {
            when (it) {
                Recipe.GOOGLE_LOGIN -> RecipeItem(it, R.drawable.bg_google_login, R.string.recipe_picker_google_login)
                Recipe.FACEBOOK_LOGIN -> RecipeItem(it, R.drawable.bg_facebook_login, R.string.recipe_picker_facebook_login)
                Recipe.NAVIGATION -> RecipeItem(it, R.drawable.bg_navigation, R.string.recipe_picker_navigation)
                Recipe.DATA_SYNC -> RecipeItem(it, R.drawable.bg_data_sync_pokemon, R.string.recipe_picker_data_sync)
                Recipe.TESTS -> RecipeItem(it, R.drawable.bg_tests, R.string.recipe_picker_tests)
                Recipe.KOIN -> RecipeItem(it, R.drawable.bg_koin, R.string.recipe_picker_koin)
                Recipe.NOTIFICATIONS -> RecipeItem(it, R.drawable.bg_notification_recipe, R.string.recipe_picker_notifications)
                Recipe.QR -> RecipeItem(it, R.drawable.bg_scan_qr, R.string.label_capture_qr)
            }
        }
    }

    override fun goToGoogleLogin() {
        requireActivity().startActivity(Intent(requireContext(), GoogleLoginRecipeActivity::class.java))
    }

    override fun goToFacebookLogin() {
        requireActivity().startActivity(Intent(requireContext(), FacebookLoginRecipeActivity::class.java))
    }

    override fun goToNavigation() {
        requireActivity().startActivity(Intent(requireContext(), NavigationActivity::class.java))
    }

    override fun goToDataSyncRecipe() {
        requireContext().startActivity(Intent(requireContext(), DataSyncRecipeActivity::class.java))
    }

    override fun goToTests() {
        requireContext().startActivity(Intent(requireContext(), TestLoginRecipeActivity::class.java))
    }

    override fun goToKoin() {
        requireContext().startActivity(Intent(requireContext(), KoinLoginRecipeActivity::class.java))
    }

    override fun goToNotificationsRecipe() {
        requireContext().startActivity(Intent(requireContext(), NotificationActivity::class.java))
    }

    override fun goToScanQrRecipe() {
        requireContext().startActivity(Intent(requireContext(), ScanQrActivity::class.java))
    }

    companion object {
        fun newInstance() = RecipePickerFragment()
    }
}