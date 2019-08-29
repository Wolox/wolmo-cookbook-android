package ar.com.wolox.android.cookbook.mpchart.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ChartPieEntry(
        @SerializedName("value") val value: Float,
        @SerializedName("label") val label: String
) : Serializable