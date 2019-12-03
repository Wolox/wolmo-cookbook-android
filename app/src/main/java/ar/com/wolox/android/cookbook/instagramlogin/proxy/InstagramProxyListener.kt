package ar.com.wolox.android.cookbook.instagramlogin.proxy

import ar.com.wolox.android.cookbook.instagramlogin.model.InstagramDataItem

interface InstagramProxyListener {

    fun onResponse(data: List<InstagramDataItem>)

    fun onError()

    fun onFail(message: String?)
}