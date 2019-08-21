package ar.com.wolox.android.cookbook.instagramlogin.adapter

interface InstagramLoginAuthListener {

    fun onCodeReceived(authToken: String)
    fun onCodeError()
    fun onApiError()
}