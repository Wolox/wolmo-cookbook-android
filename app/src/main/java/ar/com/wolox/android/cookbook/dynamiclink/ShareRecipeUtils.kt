package ar.com.wolox.android.cookbook.dynamiclink

import android.content.Intent
import android.net.Uri
import ar.com.wolox.essen.BuildConfig
import com.google.firebase.dynamiclinks.DynamicLink
import com.google.firebase.dynamiclinks.ktx.androidParameters
import com.google.firebase.dynamiclinks.ktx.dynamicLink
import com.google.firebase.dynamiclinks.ktx.dynamicLinks
import com.google.firebase.dynamiclinks.ktx.iosParameters
import com.google.firebase.dynamiclinks.ktx.socialMetaTagParameters
import com.google.firebase.ktx.Firebase

class ShareRecipeUtils {

    companion object {
        fun createRecipeLongLink(recipeId: Int, name: String, description1: String, imageURL1: String): DynamicLink {

            val link1 = Uri.Builder().scheme(urlScheme).authority(baseLink).appendPath(basePath).appendQueryParameter(recipeID, recipeId.toString()).build()
            val dynamicLinksPrefix = Uri.Builder().scheme(urlScheme).authority(DYNAMIC_LINKS_DOMAIN_URI).build()

            return Firebase.dynamicLinks.dynamicLink {
                link = link1
                domainUriPrefix = dynamicLinksPrefix.toString()

                androidParameters(BuildConfig.APPLICATION_ID) {
                    minimumVersion = ANDROID_MIN_VERSIONCODE
                }

                iosParameters(BuildConfig.IOS_BUNDLE_ID) {
                    appStoreId = IOS_APP_ID
                    minimumVersion = minimumAppVersion
                }

                socialMetaTagParameters {
                    title = name
                    description = description1
                    imageUrl = Uri.parse(imageURL1)
                }
            }
        }
        fun createIntentToSend(recipeLink: Uri): Intent {
            return Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, "$recipeLink")
            }
        }

        val urlScheme = "https"
        val baseLink = "www.essen.com.ar"
        val basePath = "/"
        val minimumAppVersion = "1.0.0"
        val recipeID = "recipe_id"

        val DYNAMIC_LINKS_DOMAIN_URI = "essentest.page.link"

        val IOS_APP_ID = "1448890186"

        val ANDROID_MIN_VERSIONCODE = 4
    }
}