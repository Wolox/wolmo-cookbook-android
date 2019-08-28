package ar.com.wolox.android.cookbook.mpchart

import com.github.mikephil.charting.data.BarData

interface MpChartRecipeView {

    fun hideGraphs()
    fun showBarChart(barData: BarData)
    fun showBubbleChart()
    fun showCombinedChart()
    fun showHorizontalBarChart()
    fun showLineChart()
    fun showPieChart()
    fun showRadarChart()
    fun showCandleStickChart()
    fun showScatterChart()
}