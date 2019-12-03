package ar.com.wolox.android.cookbook.instagramlogin.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class InstagramImages(
    @SerializedName("thumbnail") val thumbnails: InstagramImageItem,
    @SerializedName("low_resolution") val lowImg: InstagramImageItem,
    @SerializedName("standard_resolution") val standardImg: InstagramImageItem
) : Serializable