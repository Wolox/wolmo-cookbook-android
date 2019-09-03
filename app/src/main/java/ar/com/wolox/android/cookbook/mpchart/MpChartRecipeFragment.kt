package ar.com.wolox.android.cookbook.mpchart

import android.app.ProgressDialog
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import ar.com.wolox.android.cookbook.R
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.BubbleData
import com.github.mikephil.charting.data.BubbleEntry
import com.github.mikephil.charting.data.CandleData
import com.github.mikephil.charting.data.CombinedData
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.data.RadarData
import com.github.mikephil.charting.data.ScatterData
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import kotlinx.android.synthetic.main.fragment_mp_chart.*

/**
 * Note: Many of the chart have the same parameters of configuration, specific parameters are defined
 * in each method.
 *
 * Parameters:
 * data (mandatory): data to display in chart
 *
 * animations -> Animates the rendering of the chart on the x/y-axis with the specified animation time
 * animateX
 * animateY
 * animateXY
 *
 * isDragEnabled: Enables dragging (moving chart with the finger) for chart bigger than the screen
 *
 * setOnChartValueSelectedListener(): set a listener for the chart, this returns an Entry object
 * (Warning: in combined chart all types of charts return the same Entry type on item click)
 *
 * xAxis/yAxis: set position of labels, margins, etc...
 *
 * axisRight/axisLeft: Returns the _-axis object
 *
 * description.text: text with a short description of the chart (in the right bottom of the screen)
 */
class MpChartRecipeFragment : WolmoFragment<MpChartRecipePresenter>(), MpChartRecipeView {

    private lateinit var progressDialog: ProgressDialog

    override fun layout(): Int = R.layout.fragment_mp_chart

    override fun init() {
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

            setOnChartValueSelectedListener(object : OnChartValueSelectedListener {
                override fun onValueSelected(e: Entry?, h: Highlight?) {
                    val entry = e as BarEntry

                    /**
                     * Data is an additional information of Entry, represents an Object with
                     * any class or null if no data has been specified
                     */
                    Toast.makeText(context, getString(R.string.mp_chart_item_bar, entry.y, entry.data),
                            Toast.LENGTH_SHORT).show()
                }

                override fun onNothingSelected() {
                }
            })

            description.text = getString(R.string.mp_chart_bar)

            /**
             * Note: groupBars add a withe space between bars. Only works if exists more than 1
             * dataSet and its require a separation between bars. To define space is mandatory follow
             * the next condition -> (BarWidth  + BarSpace) * NumberDataSet + GroupSpace = 1
             */
            val groupSpace = UNIT - ((barData.barWidth + BAR_SPACE) * barData.dataSetCount)
            groupBars(ZERO, groupSpace, BAR_SPACE)

            /**
             * xAxisMaximum for multiple dataSet (to fit in chart) need to follow the next condition
             * -> XMax + (NumberBars * (BarWidth + BarSpace)) + AdditionalSpace
             *
             * XMax = maximum x-value this data object contains
             * NumberBars = number of LineDataSets
             * BarWidth = width each bar should have
             * BarSpace = space between bars of each dataSet
             * AdditionalSpace = space between bars of the same dataSet
             */
            xAxis.apply {
                axisMinimum = ZERO
                axisMaximum = barData.xMax + (((barData.barWidth + BAR_SPACE) * barData.dataSetCount) + groupSpace)
                position = XAxis.XAxisPosition.BOTTOM
                setCenterAxisLabels(true)
            }

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

            setOnChartValueSelectedListener(object : OnChartValueSelectedListener {
                override fun onValueSelected(e: Entry?, h: Highlight?) {
                    val entry = e as BubbleEntry

                    /**
                     * Data is an additional information of Entry, represents an Object with
                     * any class or null if no data has been specified
                     */
                    Toast.makeText(context, getString(R.string.mp_chart_item_bubble, entry.x, entry.y,
                            entry.size, entry.data), Toast.LENGTH_SHORT).show()
                }

                override fun onNothingSelected() {
                }
            })
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
                axisMinimum = ZERO
            }

            axisLeft.apply {
                setDrawGridLines(false)
                axisMinimum = ZERO
            }

            xAxis.apply {
                position = XAxis.XAxisPosition.BOTH_SIDED
                axisMinimum = ZERO
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

            axisLeft.mAxisMinimum = ZERO

            setOnChartValueSelectedListener(object : OnChartValueSelectedListener {
                override fun onValueSelected(e: Entry?, h: Highlight?) {
                    val entry = e as BarEntry

                    /**
                     * Data is an additional information of Entry, represents an Object with
                     * any class or null if no data has been specified
                     */
                    Toast.makeText(context, getString(R.string.mp_chart_item_bar, entry.y, entry.data),
                            Toast.LENGTH_SHORT).show()
                }

                override fun onNothingSelected() {
                }
            })

            xAxis.apply {
                mAxisMinimum = ZERO
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
                enableGridDashedLine(DEFAULT_LENGTH, DEFAULT_LENGTH, ZERO)
                labelCount = lineData.dataSetCount
                position = XAxis.XAxisPosition.BOTTOM
            }

            axisRight.enableGridDashedLine(DEFAULT_LENGTH, DEFAULT_LENGTH, ZERO)
            axisLeft.enableGridDashedLine(DEFAULT_LENGTH, DEFAULT_LENGTH, ZERO)
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

            setOnChartValueSelectedListener(object : OnChartValueSelectedListener {
                override fun onValueSelected(e: Entry?, h: Highlight?) {
                    val entry = e as PieEntry
                    Toast.makeText(context, getString(R.string.mp_chart_item_pie, entry.label, entry.value),
                            Toast.LENGTH_SHORT).show()
                }

                override fun onNothingSelected() {
                }
            })
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

            setOnChartValueSelectedListener(object : OnChartValueSelectedListener {
                override fun onValueSelected(e: Entry?, h: Highlight?) {
                    val entry = e as BarEntry

                    /**
                     * Data is an additional information of Entry, represents an Object with
                     * any class or null if no data has been specified
                     */
                    Toast.makeText(context, getString(R.string.mp_chart_item_bar, entry.y, entry.data),
                            Toast.LENGTH_SHORT).show()
                }

                override fun onNothingSelected() {
                }
            })

            animateXY(ANIMATION_DELAY, ANIMATION_DELAY)
            invalidate()
        }
    }

    override fun showProgressBar() {
        progressDialog.apply {
            setProgressStyle(ProgressDialog.STYLE_SPINNER)
            setCancelable(false)
            setCanceledOnTouchOutside(false)
            setMessage(getString(R.string.mp_chart_p_dialog))
            show()
        }
    }

    override fun hideProgressBar() {
        progressDialog.apply {
            setProgressStyle(ProgressDialog.STYLE_SPINNER)
            dismiss()
        }
    }

    companion object {
        private const val ANIMATION_DELAY = 2500
        private const val ZERO = 0f
        private const val RADIUS_PERCENT = 1f
        private const val DEFAULT_LENGTH = 5f
        private const val GRANULARITY = 1f
        private const val AXIS_PHASE = 0.25f
        private const val BAR_VISIBLE_RANGE = 3f

        private const val BAR_SPACE = 0.0f
        private const val UNIT = 1f
    }
}