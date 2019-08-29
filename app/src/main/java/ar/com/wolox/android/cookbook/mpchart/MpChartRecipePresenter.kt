package ar.com.wolox.android.cookbook.mpchart

import android.app.Application
import android.graphics.Color
import android.graphics.Paint
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

    override fun onViewAttached() {
        super.onViewAttached()

        dataSample = getSampleFromAssets()
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

        dataSample?.let {
            for (element in it.barData1) {
                yBar1.add(BarEntry(element.xVal, element.yVal))
            }

            for (element in it.barData2) {
                yBar2.add(BarEntry(element.xVal, element.yVal))
            }

            for (element in it.barData3) {
                yBar3.add(BarEntry(element.xVal, element.yVal))
            }
        }

        val barDataSet1 = BarDataSet(yBar1, "L1")
        barDataSet1.color = Color.BLACK

        val barDataSet2 = BarDataSet(yBar2, "L2")
        barDataSet2.color = Color.MAGENTA

        val barDataSet3 = BarDataSet(yBar3, "L3")
        barDataSet3.color = Color.RED

        val data = BarData(barDataSet1, barDataSet2, barDataSet3)
        data.barWidth = BAR_WIDTH
        view.showBarChart(data)
    }

    private fun onBubbleChartSelected() {
        view.hideGraphs()

        val yBubble = ArrayList<BubbleEntry>()
        dataSample?.let {
            for (element in it.bubbleData) {
                yBubble.add(BubbleEntry(element.xVal, element.yVal, element.size))
            }
        }

        val dataSet = BubbleDataSet(yBubble, "L1")
        val colorsArray = java.util.ArrayList<Int>()
        colorsArray.addAll(ColorTemplate.COLORFUL_COLORS.toList())

        dataSet.apply {
            colors = colorsArray
            valueTextSize = 18f
        }

        val data = BubbleData(dataSet)

        view.showBubbleChart(data)
    }

    private fun onCombinedChartSelected() {
        view.hideGraphs()

        val yBar = ArrayList<BarEntry>()
        val yLine = ArrayList<Entry>()

        dataSample?.let {
            for (element in it.combinedData1) {
                yBar.add(BarEntry(element.xVal, element.yVal))
            }

            for (element in it.combinedData2) {
                yLine.add(Entry(element.xVal, element.yVal, element.data))
            }
        }

        val barDataSet = BarDataSet(yBar, "L1")
        barDataSet.color = Color.GREEN

        val barData = BarData(barDataSet)
        barData.barWidth = BAR_WIDTH

        val lineDataSet = LineDataSet(yLine, "L2")
        lineDataSet.apply {
            color = Color.BLUE
            setCircleColor(Color.BLUE)
            lineWidth = 1f
            circleRadius = 3f
            setDrawCircleHole(false)
            valueTextSize = 0f
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
        dataSample?.let {
            for (element in it.hBarData) {
                yBar.add(BarEntry(element.xVal, element.yVal))
            }
        }

        val hLineDataSet = BarDataSet(yBar, "L3")
        hLineDataSet.color = Color.RED

        val data = BarData(hLineDataSet)
        data.barWidth = BAR_WIDTH

        view.showHorizontalBarChart(data)
    }

    private fun onLineChartSelected() {
        view.hideGraphs()

        val yLine = ArrayList<Entry>()
        dataSample?.let {
            for (element in it.lineData) {
                yLine.add(Entry(element.xVal, element.yVal, element.data))
            }
        }

        val lineDataSet = LineDataSet(yLine, "L1")
        lineDataSet.apply {
            color = Color.BLUE
            setCircleColor(Color.BLUE)
            lineWidth = 1f
            circleRadius = 3f
            setDrawCircleHole(false)
            valueTextSize = 0f
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
        dataSample?.let {
            for (element in it.pieData) {
                yPie.add(PieEntry(element.value, element.label))
            }
        }

        val dataSet = PieDataSet(yPie, TITLE)
        val colorsArray = java.util.ArrayList<Int>()
        colorsArray.addAll(ColorTemplate.COLORFUL_COLORS.toList())
        dataSet.apply {
            valueTextSize = 0f
            colors = colorsArray
        }

        val data = PieData(dataSet)

        view.showPieChart(data)
    }

    private fun onRadarChartSelected() {
        view.hideGraphs()

        val yRadar = ArrayList<RadarEntry>()
        dataSample?.let {
            for (element in it.radarData) {
                yRadar.add(RadarEntry(element.value, element.data))
            }
        }

        val radarDataSet = RadarDataSet(yRadar, "L1")
        radarDataSet.color = Color.CYAN
        radarDataSet.valueTextSize = 18f

        val data = RadarData(radarDataSet)

        view.showRadarChart(data)
    }

    private fun onCandleStickChartSelected() {
        view.hideGraphs()

        val yCandle = ArrayList<CandleEntry>()
        dataSample?.let {
            for (element in it.candleData) {
                yCandle.add(CandleEntry(element.xVal, element.high, element.low, element.open, element.close))
            }
        }

        val candleDataSet = CandleDataSet(yCandle, "L1")
        candleDataSet.apply {
            setColors(Color.rgb(80, 80, 80))
            shadowColor = Color.DKGRAY
            shadowWidth = 0.7f
            decreasingColor = Color.RED
            decreasingPaintStyle = Paint.Style.FILL
            increasingColor = Color.rgb(122, 242, 84)
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
        dataSample?.let {
            for (element in it.scatterData) {
                yScatter.add(BarEntry(element.xVal, element.yVal))
            }
        }

        val scatterDataSet = ScatterDataSet(yScatter as List<Entry>?, "L1")
        scatterDataSet.apply {
            colors = ColorTemplate.COLORFUL_COLORS.toList()
            valueTextColor = Color.BLACK
            valueTextSize = 18f
        }

        val data = ScatterData(scatterDataSet)

        view.showScatterChart(data)
    }

    companion object {
        private const val TITLE = "TITLE OF DATA SET"
        private const val BAR_WIDTH = 0.16f
    }
}