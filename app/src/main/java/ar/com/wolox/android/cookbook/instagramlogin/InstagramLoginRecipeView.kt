package ar.com.wolox.android.cookbook.instagramlogin

import ar.com.wolox.android.cookbook.instagramlogin.model.InstagramDataItem

interface InstagramLoginRecipeView {

    fun enableLoginBtn()
    fun enableLogoutBtn()
    fun showLoginError()
    fun showLogoutError()
    fun showLoginSuccessMsg()
    fun showLogoutSuccessMsg()
    fun deleteListData()

    fun showNetworkUnavailableError()

    fun showWebView(url: String)

    fun igLogout()

    fun showIGData(data: List<InstagramDataItem>)
    fun showErrorInService()
    fun showFailInService(error: String)
    fun showFetchDataError()
}