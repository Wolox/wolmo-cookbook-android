package ar.com.wolox.android.cookbook.mpchart

import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BubbleData
import com.github.mikephil.charting.data.CombinedData
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.PieData

interface MpChartRecipeView {

    fun hideGraphs()
    fun showBarChart(barData: BarData)
    fun showBubbleChart(bubbleData: BubbleData)
    fun showCombinedChart(combinedData: CombinedData)
    fun showHorizontalBarChart()
    fun showLineChart(lineData: LineData)
    fun showPieChart(pieData: PieData)
    fun showRadarChart()
    fun showCandleStickChart()
    fun showScatterChart()
}