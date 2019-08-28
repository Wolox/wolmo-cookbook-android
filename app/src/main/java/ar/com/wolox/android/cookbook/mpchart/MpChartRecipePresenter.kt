package ar.com.wolox.android.cookbook.mpchart

import android.graphics.Color
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.BubbleData
import com.github.mikephil.charting.data.BubbleDataSet
import com.github.mikephil.charting.data.BubbleEntry
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

        val yVals1 = ArrayList<BarEntry>()
        yVals1.add(BarEntry(0f, 150f))
        yVals1.add(BarEntry(1f, 160f))
        yVals1.add(BarEntry(2f, 170f))
        yVals1.add(BarEntry(3f, 200f))
        yVals1.add(BarEntry(4f, 220f))
        yVals1.add(BarEntry(5f, 222f))
        yVals1.add(BarEntry(6f, 345f))
        yVals1.add(BarEntry(7f, 367f))
        yVals1.add(BarEntry(8f, 389f))
        yVals1.add(BarEntry(9f, 555f))

        val dataSet1 = BarDataSet(yVals1, "L1")
        dataSet1.color = Color.BLACK

        val yVals2 = ArrayList<BarEntry>()
        yVals2.add(BarEntry(0f, 25f))
        yVals2.add(BarEntry(1f, 16f))
        yVals2.add(BarEntry(2f, 17f))
        yVals2.add(BarEntry(3f, 20f))
        yVals2.add(BarEntry(4f, 22f))
        yVals2.add(BarEntry(5f, 22f))
        yVals2.add(BarEntry(6f, 34f))
        yVals2.add(BarEntry(7f, 36f))
        yVals2.add(BarEntry(8f, 38f))
        yVals2.add(BarEntry(9f, 55f))

        val dataSet2 = BarDataSet(yVals2, "L2")
        dataSet2.color = Color.MAGENTA

        val yVals3 = ArrayList<BarEntry>()
        yVals3.add(BarEntry(0f, 75f))
        yVals3.add(BarEntry(1f, 200f))
        yVals3.add(BarEntry(2f, 270f))
        yVals3.add(BarEntry(3f, 100f))
        yVals3.add(BarEntry(4f, 200f))
        yVals3.add(BarEntry(5f, 122f))
        yVals3.add(BarEntry(6f, 345f))
        yVals3.add(BarEntry(7f, 367f))
        yVals3.add(BarEntry(8f, 489f))
        yVals3.add(BarEntry(9f, 455f))

        val dataSet3 = BarDataSet(yVals3, "L3")
        dataSet3.color = Color.RED

        val data = BarData(dataSet1, dataSet2, dataSet3)
        data.barWidth = 0.16f
        view.showBarChart(data)
    }

    private fun onBubbleChartSelected() {
        view.hideGraphs()

        val bubbleEntries = ArrayList<BubbleEntry>()
        bubbleEntries.add(BubbleEntry(0f, 1f, 0.21f))
        bubbleEntries.add(BubbleEntry(1f, 2f, 0.12f))
        bubbleEntries.add(BubbleEntry(2f, 3f, 0.20f))
        bubbleEntries.add(BubbleEntry(2f, 4f, 0.52f))
        bubbleEntries.add(BubbleEntry(3f, 5f, 0.29f))
        bubbleEntries.add(BubbleEntry(4f, 6f, 0.62f))

        val dataSet = BubbleDataSet(bubbleEntries, "L1")
        val colors = java.util.ArrayList<Int>()
        colors.addAll(ColorTemplate.COLORFUL_COLORS.toList())
        dataSet.colors = colors
        dataSet.valueTextSize = 18f

        val bubbleData = BubbleData(dataSet)

        view.showBubbleChart(bubbleData)
    }

    private fun onCombinedChartSelected() {
        view.hideGraphs()

        val yBar = ArrayList<BarEntry>()
        yBar.add(BarEntry(0f, 50f))
        yBar.add(BarEntry(1f, 60f))
        yBar.add(BarEntry(2f, 70f))
        yBar.add(BarEntry(3f, 100f))
        yBar.add(BarEntry(4f, 120f))
        yBar.add(BarEntry(5f, 122f))
        yBar.add(BarEntry(6f, 155f))
        yBar.add(BarEntry(7f, 217f))
        yBar.add(BarEntry(8f, 239f))
        yBar.add(BarEntry(9f, 155f))

        val barDataSet = BarDataSet(yBar, "L1")
        barDataSet.color = Color.GREEN

        val barData = BarData(barDataSet)
        barData.barWidth = 0.16f

        val yLine = ArrayList<Entry>()
        yLine.add(Entry(0f, 30f, "0"))
        yLine.add(Entry(1f, 2f, "1"))
        yLine.add(Entry(2f, 4f, "2"))
        yLine.add(Entry(3f, 6f, "3"))
        yLine.add(Entry(4f, 8f, "4"))
        yLine.add(Entry(5f, 10f, "5"))
        yLine.add(Entry(6f, 22f, "6"))
        yLine.add(Entry(7f, 12.5f, "7"))
        yLine.add(Entry(8f, 22f, "8"))
        yLine.add(Entry(9f, 32f, "9"))
        yLine.add(Entry(10f, 54f, "10"))
        yLine.add(Entry(11f, 28f, "11"))

        val lineDataSet = LineDataSet(yLine, "L1")
        lineDataSet.color = Color.BLUE
        lineDataSet.setCircleColor(Color.BLUE)
        lineDataSet.lineWidth = 1f
        lineDataSet.circleRadius = 3f
        lineDataSet.setDrawCircleHole(false)
        lineDataSet.valueTextSize = 0f
        lineDataSet.setDrawFilled(false)

        val lineData = LineData(lineDataSet)

        val data = CombinedData()
        data.setData(barData)
        data.setData(lineData)

        view.showCombinedChart(data)
    }

    private fun onHorizontalBarChartSelected() {
        view.hideGraphs()

        val yBar = ArrayList<BarEntry>()
        yBar.add(BarEntry(0f, 75f))
        yBar.add(BarEntry(1f, 200f))
        yBar.add(BarEntry(2f, 270f))
        yBar.add(BarEntry(3f, 100f))
        yBar.add(BarEntry(4f, 200f))
        yBar.add(BarEntry(5f, 122f))
        yBar.add(BarEntry(6f, 345f))
        yBar.add(BarEntry(7f, 367f))
        yBar.add(BarEntry(8f, 489f))
        yBar.add(BarEntry(9f, 455f))

        val lineDataSet = BarDataSet(yBar, "L3")
        lineDataSet.color = Color.RED

        val data = BarData(lineDataSet)
        data.barWidth = 0.16f

        view.showHorizontalBarChart(data)
    }

    private fun onLineChartSelected() {
        view.hideGraphs()

        val yVals = ArrayList<Entry>()
        yVals.add(Entry(0f, 30f, "0"))
        yVals.add(Entry(1f, 2f, "1"))
        yVals.add(Entry(2f, 4f, "2"))
        yVals.add(Entry(3f, 6f, "3"))
        yVals.add(Entry(4f, 8f, "4"))
        yVals.add(Entry(5f, 10f, "5"))
        yVals.add(Entry(6f, 22f, "6"))
        yVals.add(Entry(7f, 12.5f, "7"))
        yVals.add(Entry(8f, 22f, "8"))
        yVals.add(Entry(9f, 32f, "9"))
        yVals.add(Entry(10f, 54f, "10"))
        yVals.add(Entry(11f, 28f, "11"))

        val set1 = LineDataSet(yVals, "L1")
        set1.color = Color.BLUE
        set1.setCircleColor(Color.BLUE)
        set1.lineWidth = 1f
        set1.circleRadius = 3f
        set1.setDrawCircleHole(false)
        set1.valueTextSize = 0f
        set1.setDrawFilled(false)

        val dataSets = ArrayList<ILineDataSet>()
        dataSets.add(set1)
        val data = LineData(dataSets)

        view.showLineChart(data)
    }

    private fun onPieChartSelected() {
        view.hideGraphs()

        val yVals = ArrayList<PieEntry>()
        yVals.add(PieEntry(30f, "L1"))
        yVals.add(PieEntry(2f, "L2"))
        yVals.add(PieEntry(4f, "L3"))
        yVals.add(PieEntry(22f, "L4"))
        yVals.add(PieEntry(12.5f, "L5"))

        val dataSet = PieDataSet(yVals, TITLE)
        dataSet.valueTextSize = 0f
        val colors = java.util.ArrayList<Int>()
        colors.addAll(ColorTemplate.COLORFUL_COLORS.toList())
        dataSet.colors = colors
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
        view.showCandleStickChart()
    }

    private fun onScatterChartSelected() {
        view.hideGraphs()
        view.showScatterChart()
    }

    companion object {
        private const val TITLE = "TITLE OF DATA SET"
    }
}