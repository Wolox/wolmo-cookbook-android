package ar.com.wolox.android.cookbook.facebooklogin.helper

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Base64
import android.util.Log
import android.view.View
import ar.com.wolox.android.cookbook.facebooklogin.model.FacebookAccount
import ar.com.wolox.wolmo.core.di.scopes.ApplicationScope
import com.facebook.AccessToken
import com.facebook.AccessTokenTracker
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.GraphRequest
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import javax.inject.Inject

@ApplicationScope
class FacebookHelper @Inject constructor(context: Context) {

    private val applicationContext: Context = context.applicationContext

    private var _callbackManager: CallbackManager? = null
    private val callbackManager: CallbackManager
        get() {
            if (_callbackManager == null) {
                _callbackManager = CallbackManager.Factory.create()
            }
            return _callbackManager ?: throw AssertionError("Set to null by another thread")
        }

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) =
            callbackManager.onActivityResult(requestCode, resultCode, data)

    fun getLastSignedInAccount(listener: LoginListener) = AccessToken.getCurrentAccessToken()?.let {
        onTokenSuccess(it, listener)
        true
    } ?: false

    /**
     * This method is for the button that Facebook API provides.
     * This button already has the login and logout actions.
     */
    fun setFacebookOriginalButtonAction(
        loginButton: LoginButton,
        fragment: Fragment,
        loginListener: LoginListener,
        logoutListener: LogoutListener,
        permissions: Array<String> = arrayOf(PERMISSION_PUBLIC_PROFILE, PERMISSION_EMAIL)
    ) {
        loginButton.setReadPermissions(permissions.toList())
        loginButton.fragment = fragment
        loginButton.registerCallback(callbackManager, getFacebookCallback(loginListener))

        setLogoutListener(logoutListener)
    }

    /**
     * This method is to give the login action to a personal button or any clickable view.
     */
    fun setFacebookLoginAction(
        view: View,
        fragment: Fragment,
        listener: LoginListener,
        permissions: Array<String> = arrayOf(PERMISSION_PUBLIC_PROFILE, PERMISSION_EMAIL)
    ) {
        view.setOnClickListener {
            LoginManager.getInstance().logInWithReadPermissions(fragment, permissions.toList())
        }

        LoginManager.getInstance().registerCallback(callbackManager, getFacebookCallback(listener))
    }

    /**
     * This method is to give the logout action to a personal button or any clickable view.
     */
    fun setFacebookLogoutAction(view: View, listener: LogoutListener) {
        setLogoutListener(listener)

        view.setOnClickListener {
            LoginManager.getInstance().logOut()
        }
    }

    private fun setLogoutListener(listener: LogoutListener) {
        object : AccessTokenTracker() {
            override fun onCurrentAccessTokenChanged(accessToken: AccessToken?, accessToken2: AccessToken?) {
                if (accessToken2 == null) {
                    listener.onLogout()
                }
            }
        }
    }

    private fun onTokenSuccess(
        accessToken: AccessToken,
        listener: LoginListener,
        fields: Array<String> = arrayOf(FIELD_ID, FIELD_EMAIL, FIELD_NAME, FIELD_PICTURE)
    ) {
        val request = GraphRequest.newMeRequest(accessToken) { obj, _ ->
            listener.onLoginSuccess(FacebookAccount(obj))
        }
        val parameters = Bundle()
        parameters.putString(FIELDS, fields.joinToString(","))
        request.parameters = parameters
        request.executeAsync()
    }

    private fun getFacebookCallback(listener: LoginListener) =
            object : FacebookCallback<LoginResult> {
                override fun onSuccess(result: LoginResult) = onTokenSuccess(result.accessToken, listener)

                override fun onCancel() = listener.onLoginCancel()

                override fun onError(exception: FacebookException) = listener.onLoginError(exception)
            }

    /**
     * If you have troubles with the key hash, use this method to get the key hash that will work.
     */
    @SuppressLint("PackageManagerGetSignatures")
    fun logKeyHash(packageManager: PackageManager) {
        try {
            val info = packageManager.getPackageInfo(PACKAGE_NAME, PackageManager.GET_SIGNATURES)
            for (signature in info.signatures) {
                val md = MessageDigest.getInstance("SHA")
                md.update(signature.toByteArray())
                val sign = Base64.encodeToString(md.digest(), Base64.DEFAULT)
                Log.i(TAG, "My key hash is: $sign")
            }
        } catch (e: PackageManager.NameNotFoundException) {
            Log.e(TAG, "Name not found exception")
            e.printStackTrace()
        } catch (e: NoSuchAlgorithmException) {
            Log.e(TAG, "No such algorithm exception")
            e.printStackTrace()
        }
    }

    interface LoginListener {

        fun onLoginSuccess(account: FacebookAccount)

        fun onLoginCancel()

        fun onLoginError(exception: FacebookException)
    }

    interface LogoutListener {
        fun onLogout()
    }

    companion object {

        private const val PACKAGE_NAME = "ar.com.wolox.android.cookbook"
        private const val TAG = "FacebookHelper"

        private const val FIELDS = "fields"
        private const val FIELD_ID = "id"
        private const val FIELD_NAME = "name"
        private const val FIELD_EMAIL = "email"
        private const val FIELD_PICTURE = "picture.width(200)"

        private const val PERMISSION_PUBLIC_PROFILE = "public_profile"
        private const val PERMISSION_EMAIL = "email"
    }
}