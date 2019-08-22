package ar.com.wolox.android.cookbook.instagramlogin

import android.net.Uri
import android.util.Log
import ar.com.wolox.android.cookbook.instagramlogin.model.InstagramDataItem
import ar.com.wolox.android.cookbook.instagramlogin.proxy.InstagramProxy
import ar.com.wolox.android.cookbook.instagramlogin.proxy.InstagramProxyListener
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import javax.inject.Inject

class InstagramLoginRecipePresenter @Inject constructor(
    private val adapter: InstagramProxy
) : BasePresenter<InstagramLoginRecipeView>() {

    private var accessToken: String? = null

    override fun onViewAttached() {
        super.onViewAttached()

        if (accessToken == null) {
            view.enableLoginBtn()
        } else {
            view.enableLogoutBtn()
        }
    }

    fun onIgLoginRequest() {

        if (view.isNetworkAvailable()) {
            if (accessToken == null) {

                val uriBuilder: Uri.Builder = Uri.Builder()
                uriBuilder.scheme(SCHEME)
                        .authority(AUTH)
                        .appendPath(AUTH_KEY)
                        .appendPath(AUTH_VALUE)
                        .appendQueryParameter(CLIENT_KEY, CLIENT_VALUE)
                        .appendQueryParameter(URI_KEY, URI_VALUE)
                        .appendQueryParameter(RESPONSE_KEY, RESPONSE_VALUE)
                        .appendQueryParameter(DISPLAY_KEY, DISPLAY_VALUE)

                // val intent = Intent(Intent.ACTION_VIEW, uriBuilder.build())
                // view.startWebActivity(intent) -> {startActivity(intent)}
                view.showWebView(uriBuilder.toString())
            } else {
                view.igLogout()
            }
        } else {
            view.showNetworkUnavailableError()
        }
    }

    fun onLoginSuccessResponse(token: String) {
        Log.e("FedeLog", "onLoginSuccessResponse: $token")
        accessToken = token
        view.enableLogoutBtn()
    }

    fun onLoginErrorResponse() {
        Log.e("FedeLog", "onLoginErrorResponse")
        accessToken = null
        view.enableLoginBtn()
    }

    fun onLoginFailResponse() {
        Log.e("FedeLog", "onLoginFailResponse")
        accessToken = null
        view.enableLoginBtn()
    }

    fun onFetchDataRequest() {

        if (view.isNetworkAvailable()) {
            if (accessToken != null && accessToken!!.isNotEmpty()) {
                adapter.getInstagramData(accessToken!!, object : InstagramProxyListener {
                    override fun onResponse(data: List<InstagramDataItem>) {
                        // TODO onRESPONSE
                        view.showIGData(data)
                    }

                    override fun onError() {
                        // TODO onERROR
                    }

                    override fun onFail() {
                        // TODO onFAIL
                    }
                })
            } else {
                // TODO View -> show error ("Please login first")
            }
        } else {
            view.showNetworkUnavailableError()
        }
    }

    companion object {
        private const val SCHEME = "https"
        private const val AUTH = "api.instagram.com"
        private const val AUTH_KEY = "oauth"
        private const val AUTH_VALUE = "authorize"
        private const val CLIENT_KEY = "client_id"
        private const val CLIENT_VALUE = "794a065c52354057836efe405df98186" // API Key
        private const val URI_KEY = "redirect_uri"
        private const val URI_VALUE = "https://instagram.com" // URI to redirect
        private const val RESPONSE_KEY = "response_type"
        private const val RESPONSE_VALUE = "token"
        private const val DISPLAY_KEY = "display"
        private const val DISPLAY_VALUE = "touch"
    }
}