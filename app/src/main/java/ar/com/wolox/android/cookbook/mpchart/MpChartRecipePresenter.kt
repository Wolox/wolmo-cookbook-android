package ar.com.wolox.android.cookbook.mpchart

import android.graphics.Color
import android.graphics.Paint
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
import javax.inject.Inject

class MpChartRecipePresenter @Inject constructor() : BasePresenter<MpChartRecipeView>() {

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
        yBar1.apply {
            add(BarEntry(0f, 150f))
            add(BarEntry(1f, 160f))
            add(BarEntry(2f, 170f))
            add(BarEntry(3f, 200f))
            add(BarEntry(4f, 220f))
            add(BarEntry(5f, 222f))
            add(BarEntry(6f, 345f))
            add(BarEntry(7f, 367f))
            add(BarEntry(8f, 389f))
            add(BarEntry(9f, 555f))
        }

        val barDataSet1 = BarDataSet(yBar1, "L1")
        barDataSet1.color = Color.BLACK

        val yBar2 = ArrayList<BarEntry>()
        yBar2.apply {
            add(BarEntry(0f, 25f))
            add(BarEntry(1f, 16f))
            add(BarEntry(2f, 17f))
            add(BarEntry(3f, 20f))
            add(BarEntry(4f, 22f))
            add(BarEntry(5f, 22f))
            add(BarEntry(6f, 34f))
            add(BarEntry(7f, 36f))
            add(BarEntry(8f, 38f))
            add(BarEntry(9f, 55f))
        }

        val barDataSet2 = BarDataSet(yBar2, "L2")
        barDataSet2.color = Color.MAGENTA

        val yBar3 = ArrayList<BarEntry>()
        yBar3.apply {
            add(BarEntry(0f, 75f))
            add(BarEntry(1f, 200f))
            add(BarEntry(2f, 270f))
            add(BarEntry(3f, 100f))
            add(BarEntry(4f, 200f))
            add(BarEntry(5f, 122f))
            add(BarEntry(6f, 345f))
            add(BarEntry(7f, 367f))
            add(BarEntry(8f, 489f))
            add(BarEntry(9f, 455f))
        }

        val barDataSet3 = BarDataSet(yBar3, "L3")
        barDataSet3.color = Color.RED

        val data = BarData(barDataSet1, barDataSet2, barDataSet3)
        data.barWidth = BAR_WIDTH
        view.showBarChart(data)
    }

    private fun onBubbleChartSelected() {
        view.hideGraphs()

        val yBubble = ArrayList<BubbleEntry>()
        yBubble.apply {
            add(BubbleEntry(0f, 1f, 0.21f))
            add(BubbleEntry(1f, 2f, 0.12f))
            add(BubbleEntry(2f, 3f, 0.20f))
            add(BubbleEntry(2f, 4f, 0.52f))
            add(BubbleEntry(3f, 5f, 0.29f))
            add(BubbleEntry(4f, 6f, 0.62f))
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
        yBar.apply {
            add(BarEntry(0f, 50f))
            add(BarEntry(1f, 60f))
            add(BarEntry(2f, 70f))
            add(BarEntry(3f, 100f))
            add(BarEntry(4f, 120f))
            add(BarEntry(5f, 122f))
            add(BarEntry(6f, 155f))
            add(BarEntry(7f, 217f))
            add(BarEntry(8f, 239f))
            add(BarEntry(9f, 155f))
            add(BarEntry(10f, 120f))
            add(BarEntry(11f, 60f))
        }

        val barDataSet = BarDataSet(yBar, "L1")
        barDataSet.color = Color.GREEN

        val barData = BarData(barDataSet)
        barData.barWidth = BAR_WIDTH

        val yLine = ArrayList<Entry>()
        yLine.apply {
            add(Entry(0f, 30f, "0"))
            add(Entry(1f, 2f, "1"))
            add(Entry(2f, 4f, "2"))
            add(Entry(3f, 6f, "3"))
            add(Entry(4f, 8f, "4"))
            add(Entry(5f, 10f, "5"))
            add(Entry(6f, 22f, "6"))
            add(Entry(7f, 12.5f, "7"))
            add(Entry(8f, 22f, "8"))
            add(Entry(9f, 32f, "9"))
            add(Entry(10f, 54f, "10"))
            add(Entry(11f, 28f, "11"))
        }

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
        yBar.apply {
            add(BarEntry(0f, 75f))
            add(BarEntry(1f, 200f))
            add(BarEntry(2f, 270f))
            add(BarEntry(3f, 100f))
            add(BarEntry(4f, 200f))
            add(BarEntry(5f, 122f))
            add(BarEntry(6f, 345f))
            add(BarEntry(7f, 367f))
            add(BarEntry(8f, 489f))
            add(BarEntry(9f, 455f))
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
        yLine.apply {
            add(Entry(0f, 30f, "0"))
            add(Entry(1f, 2f, "1"))
            add(Entry(2f, 4f, "2"))
            add(Entry(3f, 6f, "3"))
            add(Entry(4f, 8f, "4"))
            add(Entry(5f, 10f, "5"))
            add(Entry(6f, 22f, "6"))
            add(Entry(7f, 12.5f, "7"))
            add(Entry(8f, 22f, "8"))
            add(Entry(9f, 32f, "9"))
            add(Entry(10f, 54f, "10"))
            add(Entry(11f, 28f, "11"))
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
        yPie.apply {
            add(PieEntry(30f, "L1"))
            add(PieEntry(2f, "L2"))
            add(PieEntry(4f, "L3"))
            add(PieEntry(22f, "L4"))
            add(PieEntry(12.5f, "L5"))
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
        yRadar.add(RadarEntry(0f, 0.41f))
        yRadar.add(RadarEntry(1f, 0.32f))
        yRadar.add(RadarEntry(2f, 0.40f))
        yRadar.add(RadarEntry(3f, 0.72f))
        yRadar.add(RadarEntry(4f, 0.49f))
        yRadar.add(RadarEntry(5f, 0.82f))

        val radarDataSet = RadarDataSet(yRadar, "L1")
        radarDataSet.color = Color.CYAN
        radarDataSet.valueTextSize = 18f

        val data = RadarData(radarDataSet)

        view.showRadarChart(data)
    }

    private fun onCandleStickChartSelected() {
        view.hideGraphs()

        val yCandle = ArrayList<CandleEntry>()
        yCandle.apply {
            add(CandleEntry(0f, 4.62f, 2.02f, 2.70f, 4.13f))
            add(CandleEntry(1f, 5.50f, 2.70f, 3.35f, 4.96f))
            add(CandleEntry(2f, 5.25f, 3.02f, 3.50f, 4.50f))
            add(CandleEntry(3f, 6f, 3.25f, 4.40f, 5.0f))
            add(CandleEntry(4f, 5.57f, 2f, 2.80f, 4.5f))
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
        yScatter.apply {
            add(BarEntry(2f, 0f))
            add(BarEntry(4f, 1f))
            add(BarEntry(6f, 1f))
            add(BarEntry(8f, 3f))
            add(BarEntry(7f, 4f))
            add(BarEntry(3f, 3f))
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