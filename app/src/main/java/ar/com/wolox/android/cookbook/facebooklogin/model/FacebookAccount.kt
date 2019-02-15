package ar.com.wolox.android.cookbook.facebooklogin.model

import android.net.Uri
import org.json.JSONObject

class FacebookAccount(jsonAccount: JSONObject) {
    val name = jsonAccount.optString("name") ?: ""
    val email = jsonAccount.optString("email") ?: ""
    val picture = {
        val url = jsonAccount.optJSONObject("picture")?.optJSONObject("data")?.optString("url")
        if (url != null) Uri.parse(url) else null
    }
}