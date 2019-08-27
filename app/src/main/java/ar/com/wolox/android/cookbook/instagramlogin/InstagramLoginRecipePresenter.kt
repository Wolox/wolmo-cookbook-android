package ar.com.wolox.android.cookbook.instagramlogin

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.net.Uri
import ar.com.wolox.android.cookbook.instagramlogin.model.InstagramDataItem
import ar.com.wolox.android.cookbook.instagramlogin.proxy.InstagramProxy
import ar.com.wolox.android.cookbook.instagramlogin.proxy.InstagramProxyListener
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import javax.inject.Inject

class InstagramLoginRecipePresenter @Inject constructor(
    private val adapter: InstagramProxy,
    private val application: Application
) : BasePresenter<InstagramLoginRecipeView>() {

    private var accessToken: String? = null

    override fun onViewAttached() {
        super.onViewAttached()

        accessToken?.let {
            view.enableLogoutBtn()
        } ?: run {
            view.enableLoginBtn()
        }
    }

    fun onSessionButtonClicked() {

        if (isNetworkAvailable()) {
            accessToken?.let {
                view.igLogout()
            } ?: run {
                val uriBuilder: Uri.Builder = Uri.Builder()
                uriBuilder.scheme(SCHEME)
                        .authority(AUTH)
                        .appendPath(AUTH_KEY)
                        .appendPath(AUTH_VALUE)
                        .appendQueryParameter(CLIENT_KEY, CLIENT_VALUE)
                        .appendQueryParameter(URI_KEY, URI_VALUE)
                        .appendQueryParameter(RESPONSE_KEY, RESPONSE_VALUE)
                        .appendQueryParameter(DISPLAY_KEY, DISPLAY_VALUE)

                view.showWebView(uriBuilder.toString())
            }
        } else {
            view.showNetworkUnavailableError()
        }
    }

    fun onLogoutResponse(response: Boolean) {
        if (response) {
            accessToken = null
            view.run {
                enableLoginBtn()
                deleteListData()
                showLogoutSuccessMsg()
            }
        } else {
            view.run {
                enableLogoutBtn()
                showLogoutError()
            }
        }
    }

    fun onLoginSuccessResponse(token: String) {
        accessToken = token
        view.run {
            enableLogoutBtn()
            showLoginSuccessMsg()
        }
    }

    fun onLoginErrorResponse() {
        accessToken = null
        view.run {
            enableLoginBtn()
            showLoginError()
        }
    }

    fun onLoginFailResponse() {
        accessToken = null
        view.run {
            enableLoginBtn()
            showLoginError()
        }
    }

    fun onFetchDataButtonClicked() {

        if (isNetworkAvailable()) {
            accessToken?.let {
                adapter.getInstagramData(it, object : InstagramProxyListener {
                    override fun onResponse(data: List<InstagramDataItem>) {
                        view.showIGData(data)
                    }

                    override fun onError() {
                        view.showErrorInService()
                    }

                    override fun onFail(message: String?) {
                        message?.let { lambda ->
                            view.showFailInService(lambda)
                        } ?: run {
                            view.showErrorInService()
                        }
                    }
                })
            } ?: run {
                view.showFetchDataError()
            }
        } else {
            view.showNetworkUnavailableError()
        }
    }

    private fun isNetworkAvailable(): Boolean {

        val connectivityManager = application.applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE)
        return if (connectivityManager is ConnectivityManager) {
            val networkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo
            networkInfo?.isConnected ?: false
        } else false
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