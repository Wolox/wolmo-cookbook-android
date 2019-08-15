package ar.com.wolox.android.cookbook.twitterlogin.adapter

import com.twitter.sdk.android.core.models.User

interface TwitterLoginPictureListener {

    fun onUserSuccess(user: User)
    fun onUserError(message: String)
    fun onUserFail()
}