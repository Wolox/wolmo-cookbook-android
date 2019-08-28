package ar.com.wolox.android.cookbook.mpchart

import android.app.Application
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.utils.ColorTemplate
import javax.inject.Inject

class MpChartRecipePresenter @Inject constructor(
        application: Application
) : BasePresenter<MpChartRecipeView>() {

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

        val yVals = ArrayList<BarEntry>()
        yVals.add(BarEntry(150f, 0f))
        yVals.add(BarEntry(160f, 1f))
        yVals.add(BarEntry(170f, 2f))
        yVals.add(BarEntry(200f, 3f))
        yVals.add(BarEntry(220f, 4f))
        yVals.add(BarEntry(222f, 5f))
        yVals.add(BarEntry(345f, 6f))
        yVals.add(BarEntry(367f, 7f))
        yVals.add(BarEntry(389f, 8f))
        yVals.add(BarEntry(555f, 9f))

        val dataSet = BarDataSet(yVals, "Title of DataSet")
        dataSet.valueTextSize = 0f
        val colors = java.util.ArrayList<Int>()
        colors.addAll(ColorTemplate.COLORFUL_COLORS.toList())
        dataSet.colors = colors
        val data = BarData(dataSet)
        data.barWidth = 10f
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
        view.showPieChart()
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
}