package ar.com.wolox.android.cookbook.facebooklogin.model

import android.net.Uri
import org.json.JSONObject

class FacebookAccount(jsonAccount: JSONObject) {
    val name = jsonAccount.optString("name") ?: ""
    val email = jsonAccount.optString("email") ?: ""
    val picture = jsonAccount.optJSONObject("picture")?.optJSONObject("data")?.optString("url")?.let {
        Uri.parse(it)
    }
}