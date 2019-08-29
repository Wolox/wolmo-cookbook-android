package ar.com.wolox.android.cookbook.mpchart.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ChartDataSample(
    @SerializedName("barChart_dataSet1") val barData1: ArrayList<ChartBarEntry>,
    @SerializedName("barChart_dataSet2") val barData2: ArrayList<ChartBarEntry>,
    @SerializedName("barChart_dataSet3") val barData3: ArrayList<ChartBarEntry>,
    @SerializedName("bubbleChart_dataSet1") val bubbleData: ArrayList<ChartBubbleEntry>,
    @SerializedName("combinedChart_dataSet1") val combinedData1: ArrayList<ChartBarEntry>,
    @SerializedName("combinedChart_dataSet2") val combinedData2: ArrayList<ChartEntry>,
    @SerializedName("horizontalBarChart_dataSet1") val hBarData: ArrayList<ChartBarEntry>,
    @SerializedName("lineChart_dataSet1") val lineData: ArrayList<ChartEntry>,
    @SerializedName("pieChart_dataSet1") val pieData: ArrayList<ChartPieEntry>,
    @SerializedName("radarChart_dataSet1") val radarData: ArrayList<ChartRadarEntry>,
    @SerializedName("candleStickChart_dataSet1") val candleData: ArrayList<ChartCandleEntry>,
    @SerializedName("scatterChart_dataSet1") val scatterData: ArrayList<ChartBarEntry>
) : Serializable