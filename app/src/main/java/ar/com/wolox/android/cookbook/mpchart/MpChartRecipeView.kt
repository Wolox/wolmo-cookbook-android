package ar.com.wolox.android.cookbook.mpchart

import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BubbleData
import com.github.mikephil.charting.data.CandleData
import com.github.mikephil.charting.data.CombinedData
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.RadarData
import com.github.mikephil.charting.data.ScatterData

interface MpChartRecipeView {

    fun hideGraphs()
    fun showBarChart(barData: BarData)
    fun showBubbleChart(bubbleData: BubbleData)
    fun showCombinedChart(combinedData: CombinedData)
    fun showHorizontalBarChart(hBarData: BarData)
    fun showLineChart(lineData: LineData)
    fun showPieChart(pieData: PieData)
    fun showRadarChart(radarData: RadarData)
    fun showCandleStickChart(candleData: CandleData)
    fun showScatterChart(scatterData: ScatterData)

    fun showProgressBar()
    fun hideProgressBar()
}