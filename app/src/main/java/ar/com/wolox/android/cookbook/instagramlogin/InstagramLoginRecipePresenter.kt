package ar.com.wolox.android.cookbook.instagramlogin

import android.net.Uri
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import javax.inject.Inject

class InstagramLoginRecipePresenter @Inject constructor() : BasePresenter<InstagramLoginRecipeView>() {

    fun onIgLoginRequest() {

        val uriBuilder: Uri.Builder = Uri.Builder()
        uriBuilder.scheme("https")
                .authority("api.instagram.com")
                .appendPath("oauth")
                .appendPath("authorize")
                .appendQueryParameter("client_id", "794a065c52354057836efe405df98186")
                .appendQueryParameter("redirect_uri", "https://instagram.com")
                .appendQueryParameter("response_type", "token")
                .appendQueryParameter("display", "touch")
        // val intent = Intent(Intent.ACTION_VIEW, uriBuilder.build())
        // view.startWebActivity(intent) -> {startActivity(intent)}
        view.showWebView(uriBuilder.toString())
    }
}