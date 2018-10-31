package ar.com.wolox.android.cookbook.googleLogin

import android.content.Intent
import android.support.v4.app.Fragment
import android.view.View
import ar.com.wolox.android.cookbook.googleLogin.model.UserGoogle
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException

/**
 * Set google login action to the button
 *
 * @param fragment
 *  the fragment where the button is used
 *
 * @param resultCode
 *  the code to catch the activity result
 */
fun View.setGoogleLoginAction(fragment: Fragment, resultCode: Int): UserGoogle? {

    // Check for existing Google Sign In account, if the user is already signed in, return account
    val account = GoogleSignIn.getLastSignedInAccount(fragment.requireContext())

    // On click button, open Google activity
    this.setOnClickListener {
        fragment.startActivityForResult(GoogleHelper.getClient(fragment).signInIntent, resultCode)
    }

    return if (account != null) UserGoogle(account) else null
}

fun View.setGoogleLogoutAction(fragment: Fragment, response: () -> Unit) {
    this.setOnClickListener {
        GoogleHelper.getClient(fragment).signOut().addOnCompleteListener(fragment.requireActivity()) {
            response()
        }
    }
}

class GoogleHelper {
    companion object {

        /**
         * Get the signed in account from data received by intent on activity result
         *
         * @param data
         *  the intent received onActivityResult
         *
         * @param onSuccess
         *  function called on success, receive an UserGoogle model with the signed in account
         *
         * @param onError
         *  function called on error
         */
        fun getSignedInAccountFromIntent(data: Intent?, onSuccess: (UserGoogle) -> Unit, onError: () -> Unit) {
            // Create the task to get signed in account
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)

            try {
                val account = task.getResult(ApiException::class.java)

                if (account == null) {
                    onError()
                } else {
                    onSuccess(UserGoogle(account))
                }
            } catch (e: ApiException) {
                e.printStackTrace()
                onError()
            }
        }

        /**
         * Get the google sign in client object
         *
         * @param fragment
         *      the fragment where the login/logout is
         */
        fun getClient(fragment: Fragment): GoogleSignInClient {
            // Configure Google Sign-in and the GoogleSignInClient object
            val gso =
                    GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                            .requestEmail()
                            .build()
            return GoogleSignIn.getClient(fragment.requireActivity(), gso)
        }
    }
}