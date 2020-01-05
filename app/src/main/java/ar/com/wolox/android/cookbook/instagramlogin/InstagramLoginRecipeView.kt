package ar.com.wolox.android.cookbook.instagramlogin

import ar.com.wolox.android.cookbook.instagramlogin.model.InstagramDataItem
import ar.com.wolox.android.cookbook.instagramlogin.model.TypeErrorMessage

interface InstagramLoginRecipeView {

    fun enableLoginBtn()

    fun enableLogoutBtn()

    fun showLoginSuccessMsg()

    fun showLogoutSuccessMsg()

    fun deleteListData()

    fun showWebView(url: String)

    fun igLogout()

    fun showIGData(data: List<InstagramDataItem>)

    fun showFailInService(error: String)

    fun showError(type: TypeErrorMessage)
}