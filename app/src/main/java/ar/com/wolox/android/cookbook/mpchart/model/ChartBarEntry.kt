package ar.com.wolox.android.cookbook.mpchart.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ChartBarEntry(
    @SerializedName("xVal") val xVal: Float,
    @SerializedName("yVal") val yVal: Float
) : Serializable