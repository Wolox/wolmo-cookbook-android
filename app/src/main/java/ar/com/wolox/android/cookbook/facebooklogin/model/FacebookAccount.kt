package ar.com.wolox.android.cookbook.facebooklogin.model

import android.net.Uri
import org.json.JSONObject

class FacebookAccount(private val jsonAccount: JSONObject) {
    val name: String
        get() = jsonAccount.optString("name") ?: ""
    val email: String
        get() = jsonAccount.optString("email") ?: ""
    val picture: Uri?
        get() {
            val url = jsonAccount.optJSONObject("picture")?.optJSONObject("data")?.optString("url")
            return if (url != null) Uri.parse(url) else null
        }
}