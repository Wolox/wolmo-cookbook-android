package ar.com.wolox.android.cookbook.instagramlogin

import ar.com.wolox.android.cookbook.instagramlogin.model.InstagramDataItem

interface InstagramLoginRecipeView {

    fun showWebView(url: String)

    fun showIGData(data: List<InstagramDataItem>)
}