package ar.com.wolox.android.cookbook.mpchart

import com.github.mikephil.charting.data.BarEntry

interface MpChartRecipeView {

    fun hideGraphs()
    fun showBarChart()
    fun showBubbleChart()
    fun showCombinedChart()
    fun showHorizontalBarChart()
    fun showLineChart()
    fun showPieChart()
    fun showRadarChart()
    fun showCandleStickChart()
    fun showScatterChart()
}