package ar.com.wolox.android.cookbook.instagramlogin.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class InstagramResponse(
    @SerializedName("data") val data: List<InstagramDataItem>
) : Serializable