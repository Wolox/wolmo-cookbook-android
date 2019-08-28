package ar.com.wolox.android.cookbook.mpchart

import android.graphics.Color
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
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
        view.showBubbleChart()
    }

    private fun onCombinedChartSelected() {
        view.hideGraphs()
        view.showCombinedChart()
    }

    private fun onHorizontalBarChartSelected() {
        view.hideGraphs()
        view.showHorizontalBarChart()
    }

    private fun onLineChartSelected() {
        view.hideGraphs()
        view.showLineChart()
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
        view.showRadarChart()
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