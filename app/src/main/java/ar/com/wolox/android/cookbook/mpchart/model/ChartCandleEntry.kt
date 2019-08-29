package ar.com.wolox.android.cookbook.mpchart.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ChartCandleEntry(
    @SerializedName("xVal") val xVal: Float,
    @SerializedName("highValue") val high: Float,
    @SerializedName("lowValue") val low: Float,
    @SerializedName("openValue") val open: Float,
    @SerializedName("closeValue") val close: Float
) : Serializable