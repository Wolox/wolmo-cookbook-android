package ar.com.wolox.android.cookbook.googlelogin.model

import com.google.android.gms.auth.api.signin.GoogleSignInAccount

data class GoogleAccount(private val user: GoogleSignInAccount) {
    val email = user.email
    val displayName = user.displayName
    val picture = user.photoUrl
}