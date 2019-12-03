package ar.com.wolox.android.cookbook.instagramlogin.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class InstagramFrom(
    @SerializedName("id") val id: String,
    @SerializedName("full_name") val fullName: String,
    @SerializedName("profile_picture") val picture: String,
    @SerializedName("username") val username: String
) : Serializable