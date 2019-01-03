package ar.com.wolox.android.cookbook.facebooklogin.model

import android.net.Uri
import org.json.JSONObject

class FacebookAccount(private val jsonAccount: JSONObject) {
    val name: String
        get() = (if (jsonAccount.has("name")) jsonAccount.getString("name") else "")
    val email: String
        get() = (if (jsonAccount.has("email")) jsonAccount.getString("email") else "")
    val picture
        get() = (if (jsonAccount.has("picture")) Uri.parse(jsonAccount.getString("picture")) else null)
}