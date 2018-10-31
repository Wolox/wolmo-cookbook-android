package ar.com.wolox.android.cookbook.googleLogin.model

import com.google.android.gms.auth.api.signin.GoogleSignInAccount

data class UserGoogle(private val user: GoogleSignInAccount) {
    val email = user.email
    val displayName = user.displayName
    val picture = user.photoUrl
}