package ar.com.wolox.android.cookbook.mpchart

import android.util.Log
import android.view.View
import android.widget.AdapterView
import ar.com.wolox.android.cookbook.R
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BubbleData
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.PieData
import kotlinx.android.synthetic.main.fragment_mp_chart.*

class MpChartRecipeFragment : WolmoFragment<MpChartRecipePresenter>(), MpChartRecipeView {

    override fun layout(): Int = R.layout.fragment_mp_chart

    override fun init() {
        hideGraphs()
    }

    override fun setListeners() {
        super.setListeners()

        vSpinnerChart.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val label = resources.getStringArray(R.array.mp_chart_types)[p2]
                Log.e("FedeLog", "OnItemSelected: $label")
                presenter.onSpinnerItemClicked(p2)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }
    }

    override fun hideGraphs() {
        vBarChart.visibility = View.GONE
        vBubbleChart.visibility = View.GONE
        vCombinedChart.visibility = View.GONE
        vHorizontalBarChart.visibility = View.GONE
        vLineChart.visibility = View.GONE
        vPieChart.visibility = View.GONE
        vRadarChart.visibility = View.GONE
        vCandleStickChart.visibility = View.GONE
        vScatterChart.visibility = View.GONE
    }

    override fun showBarChart(barData: BarData) {

        val groupSpace = 0.30f
        val barSpace = 0.05f

        vBarChart.apply {
            visibility = View.VISIBLE
            data = barData
            animateY(ANIMATION_DELAY)
            isDragEnabled = true

            axisLeft.mAxisMinimum = 0f

            xAxis.apply {
                mAxisMinimum = 0f
                position = XAxis.XAxisPosition.BOTTOM
                setCenterAxisLabels(true)
            }

            description.text = getString(R.string.mp_chart_bar)
            groupBars(0f, groupSpace, barSpace)

            setVisibleXRangeMaximum(3f)
            invalidate()
        }
    }

    override fun showBubbleChart(bubbleData: BubbleData) {

        vBubbleChart.apply {
            visibility = View.VISIBLE
            data = bubbleData
            isDragEnabled = true
            description.text = getString(R.string.mp_chart_bubble)
        }
    }

    override fun showCombinedChart() {
        vCombinedChart.visibility = View.VISIBLE
    }

    override fun showHorizontalBarChart() {
        vHorizontalBarChart.visibility = View.VISIBLE
    }

    override fun showLineChart(lineData: LineData) {

        val defaultLength = 5f
        val phase = 0f

        vLineChart.apply {
            visibility = View.VISIBLE

            data = lineData
            description.isEnabled = false
            legend.isEnabled = false
            setPinchZoom(true)

            animateX(ANIMATION_DELAY)

            xAxis.apply {
                enableGridDashedLine(defaultLength, defaultLength, phase)
                labelCount = lineData.dataSetCount
                position = XAxis.XAxisPosition.BOTTOM
            }

            axisRight.enableGridDashedLine(defaultLength, defaultLength, phase)
            axisLeft.enableGridDashedLine(defaultLength, defaultLength, phase)
        }
    }

    override fun showPieChart(pieData: PieData) {
        vPieChart.visibility = View.VISIBLE

        vPieChart.apply {
            data = pieData
            centerTextRadiusPercent = 1f
            animateXY(ANIMATION_DELAY, ANIMATION_DELAY)
            isDrawHoleEnabled = false
            description.text = getString(R.string.mp_chart_pie)
        }
    }

    override fun showRadarChart() {
        vRadarChart.visibility = View.VISIBLE
    }

    override fun showCandleStickChart() {
        vCandleStickChart.visibility = View.VISIBLE
    }

    override fun showScatterChart() {
        vScatterChart.visibility = View.VISIBLE
    }

    companion object {
        private const val ANIMATION_DELAY = 3000
    }
}