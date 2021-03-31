package ar.com.wolox.android.cookbook.dynamiclink

import android.net.Uri
import android.os.Bundle
import android.util.Log
import ar.com.wolox.essen.R
import ar.com.wolox.essen.common.activity.EssenActivity
import ar.com.wolox.essen.welcome.splash.SplashFragment
import com.google.firebase.dynamiclinks.ktx.dynamicLinks
import com.google.firebase.ktx.Firebase
import javax.inject.Inject

class RouterActivity @Inject constructor() : EssenActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)

        getDynamicLink()
    }

    private fun getDynamicLink() {
        Firebase.dynamicLinks
                .getDynamicLink(intent)
                .addOnSuccessListener(this) { pendingDynamicLinkData ->
                    var deepLink: Uri? = null
                    if (pendingDynamicLinkData != null) {
                        deepLink = pendingDynamicLinkData.link
                    }

                    handleDeepLink(deepLink.toString())
                }
                .addOnFailureListener(this) { e -> Log.w("TAG", "getDynamicLink:onFailure", e) }
    }

    private fun handleDeepLink(deepLink: String?) {
        val deepUri = Uri.parse(deepLink)
        val paramValue = deepUri.getQueryParameter("recipe_id")

        if (paramValue != null) {
            replaceFragment(R.id.vActivityBaseContent, RouterFragment.newInstance(paramValue))
        }
    }

    override fun layout(): Int = R.layout.activity_base

    override fun init() {
        replaceFragment(R.id.vActivityBaseContent, SplashFragment())
    }
}