package ar.com.wolox.android.cookbook.mpchart

import android.app.ProgressDialog
import android.view.View
import android.widget.AdapterView
import ar.com.wolox.android.cookbook.R
import ar.com.wolox.android.cookbook.databinding.FragmentMpChartBinding
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import ar.com.wolox.wolmo.core.util.ToastFactory
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
import javax.inject.Inject

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
class MpChartRecipeFragment @Inject constructor(
    val toastFactory: ToastFactory
) : WolmoFragment<FragmentMpChartBinding, MpChartRecipePresenter>(), MpChartRecipeView {

    private lateinit var progressDialog: ProgressDialog

    override fun layout(): Int = R.layout.fragment_mp_chart

    override fun init() {
    }

    override fun setListeners() {
        super.setListeners()

        binding!!.vChartList.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                adapterView: AdapterView<*>?,
                view: View?,
                positionInt: Int,
                positionLong: Long
            ) {
                presenter.onSpinnerItemClicked(positionInt)
            }

            override fun onNothingSelected(adapterView: AdapterView<*>?) {
            }
        }
    }

    override fun hideGraphs() {
        with(binding!!) {
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
    }

    override fun showBarChart(barData: BarData) {
        binding!!.vBarChart.run {
            visibility = View.VISIBLE
            data = barData
            animateY(ANIMATION_DELAY)
            isDragEnabled = true

            setOnChartValueSelectedListener(object : OnChartValueSelectedListener {
                override fun onValueSelected(entry: Entry?, highlight: Highlight?) {
                    val barEntry = entry as BarEntry

                    /**
                     * Data is an additional information of Entry, represents an Object with
                     * any class or null if no data has been specified
                     */
                    toastFactory.show(
                        getString(
                            R.string.mp_chart_item_bar,
                            barEntry.y,
                            barEntry.data
                        )
                    )
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
            groupBars(0f, groupSpace, BAR_SPACE)

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
            xAxis.run {
                axisMinimum = 0f
                axisMaximum =
                    barData.xMax + (((barData.barWidth + BAR_SPACE) * barData.dataSetCount) + groupSpace)
                position = XAxis.XAxisPosition.BOTTOM
                setCenterAxisLabels(true)
            }

            setVisibleXRangeMaximum(BAR_VISIBLE_RANGE)
            invalidate()
        }
    }

    override fun showBubbleChart(bubbleData: BubbleData) {
        binding!!.vBubbleChart.run {
            visibility = View.VISIBLE
            data = bubbleData
            isDragEnabled = true
            description.text = getString(R.string.mp_chart_bubble)

            animateXY(ANIMATION_DELAY, ANIMATION_DELAY)

            setOnChartValueSelectedListener(object : OnChartValueSelectedListener {
                override fun onValueSelected(entry: Entry?, highlight: Highlight?) {
                    val bubbleEntry = entry as BubbleEntry

                    /**
                     * Data is an additional information of Entry, represents an Object with
                     * any class or null if no data has been specified
                     */
                    toastFactory.show(
                        getString(
                            R.string.mp_chart_item_bubble, bubbleEntry.x,
                            bubbleEntry.y, bubbleEntry.size, bubbleEntry.data
                        )
                    )
                }

                override fun onNothingSelected() {
                }
            })
        }
    }

    override fun showCombinedChart(combinedData: CombinedData) {
        binding!!.vCombinedChart.run {
            visibility = View.VISIBLE
            data = combinedData

            setDrawBarShadow(false)
            description.text = getString(R.string.mp_chart_combined)
            setDrawGridBackground(false)
            isHighlightFullBarEnabled = false

            axisRight.run {
                setDrawGridLines(false)
                axisMinimum = 0f
            }

            axisLeft.run {
                setDrawGridLines(false)
                axisMinimum = 0f
            }

            xAxis.run {
                position = XAxis.XAxisPosition.BOTH_SIDED
                axisMinimum = 0f
                granularity = GRANULARITY
                axisMaximum = data.xMax + AXIS_PHASE
            }

            animateXY(ANIMATION_DELAY, ANIMATION_DELAY)
            invalidate()
        }
    }

    override fun showHorizontalBarChart(hBarData: BarData) {
        binding!!.vHorizontalBarChart.run {
            visibility = View.VISIBLE
            data = hBarData
            animateY(ANIMATION_DELAY)
            isDragEnabled = true

            axisLeft.mAxisMinimum = 0f

            setOnChartValueSelectedListener(object : OnChartValueSelectedListener {
                override fun onValueSelected(entry: Entry?, highlight: Highlight?) {
                    val barEntry = entry as BarEntry

                    /**
                     * Data is an additional information of Entry, represents an Object with
                     * any class or null if no data has been specified
                     */
                    toastFactory.show(
                        getString(
                            R.string.mp_chart_item_bar,
                            barEntry.y,
                            barEntry.data
                        )
                    )
                }

                override fun onNothingSelected() {
                }
            })

            xAxis.run {
                mAxisMinimum = 0f
                position = XAxis.XAxisPosition.BOTTOM
                setCenterAxisLabels(true)
            }

            description.text = getString(R.string.mp_chart_h_bar)
            invalidate()
        }
    }

    override fun showLineChart(lineData: LineData) {
        binding!!.vLineChart.run {
            visibility = View.VISIBLE

            data = lineData
            description.isEnabled = false
            legend.isEnabled = false
            setPinchZoom(true)

            animateX(ANIMATION_DELAY)

            xAxis.run {
                enableGridDashedLine(DEFAULT_LENGTH, DEFAULT_LENGTH, 0f)
                labelCount = lineData.dataSetCount
                position = XAxis.XAxisPosition.BOTTOM
            }

            axisRight.enableGridDashedLine(DEFAULT_LENGTH, DEFAULT_LENGTH, 0f)
            axisLeft.enableGridDashedLine(DEFAULT_LENGTH, DEFAULT_LENGTH, 0f)
        }
    }

    override fun showPieChart(pieData: PieData) {
        binding!!.vPieChart.visibility = View.VISIBLE

        binding!!.vPieChart.run {
            data = pieData
            centerTextRadiusPercent = RADIUS_PERCENT
            animateXY(ANIMATION_DELAY, ANIMATION_DELAY)
            isDrawHoleEnabled = false
            description.text = getString(R.string.mp_chart_pie)

            setOnChartValueSelectedListener(object : OnChartValueSelectedListener {
                override fun onValueSelected(entry: Entry?, highlight: Highlight?) {
                    val pieEntry = entry as PieEntry
                    toastFactory.show(
                        getString(
                            R.string.mp_chart_item_pie,
                            pieEntry.label,
                            pieEntry.value
                        )
                    )
                }

                override fun onNothingSelected() {
                }
            })
        }
    }

    override fun showRadarChart(radarData: RadarData) {
        binding!!.vRadarChart.run {
            visibility = View.VISIBLE
            data = radarData
            description.text = getString(R.string.mp_chart_radar)
            animateXY(ANIMATION_DELAY, ANIMATION_DELAY)
        }
    }

    override fun showCandleStickChart(candleData: CandleData) {
        binding!!.vCandleStickChart.run {
            visibility = View.VISIBLE
            data = candleData
            description.text = getString(R.string.mp_chart_candle)
            animateXY(ANIMATION_DELAY, ANIMATION_DELAY)
            invalidate()
        }
    }

    override fun showScatterChart(scatterData: ScatterData) {
        binding!!.vScatterChart.run {
            visibility = View.VISIBLE
            data = scatterData
            description.text = getString(R.string.mp_chart_scatter)

            setOnChartValueSelectedListener(object : OnChartValueSelectedListener {
                override fun onValueSelected(entry: Entry?, highlight: Highlight?) {
                    val barEntry = entry as BarEntry

                    /**
                     * Data is an additional information of Entry, represents an Object with
                     * any class or null if no data has been specified
                     */
                    toastFactory.show(
                        getString(
                            R.string.mp_chart_item_bar,
                            barEntry.y,
                            barEntry.data
                        )
                    )
                }

                override fun onNothingSelected() {
                }
            })

            animateXY(ANIMATION_DELAY, ANIMATION_DELAY)
            invalidate()
        }
    }

    override fun showProgressBar() {
        progressDialog = ProgressDialog(context).apply {
            setProgressStyle(ProgressDialog.STYLE_SPINNER)
            setCancelable(false)
            setCanceledOnTouchOutside(false)
            setMessage(getString(R.string.mp_chart_p_dialog))
            show()
        }
    }

    override fun hideProgressBar() {
        progressDialog.run {
            setProgressStyle(ProgressDialog.STYLE_SPINNER)
            dismiss()
        }
    }

    companion object {
        private const val ANIMATION_DELAY = 2500
        private const val RADIUS_PERCENT = 1f
        private const val DEFAULT_LENGTH = 5f
        private const val GRANULARITY = 1f
        private const val AXIS_PHASE = 0.25f
        private const val BAR_VISIBLE_RANGE = 3f

        private const val BAR_SPACE = 0.0f
        private const val UNIT = 1f
    }
}