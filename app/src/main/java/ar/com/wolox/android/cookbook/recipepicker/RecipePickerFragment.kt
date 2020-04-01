package ar.com.wolox.android.cookbook.recipepicker

import android.content.Intent
import ar.com.wolox.android.cookbook.R
import ar.com.wolox.android.cookbook.coroutines.CoroutinesRecipeActivity
import ar.com.wolox.android.cookbook.datasync.DataSyncRecipeActivity
import ar.com.wolox.android.cookbook.facebooklogin.FacebookLoginRecipeActivity
import ar.com.wolox.android.cookbook.googlelogin.GoogleLoginRecipeActivity
import ar.com.wolox.android.cookbook.graphQl.OrdersActivity
import ar.com.wolox.android.cookbook.instagramlogin.InstagramLoginRecipeActivity
import ar.com.wolox.android.cookbook.koin.KoinLoginRecipeActivity
import ar.com.wolox.android.cookbook.mpchart.MpChartRecipeActivity
import ar.com.wolox.android.cookbook.navigation.NavigationActivity
import ar.com.wolox.android.cookbook.notifications.NotificationActivity
import ar.com.wolox.android.cookbook.scanqr.ScanQrActivity
import ar.com.wolox.android.cookbook.room.RoomRecipeActivity
import ar.com.wolox.android.cookbook.tests.TestLoginRecipeActivity
import ar.com.wolox.android.cookbook.twitterlogin.TwitterLoginRecipeActivity
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
                Recipe.COROUTINES -> RecipeItem(it, R.drawable.bg_coroutines, R.string.recipe_picker_coroutines)
                Recipe.GOOGLE_LOGIN -> RecipeItem(it, R.drawable.bg_google_login, R.string.recipe_picker_google_login)
                Recipe.FACEBOOK_LOGIN -> RecipeItem(it, R.drawable.bg_facebook_login, R.string.recipe_picker_facebook_login)
                Recipe.TWITTER_LOGIN -> RecipeItem(it, R.drawable.bg_twitter_login, R.string.recipe_picker_twitter_login)
                Recipe.INSTAGRAM_LOGIN -> RecipeItem(it, R.drawable.bg_instagram_login, R.string.recipe_picker_instagram_login)
                Recipe.ROOM -> RecipeItem(it, R.drawable.bg_room, R.string.recipe_picker_room)
                Recipe.MP_CHART -> RecipeItem(it, R.drawable.bg_mp_chart, R.string.recipe_picker_mp_chart)
                Recipe.NAVIGATION -> RecipeItem(it, R.drawable.bg_navigation, R.string.recipe_picker_navigation)
                Recipe.DATA_SYNC -> RecipeItem(it, R.drawable.bg_data_sync_pokemon, R.string.recipe_picker_data_sync)
                Recipe.TESTS -> RecipeItem(it, R.drawable.bg_tests, R.string.recipe_picker_tests)
                Recipe.KOIN -> RecipeItem(it, R.drawable.bg_koin, R.string.recipe_picker_koin)
                Recipe.NOTIFICATIONS -> RecipeItem(it, R.drawable.bg_notification_recipe, R.string.recipe_picker_notifications)
                Recipe.QR -> RecipeItem(it, R.drawable.bg_scan_qr, R.string.label_capture_qr)
                Recipe.GRAPH_QL -> RecipeItem(it, R.drawable.bg_graph_ql, R.string.recipe_picker_graph_ql)
            }
        }
    }

    private fun goTo(activity: Class<*>) = requireContext().startActivity(Intent(requireContext(), activity))

    override fun goToGoogleLogin() = goTo(GoogleLoginRecipeActivity::class.java)

    override fun goToFacebookLogin() = goTo(FacebookLoginRecipeActivity::class.java)

    override fun goToTwitterLogin() = goTo(TwitterLoginRecipeActivity::class.java)

    override fun goToInstagramLogin() = goTo(InstagramLoginRecipeActivity::class.java)

    override fun goToRoom() = goTo(RoomRecipeActivity::class.java)

    override fun goToMpChart() = goTo(MpChartRecipeActivity::class.java)

    override fun goToNavigation() = goTo(NavigationActivity::class.java)

    override fun goToDataSyncRecipe() = goTo(DataSyncRecipeActivity::class.java)

    override fun goToTests() = goTo(TestLoginRecipeActivity::class.java)

    override fun goToKoin() = goTo(KoinLoginRecipeActivity::class.java)

    override fun goToCoroutines() = goTo(CoroutinesRecipeActivity::class.java)

    override fun goToNotificationsRecipe() = goTo(NotificationActivity::class.java)

    override fun goToGraphQlRecipe() = goTo(OrdersActivity::class.java)

    override fun goToScanQrRecipe() {
        requireContext().startActivity(Intent(requireContext(), ScanQrActivity::class.java))
    }

    companion object {
        fun newInstance() = RecipePickerFragment()
    }
}