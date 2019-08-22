package ar.com.wolox.android.cookbook.instagramlogin

import ar.com.wolox.android.cookbook.instagramlogin.model.InstagramDataItem

interface InstagramLoginRecipeView {

    fun enableLoginBtn()
    fun enableLogoutBtn()

    fun isNetworkAvailable(): Boolean
    fun showNetworkUnavailableError()

    fun showWebView(url: String)

    fun igLogout()

    fun showIGData(data: List<InstagramDataItem>)
}