package ar.com.wolox.android.cookbook.mpchart

import android.app.Application
import android.graphics.Color
import android.graphics.Paint
import androidx.core.content.ContextCompat
import ar.com.wolox.android.cookbook.R
import ar.com.wolox.android.cookbook.mpchart.model.ChartDataSample
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.BubbleData
import com.github.mikephil.charting.data.BubbleDataSet
import com.github.mikephil.charting.data.BubbleEntry
import com.github.mikephil.charting.data.CandleData
import com.github.mikephil.charting.data.CandleDataSet
import com.github.mikephil.charting.data.CandleEntry
import com.github.mikephil.charting.data.CombinedData
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.data.RadarData
import com.github.mikephil.charting.data.RadarDataSet
import com.github.mikephil.charting.data.RadarEntry
import com.github.mikephil.charting.data.ScatterData
import com.github.mikephil.charting.data.ScatterDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.github.mikephil.charting.utils.ColorTemplate
import com.google.gson.GsonBuilder
import javax.inject.Inject

class MpChartRecipePresenter @Inject constructor(
    val application: Application
) : BasePresenter<MpChartRecipeView>() {

    private var dataSample: ChartDataSample? = null

    fun onInit() {
        view.showProgressBar()
        dataSample = getSampleFromAssets()
        view.hideProgressBar()
    }

    private fun getSampleFromAssets(): ChartDataSample? {
        val inputStream = application.applicationContext.assets.open("MpAndroidChartDataExample.json")
        val size = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        val json = String(buffer)

        val gson = GsonBuilder().create()
        return gson.fromJson(json, ChartDataSample::class.java)
    }

    fun onSpinnerItemClicked(item: Int) {

        when (item) {
            0 -> clearScreen()
            1 -> onBarChartSelected()
            2 -> onBubbleChartSelected()
            3 -> onCombinedChartSelected()
            4 -> onHorizontalBarChartSelected()
            5 -> onLineChartSelected()
            6 -> onPieChartSelected()
            7 -> onRadarChartSelected()
            8 -> onCandleStickChartSelected()
            9 -> onScatterChartSelected()
            else -> clearScreen()
        }
    }

    private fun clearScreen() {
        view.hideGraphs()
    }

    private fun onBarChartSelected() {
        view.hideGraphs()

        val yBar1 = ArrayList<BarEntry>()
        val yBar2 = ArrayList<BarEntry>()
        val yBar3 = ArrayList<BarEntry>()

        var label1 = LABEL_DEFAULT
        var label2 = LABEL_DEFAULT
        var label3 = LABEL_DEFAULT

        dataSample?.let {
            label1 = it.barLabel1
            for (element in it.barData1) {
                yBar1.add(BarEntry(element.xVal, element.yVal, "HH-T1"))
            }

            label2 = it.barLabel2
            for (element in it.barData2) {
                yBar2.add(BarEntry(element.xVal, element.yVal, "HH-T2"))
            }

            label3 = it.barLabel3
            for (element in it.barData3) {
                yBar3.add(BarEntry(element.xVal, element.yVal, "HH-T3"))
            }
        }

        val barDataSet1 = BarDataSet(yBar1, label1)
        barDataSet1.color = Color.BLACK

        val barDataSet2 = BarDataSet(yBar2, label2)
        barDataSet2.color = Color.MAGENTA

        val barDataSet3 = BarDataSet(yBar3, label3)
        barDataSet3.color = Color.RED

        val data = BarData(barDataSet1, barDataSet2, barDataSet3)
        data.barWidth = BAR_WIDTH
        view.showBarChart(data)
    }

    private fun onBubbleChartSelected() {
        view.hideGraphs()

        val yBubble = ArrayList<BubbleEntry>()
        var label = LABEL_DEFAULT

        dataSample?.let {
            label = it.bubbleLabel
            for (element in it.bubbleData) {
                yBubble.add(BubbleEntry(element.xVal, element.yVal, element.size))
            }
        }

        val dataSet = BubbleDataSet(yBubble, label)
        val colorsArray = java.util.ArrayList<Int>()
        colorsArray.addAll(ColorTemplate.COLORFUL_COLORS.toList())

        dataSet.apply {
            colors = colorsArray
            valueTextSize = TEXT_SIZE_DEFAULT
        }

        val data = BubbleData(dataSet)

        view.showBubbleChart(data)
    }

    private fun onCombinedChartSelected() {
        view.hideGraphs()

        val yBar = ArrayList<BarEntry>()
        val yLine = ArrayList<Entry>()

        var labelBar = LABEL_DEFAULT
        var labelLine = LABEL_DEFAULT

        dataSample?.let {
            labelBar = it.combinedLabel1
            for (element in it.combinedData1) {
                yBar.add(BarEntry(element.xVal, element.yVal))
            }

            labelLine = it.combinedLabel2
            for (element in it.combinedData2) {
                yLine.add(Entry(element.xVal, element.yVal, element.data))
            }
        }

        val barDataSet = BarDataSet(yBar, labelBar)
        barDataSet.color = Color.GREEN

        val barData = BarData(barDataSet)
        barData.barWidth = BAR_WIDTH

        val lineDataSet = LineDataSet(yLine, labelLine)
        lineDataSet.apply {
            color = Color.BLUE
            setCircleColor(Color.BLUE)
            lineWidth = LINE_WIDTH
            circleRadius = RADIUS
            setDrawCircleHole(false)
            valueTextSize = TEXT_SIZE_MIN
            setDrawFilled(false)
        }

        val lineData = LineData(lineDataSet)

        val data = CombinedData()
        data.apply {
            setData(barData)
            setData(lineData)
        }

        view.showCombinedChart(data)
    }

    private fun onHorizontalBarChartSelected() {
        view.hideGraphs()

        val yBar = ArrayList<BarEntry>()
        var label = LABEL_DEFAULT

        dataSample?.let {
            label = it.hBarLabel
            for (element in it.hBarData) {
                yBar.add(BarEntry(element.xVal, element.yVal))
            }
        }

        val hLineDataSet = BarDataSet(yBar, label)
        hLineDataSet.color = Color.RED

        val data = BarData(hLineDataSet)
        data.barWidth = BAR_WIDTH

        view.showHorizontalBarChart(data)
    }

    private fun onLineChartSelected() {
        view.hideGraphs()

        val yLine = ArrayList<Entry>()
        var label = LABEL_DEFAULT

        dataSample?.let {
            label = it.lineLabel
            for (element in it.lineData) {
                yLine.add(Entry(element.xVal, element.yVal, element.data))
            }
        }

        val lineDataSet = LineDataSet(yLine, label)
        lineDataSet.apply {
            color = Color.BLUE
            setCircleColor(Color.BLUE)
            lineWidth = LINE_WIDTH
            circleRadius = RADIUS
            setDrawCircleHole(false)
            valueTextSize = TEXT_SIZE_MIN
            setDrawFilled(false)
        }

        val dataSets = ArrayList<ILineDataSet>()
        dataSets.add(lineDataSet)

        val data = LineData(dataSets)

        view.showLineChart(data)
    }

    private fun onPieChartSelected() {
        view.hideGraphs()

        val yPie = ArrayList<PieEntry>()
        var label = LABEL_DEFAULT

        dataSample?.let {
            label = it.pieLabel
            for (element in it.pieData) {
                yPie.add(PieEntry(element.value, element.label))
            }
        }

        val dataSet = PieDataSet(yPie, label)
        val colorsArray = java.util.ArrayList<Int>()
        colorsArray.addAll(ColorTemplate.COLORFUL_COLORS.toList())
        dataSet.apply {
            valueTextSize = TEXT_SIZE_MIN
            colors = colorsArray
        }

        val data = PieData(dataSet)

        view.showPieChart(data)
    }

    private fun onRadarChartSelected() {
        view.hideGraphs()

        val yRadar = ArrayList<RadarEntry>()
        var label = LABEL_DEFAULT

        dataSample?.let {
            label = it.radarLabel
            for (element in it.radarData) {
                yRadar.add(RadarEntry(element.value, element.data))
            }
        }

        val radarDataSet = RadarDataSet(yRadar, label)
        radarDataSet.color = Color.BLUE
        radarDataSet.valueTextSize = TEXT_SIZE_DEFAULT

        val data = RadarData(radarDataSet)

        radarDataSet.color = Color.BLUE
        radarDataSet.fillColor = Color.CYAN
        radarDataSet.setDrawFilled(true)
        radarDataSet.fillAlpha = 180

        view.showRadarChart(data)
    }

    private fun onCandleStickChartSelected() {
        view.hideGraphs()

        val yCandle = ArrayList<CandleEntry>()
        var label = LABEL_DEFAULT

        dataSample?.let {
            label = it.candleLabel
            for (element in it.candleData) {
                yCandle.add(CandleEntry(element.xVal, element.high, element.low, element.open, element.close))
            }
        }

        val candleDataSet = CandleDataSet(yCandle, label)
        candleDataSet.apply {
            setColors(ContextCompat.getColor(application.applicationContext, R.color.chart_candle_set_data))
            shadowColor = Color.DKGRAY
            shadowWidth = SHADOW_WIDTH
            decreasingColor = Color.RED
            decreasingPaintStyle = Paint.Style.FILL
            increasingColor = ContextCompat.getColor(application.applicationContext, R.color.chart_candle_increase)
            increasingPaintStyle = Paint.Style.FILL
            neutralColor = Color.BLUE
            valueTextColor = Color.RED
        }

        val data = CandleData(candleDataSet)

        view.showCandleStickChart(data)
    }

    private fun onScatterChartSelected() {
        view.hideGraphs()

        val yScatter = ArrayList<BarEntry>()
        var label = LABEL_DEFAULT

        dataSample?.let {
            label = it.scatterLabel
            for (element in it.scatterData) {
                yScatter.add(BarEntry(element.xVal, element.yVal))
            }
        }

        val scatterDataSet = ScatterDataSet(yScatter as List<Entry>?, label)
        scatterDataSet.apply {
            colors = ColorTemplate.COLORFUL_COLORS.toList()
            valueTextColor = Color.BLACK
            valueTextSize = TEXT_SIZE_DEFAULT
        }

        val data = ScatterData(scatterDataSet)

        view.showScatterChart(data)
    }

    companion object {
        private const val LABEL_DEFAULT = "Label"

        // private const val BAR_WIDTH = 0.16f
        private const val BAR_WIDTH = 0.2f
        private const val TEXT_SIZE_DEFAULT = 18f
        private const val LINE_WIDTH = 1f
        private const val RADIUS = 3f
        private const val TEXT_SIZE_MIN = 0f
        private const val SHADOW_WIDTH = 0.7f
    }
}