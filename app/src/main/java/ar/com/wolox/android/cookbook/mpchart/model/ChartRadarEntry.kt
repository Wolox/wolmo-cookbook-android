package ar.com.wolox.android.cookbook.mpchart.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ChartRadarEntry(
        @SerializedName("value") val value: Float,
        @SerializedName("data") val data: Float
) : Serializable