package ar.com.wolox.android.cookbook.recipepicker

import android.content.Intent
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.GridLayoutManager
import ar.com.wolox.android.cookbook.R
import ar.com.wolox.android.cookbook.analytics.AnalyticsRecipeActivity
import ar.com.wolox.android.cookbook.coroutines.CoroutinesRecipeActivity
import ar.com.wolox.android.cookbook.databinding.FragmentRecipeListBinding
import ar.com.wolox.android.cookbook.datasync.DataSyncRecipeActivity
import ar.com.wolox.android.cookbook.facebooklogin.FacebookLoginRecipeActivity
import ar.com.wolox.android.cookbook.fingerprint.login.FingerprintLoginRecipeActivity
import ar.com.wolox.android.cookbook.googlelogin.GoogleLoginRecipeActivity
import ar.com.wolox.android.cookbook.graphQl.OrdersActivity
import ar.com.wolox.android.cookbook.instagramlogin.InstagramLoginRecipeActivity
import ar.com.wolox.android.cookbook.koin.KoinLoginRecipeActivity
import ar.com.wolox.android.cookbook.maps.MapActivity
import ar.com.wolox.android.cookbook.lottie.LottieRecipeActivity
import ar.com.wolox.android.cookbook.mercadopago.MercadoPagoRecipeActivity
import ar.com.wolox.android.cookbook.mpchart.MpChartRecipeActivity
import ar.com.wolox.android.cookbook.navigation.NavigationActivity
import ar.com.wolox.android.cookbook.notifications.NotificationActivity
import ar.com.wolox.android.cookbook.room.RoomRecipeActivity
import ar.com.wolox.android.cookbook.tests.TestLoginRecipeActivity
import ar.com.wolox.android.cookbook.twitterlogin.TwitterLoginRecipeActivity
import ar.com.wolox.wolmo.core.fragment.WolmoFragment

class RecipePickerFragment : WolmoFragment<FragmentRecipeListBinding, RecipePickerPresenter>(), RecipePickerView {

    override fun layout() = R.layout.fragment_recipe_list

    override fun init() {}

    override fun showRecipes(recipes: List<Recipe>) {
        binding!!.vRecipeList.apply {
            adapter = RecipePickerAdapter(presenter).apply {
                submitList(mapRecipesToItems(recipes))
            }
            layoutManager = GridLayoutManager(context, 4)
        }
    }

    override fun setListeners() {
        with(binding!!) {
            searchInput.doOnTextChanged { text, _, _, _ ->
                presenter.onSearchPerformed(text.toString())
            }
        }
    }

    private fun mapRecipesToItems(recipes: List<Recipe>): List<RecipeItem> {
        // Create a RecipeItem with the desired image, text and background color for it inside the 'when' statement
        return recipes.map {
            when (it) {
                Recipe.LOTTIE -> RecipeItem(it, R.drawable.ic_lottie, R.string.recipe_picker_lottie)
                Recipe.MERCADOPAGO -> RecipeItem(it, R.drawable.ic_mercadopago, R.string.recipe_picker_mercadopago)
                Recipe.ANALYTICS -> RecipeItem(it, R.drawable.bg_firebase, R.string.recipe_picker_firebase, R.color.firebase_color)
                Recipe.COROUTINES -> RecipeItem(it, R.drawable.ic_coroutines, R.string.recipe_picker_coroutines, R.color.yellow_coroutines)
                Recipe.GOOGLE_LOGIN -> RecipeItem(it, R.drawable.ic_google, R.string.recipe_picker_google_login)
                Recipe.FACEBOOK_LOGIN -> RecipeItem(it, R.drawable.ic_facebook, R.string.recipe_picker_facebook_login)
                Recipe.TWITTER_LOGIN -> RecipeItem(it, R.drawable.ic_twitter, R.string.recipe_picker_twitter_login, R.color.twitter_color)
                Recipe.INSTAGRAM_LOGIN -> RecipeItem(it, R.drawable.ic_instagram, R.string.recipe_picker_instagram_login)
                Recipe.ROOM -> RecipeItem(it, R.drawable.ic_room, R.string.recipe_picker_room, R.color.px_orange_status_bar)
                Recipe.MP_CHART -> RecipeItem(it, R.drawable.ic_chart, R.string.recipe_picker_mp_chart)
                Recipe.NAVIGATION -> RecipeItem(it, R.drawable.ic_navigation, R.string.recipe_picker_navigation)
                Recipe.DATA_SYNC -> RecipeItem(it, R.drawable.bg_data_sync_pokemon, R.string.recipe_picker_data_sync)
                Recipe.TESTS -> RecipeItem(it, R.drawable.bg_tests, R.string.recipe_picker_tests)
                Recipe.KOIN -> RecipeItem(it, R.drawable.ic_koin, R.string.recipe_picker_koin, R.color.koin_color)
                Recipe.NOTIFICATIONS -> RecipeItem(it, R.drawable.ic_notifications, R.string.recipe_picker_notifications)
                Recipe.GRAPH_QL -> RecipeItem(it, R.drawable.ic_graphql, R.string.recipe_picker_graph_ql)
                Recipe.BIOMETRIC_LOGIN -> RecipeItem(it, R.drawable.bg_fingerprint, R.string.recipe_picker_fingerprint)
                Recipe.MAP -> RecipeItem(it, R.drawable.bg_google_maps, R.string.recipe_picker_google_maps)
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

    override fun goToAnalyticsRecipe() = goTo(AnalyticsRecipeActivity::class.java)

    override fun goToMercadoPagoRecipe() = goTo(MercadoPagoRecipeActivity::class.java)

    override fun goToMaps() = goTo(MapActivity::class.java)

    override fun goToFingerprintRecipe() = goTo(FingerprintLoginRecipeActivity::class.java)

    override fun goToLottieRecipe() = goTo(LottieRecipeActivity::class.java)

    companion object {
        fun newInstance() = RecipePickerFragment()
    }
}