package ar.com.wolox.android.cookbook.instagramlogin

import android.net.Uri
import android.util.Log
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import javax.inject.Inject

class InstagramLoginRecipePresenter @Inject constructor() : BasePresenter<InstagramLoginRecipeView>() {

    private var accessToken: String? = null

    fun onIgLoginRequest() {

        val uriBuilder: Uri.Builder = Uri.Builder()
        uriBuilder.scheme("https")
                .authority("api.instagram.com")
                .appendPath("oauth")
                .appendPath("authorize")
                .appendQueryParameter("client_id", "794a065c52354057836efe405df98186")
                .appendQueryParameter("redirect_uri", "https://instagram.com")
                .appendQueryParameter("response_type", "accessToken")
                .appendQueryParameter("display", "touch")
        // val intent = Intent(Intent.ACTION_VIEW, uriBuilder.build())
        // view.startWebActivity(intent) -> {startActivity(intent)}
        view.showWebView(uriBuilder.toString())
    }

    fun onLoginSuccessResponse(token: String) {
        Log.e("FedeLog", "onLoginSuccessResponse: $token")
        accessToken = token
    }

    fun onLoginErrorResponse() {
        Log.e("FedeLog", "onLoginErrorResponse")
        accessToken = null
    }

    fun onLoginFailResponse() {
        Log.e("FedeLog", "onLoginFailResponse")
        accessToken = null
    }

    fun onFetchDataRequest() {
        if (accessToken != null && accessToken!!.isNotEmpty()) {
            //TODO download data
        } else {
            //TODO View -> show error ("Please login first")
        }
    }
}