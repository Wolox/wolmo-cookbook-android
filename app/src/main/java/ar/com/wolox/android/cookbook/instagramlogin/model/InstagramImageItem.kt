package ar.com.wolox.android.cookbook.instagramlogin.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class InstagramImageItem(
    @SerializedName("width") val width: Int,
    @SerializedName("height") val height: Int,
    @SerializedName("url") val url: String
) : Serializable