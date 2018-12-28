package ar.com.wolox.android.cookbook.googlelogin.helper

import android.content.Context
import android.content.Intent
import android.support.v4.app.Fragment
import android.view.View
import ar.com.wolox.android.cookbook.R
import ar.com.wolox.android.cookbook.googlelogin.model.GoogleAccount
import ar.com.wolox.wolmo.core.di.scopes.ApplicationScope
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.GoogleSignInStatusCodes
import com.google.android.gms.common.api.ApiException
import javax.inject.Inject

@ApplicationScope
class GoogleHelper @Inject constructor(context: Context) {

    private val applicationContext: Context = context.applicationContext

    fun getLastSignedInAccount() = GoogleSignIn.getLastSignedInAccount(applicationContext)?.let {
        GoogleAccount(it)
    }

    /**
     * Set google login action to the view.
     *
     * @param fragment
     *  the fragment where the button is used.
     *
     * @param resultCode
     *  the code to catch the activity result.
     */
    fun setGoogleLoginAction(view: View, fragment: Fragment, resultCode: Int) {
        // On click button, open Google activity
        view.setOnClickListener {
            fragment.startActivityForResult(getClient(fragment).signInIntent, resultCode)
        }
    }

    /**
     * Set google logout action to the view.
     *
     * @param fragment
     *  the fragment where the button is used.
     *
     * @param onComplete
     *  callback to be called on logout complete.
     */
    fun setGoogleLogoutAction(view: View, fragment: Fragment, onComplete: () -> Unit) {
        view.setOnClickListener { _ ->
            getClient(fragment).signOut().addOnCompleteListener(fragment.requireActivity()) { _ ->
                onComplete()
            }
        }
    }

    /**
     * Get the google sign in client object.
     *
     * @param fragment
     *  the fragment where the login/logout is.
     */
    private fun getClient(fragment: Fragment): GoogleSignInClient {
        // Configure Google Sign-in and the GoogleSignInClient object
        val gso =
                GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestEmail()
                        .build()
        return GoogleSignIn.getClient(fragment.requireActivity(), gso)
    }

    /**
     * Get error message from a code.
     *
     * @param errorCode
     *  the error code.
     */
    fun getErrorMessage(context: Context, errorCode: Int?): String =
            context.getString(when (errorCode) {
                GoogleSignInStatusCodes.SIGN_IN_CANCELLED -> R.string.google_login_error_cancelled
                GoogleSignInStatusCodes.SIGN_IN_CURRENTLY_IN_PROGRESS -> R.string.google_login_error_in_progress
                GoogleSignInStatusCodes.SIGN_IN_FAILED -> R.string.google_login_error_failed
                else -> R.string.google_login_error_unexpected
            })

    companion object {
        /**
         * Get the signed in account from data received by intent on activity result.
         *
         * @param data
         *  the intent received onActivityResult.
         *
         * @param onSuccess
         *  function called on success, receive an UserGoogle model with the signed in account.
         *
         * @param onError
         *  function called on error.
         */
        fun getSignedInAccountFromIntent(data: Intent?, onSuccess: (GoogleAccount) -> Unit, onError: (Int?) -> Unit) {
            // Create the task to get signed in account
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)

            try {
                val account = task.getResult(ApiException::class.java)
                account?.let { onSuccess(GoogleAccount(it)) } ?: onError(null)
            } catch (e: ApiException) {
                e.printStackTrace()
                onError(e.statusCode)
            }
        }
    }
}