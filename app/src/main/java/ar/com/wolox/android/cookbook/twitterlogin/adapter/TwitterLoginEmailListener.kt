package ar.com.wolox.android.cookbook.twitterlogin.adapter

interface TwitterLoginEmailListener {

    fun onEmailSuccess(response: String)

    fun onEmailError(message: String)

    fun onEmailFailure()
}