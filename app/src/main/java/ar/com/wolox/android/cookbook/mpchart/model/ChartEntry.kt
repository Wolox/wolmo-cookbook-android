package ar.com.wolox.android.cookbook.mpchart.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ChartEntry(
    @SerializedName("xVal") val xVal: Float,
    @SerializedName("yVal") val yVal: Float,
    @SerializedName("additionalData") val data: String
) : Serializable