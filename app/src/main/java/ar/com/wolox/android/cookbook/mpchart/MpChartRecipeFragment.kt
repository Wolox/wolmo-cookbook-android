package ar.com.wolox.android.cookbook.mpchart

import android.app.ProgressDialog
import android.view.View
import android.widget.AdapterView
import ar.com.wolox.android.cookbook.R
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BubbleData
import com.github.mikephil.charting.data.CandleData
import com.github.mikephil.charting.data.CombinedData
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.RadarData
import com.github.mikephil.charting.data.ScatterData
import kotlinx.android.synthetic.main.fragment_mp_chart.*

class MpChartRecipeFragment : WolmoFragment<MpChartRecipePresenter>(), MpChartRecipeView {

    private lateinit var progressDialog: ProgressDialog

    override fun layout(): Int = R.layout.fragment_mp_chart

    override fun init() {
        hideGraphs()
        progressDialog = ProgressDialog(context)
        presenter.onInit()
    }

    override fun setListeners() {
        super.setListeners()

        vSpinnerChart.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
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

        vBarChart.apply {
            visibility = View.VISIBLE
            data = barData
            animateY(ANIMATION_DELAY)
            isDragEnabled = true

            axisLeft.mAxisMinimum = MINIMUM

            xAxis.apply {
                mAxisMinimum = MINIMUM
                position = XAxis.XAxisPosition.BOTTOM
                setCenterAxisLabels(true)
            }

            description.text = getString(R.string.mp_chart_bar)
            groupBars(MINIMUM, GROUP_SPACE, BAR_SPACE)

            setVisibleXRangeMaximum(BAR_VISIBLE_RANGE)
            invalidate()
        }
    }

    override fun showBubbleChart(bubbleData: BubbleData) {

        vBubbleChart.apply {
            visibility = View.VISIBLE
            data = bubbleData
            isDragEnabled = true
            description.text = getString(R.string.mp_chart_bubble)
            animateXY(ANIMATION_DELAY, ANIMATION_DELAY)
        }
    }

    override fun showCombinedChart(combinedData: CombinedData) {

        vCombinedChart.apply {
            visibility = View.VISIBLE
            data = combinedData

            setDrawBarShadow(false)
            description.text = getString(R.string.mp_chart_combined)
            setDrawGridBackground(false)
            isHighlightFullBarEnabled = false

            axisRight.apply {
                setDrawGridLines(false)
                axisMinimum = MINIMUM
            }

            axisLeft.apply {
                setDrawGridLines(false)
                axisMinimum = MINIMUM
            }

            xAxis.apply {
                position = XAxis.XAxisPosition.BOTH_SIDED
                axisMinimum = MINIMUM
                granularity = GRANULARITY
                axisMaximum = data.xMax + AXIS_PHASE
            }

            animateXY(ANIMATION_DELAY, ANIMATION_DELAY)
            invalidate()
        }
    }

    override fun showHorizontalBarChart(hBarData: BarData) {

        vHorizontalBarChart.apply {
            visibility = View.VISIBLE
            data = hBarData
            animateY(ANIMATION_DELAY)
            isDragEnabled = true

            axisLeft.mAxisMinimum = MINIMUM

            xAxis.apply {
                mAxisMinimum = MINIMUM
                position = XAxis.XAxisPosition.BOTTOM
                setCenterAxisLabels(true)
            }

            description.text = getString(R.string.mp_chart_h_bar)
            invalidate()
        }
    }

    override fun showLineChart(lineData: LineData) {

        vLineChart.apply {
            visibility = View.VISIBLE

            data = lineData
            description.isEnabled = false
            legend.isEnabled = false
            setPinchZoom(true)

            animateX(ANIMATION_DELAY)

            xAxis.apply {
                enableGridDashedLine(DEFAULT_LENGTH, DEFAULT_LENGTH, MINIMUM)
                labelCount = lineData.dataSetCount
                position = XAxis.XAxisPosition.BOTTOM
            }

            axisRight.enableGridDashedLine(DEFAULT_LENGTH, DEFAULT_LENGTH, MINIMUM)
            axisLeft.enableGridDashedLine(DEFAULT_LENGTH, DEFAULT_LENGTH, MINIMUM)
        }
    }

    override fun showPieChart(pieData: PieData) {
        vPieChart.visibility = View.VISIBLE

        vPieChart.apply {
            data = pieData
            centerTextRadiusPercent = RADIUS_PERCENT
            animateXY(ANIMATION_DELAY, ANIMATION_DELAY)
            isDrawHoleEnabled = false
            description.text = getString(R.string.mp_chart_pie)
        }
    }

    override fun showRadarChart(radarData: RadarData) {

        vRadarChart.apply {
            visibility = View.VISIBLE
            data = radarData
            description.text = getString(R.string.mp_chart_radar)
            animateXY(ANIMATION_DELAY, ANIMATION_DELAY)
        }
    }

    override fun showCandleStickChart(candleData: CandleData) {
        vCandleStickChart.apply {
            visibility = View.VISIBLE
            data = candleData
            description.text = getString(R.string.mp_chart_candle)
            animateXY(ANIMATION_DELAY, ANIMATION_DELAY)
            invalidate()
        }
    }

    override fun showScatterChart(scatterData: ScatterData) {
        vScatterChart.apply {
            visibility = View.VISIBLE
            data = scatterData
            description.text = getString(R.string.mp_chart_scatter)
            animateXY(ANIMATION_DELAY, ANIMATION_DELAY)
            invalidate()
        }
    }

    override fun showProgressBar() {
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER)
        progressDialog.setCancelable(false)
        progressDialog.setCanceledOnTouchOutside(false)
        progressDialog.setMessage(getString(R.string.mp_chart_p_dialog))
        progressDialog.show()
    }

    override fun hideProgressBar() {
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER)
        progressDialog.dismiss()
    }

    companion object {
        private const val ANIMATION_DELAY = 2500
        private const val MINIMUM = 0f
        private const val RADIUS_PERCENT = 1f
        private const val DEFAULT_LENGTH = 5f
        private const val GRANULARITY = 1f
        private const val AXIS_PHASE = 0.25f
        private const val BAR_VISIBLE_RANGE = 3f
        private const val GROUP_SPACE = 0.30f
        private const val BAR_SPACE = 0.05f
    }
}