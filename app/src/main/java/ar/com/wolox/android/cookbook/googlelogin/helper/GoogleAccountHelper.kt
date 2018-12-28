package ar.com.wolox.android.cookbook.googlelogin.helper

import android.content.Intent
import ar.com.wolox.android.cookbook.googlelogin.model.GoogleAccount

class GoogleAccountHelper(private val intent: Intent?) {
    fun getAccount(onSuccess: (GoogleAccount) -> Unit, onError: (Int?) -> Unit) =
            GoogleHelper.getSignedInAccountFromIntent(intent, onSuccess, onError)
}