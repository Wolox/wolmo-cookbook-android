package ar.com.wolox.android.cookbook.instagramlogin.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class InstagramDataItem(
    @SerializedName("id") val id: String,
    @SerializedName("images") val images: InstagramImages,
    @SerializedName("caption") val caption: InstagramCaption
) : Serializable