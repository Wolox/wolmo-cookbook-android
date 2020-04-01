package ar.com.wolox.android.cookbook.instagramlogin.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class InstagramCaption(
    @SerializedName("id") val id: String,
    @SerializedName("text") val text: String,
    @SerializedName("from") val from: InstagramFrom
) : Serializable