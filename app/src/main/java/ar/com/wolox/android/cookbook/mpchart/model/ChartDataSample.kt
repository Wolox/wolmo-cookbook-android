package ar.com.wolox.android.cookbook.mpchart.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ChartDataSample(
    @SerializedName("barChart_label1") val barLabel1: String,
    @SerializedName("barChart_dataSet1") val barData1: ArrayList<ChartBarEntry>,

    @SerializedName("barChart_label2") val barLabel2: String,
    @SerializedName("barChart_dataSet2") val barData2: ArrayList<ChartBarEntry>,

    @SerializedName("barChart_label3") val barLabel3: String,
    @SerializedName("barChart_dataSet3") val barData3: ArrayList<ChartBarEntry>,

    @SerializedName("bubbleChart_label1") val bubbleLabel: String,
    @SerializedName("bubbleChart_dataSet1") val bubbleData: ArrayList<ChartBubbleEntry>,

    @SerializedName("combinedChart_label1") val combinedLabel1: String,
    @SerializedName("combinedChart_dataSet1") val combinedData1: ArrayList<ChartBarEntry>,

    @SerializedName("combinedChart_label2") val combinedLabel2: String,
    @SerializedName("combinedChart_dataSet2") val combinedData2: ArrayList<ChartEntry>,

    @SerializedName("horizontalBarChart_label1") val hBarLabel: String,
    @SerializedName("horizontalBarChart_dataSet1") val hBarData: ArrayList<ChartBarEntry>,

    @SerializedName("lineChart_label1") val lineLabel: String,
    @SerializedName("lineChart_dataSet1") val lineData: ArrayList<ChartEntry>,

    @SerializedName("pieChart_label1") val pieLabel: String,
    @SerializedName("pieChart_dataSet1") val pieData: ArrayList<ChartPieEntry>,

    @SerializedName("radarChart_label1") val radarLabel: String,
    @SerializedName("radarChart_dataSet1") val radarData: ArrayList<ChartRadarEntry>,

    @SerializedName("candleStickChart_label1") val candleLabel: String,
    @SerializedName("candleStickChart_dataSet1") val candleData: ArrayList<ChartCandleEntry>,

    @SerializedName("scatterChart_label1") val scatterLabel: String,
    @SerializedName("scatterChart_dataSet1") val scatterData: ArrayList<ChartBarEntry>
) : Serializable