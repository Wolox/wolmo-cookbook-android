package ar.com.wolox.android.cookbook.mpchart

import android.app.Application
import android.graphics.Color
import android.graphics.Paint
import androidx.core.content.ContextCompat
import ar.com.wolox.android.cookbook.R
import ar.com.wolox.android.cookbook.mpchart.model.ChartDataSample
import ar.com.wolox.android.cookbook.mpchart.model.SpinnerClickItem
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.BubbleData
import com.github.mikephil.charting.data.BubbleDataSet
import com.github.mikephil.charting.data.BubbleEntry
import com.github.mikephil.charting.data.CandleData
import com.github.mikephil.charting.data.CandleDataSet
import com.github.mikephil.charting.data.CandleEntry
import com.github.mikephil.charting.data.CombinedData
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.data.RadarData
import com.github.mikephil.charting.data.RadarDataSet
import com.github.mikephil.charting.data.RadarEntry
import com.github.mikephil.charting.data.ScatterData
import com.github.mikephil.charting.data.ScatterDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.github.mikephil.charting.utils.ColorTemplate
import com.google.gson.GsonBuilder
import javax.inject.Inject

/**
 * Notes -> To display data, logic handle 4 major data type:
 * 1. Entries (Basic object with data unit):
 *  To define a dataSet need an "ArrayList<XXXEntry>()" (XXX maybe Entry, BarEntry, BubbleEntry, etc...)
 *  Entries extend from "Entry", they needs a values to define position (required):
 *  I) "X" and "Y" in case of Entry and BarEntry;
 *  II) "X", "Y" and "Size" in BubbleEntry;
 *  III) "Value" in PieEntry; "X", "ShadowHigh", "ShadowLow", "open" and "close" in CandleEntry
 *  and other optional parameters:
 *  I) "label" -> value or name to display in graph
 *  II) "icon" -> Drawable to display in graph (can display images instead normal dots)
 *  III) "data" -> Any object. (Warning: different types needs different manages)
 *
 * -DataSet and Data details in each method of presenter, Graph data details in each method of fragment
 * 2. DataSet (Contains a list of points -Entries- and basic parameters like color, size, etc...),
 *  see class documentation to view full parameters to change
 * 3. Data (contains one or more data sets to show)
 * 4. Graph/Chart (contains previous data and basic parameters like relation between each one -margin,
 *  distance-, animations, descriptions, labels, etc...
 *
 *  Notes2: Most of the attributes in DataSet or Chart are the same in all types of chart, a few items
 *  are specific of each class
 */
class MpChartRecipePresenter @Inject constructor(
    val application: Application
) : BasePresenter<MpChartRecipeView>() {

    private var dataSample: ChartDataSample? = null

    override fun onViewAttached() {
        super.onViewAttached()

        view.hideGraphs()
        dataSample = getSampleFromAssets()
    }

    /**
     * See "MpAndroidChartDataExample.json" in "assets" folder to get a example of the dataSet
     * used in the example.
     */
    private fun getSampleFromAssets(): ChartDataSample? {
        view.showProgressBar()

        val inputStream = application.applicationContext.assets.open("MpAndroidChartDataExample.json")
        val size = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        val json = String(buffer)

        val gson = GsonBuilder().create()
        val result = gson.fromJson(json, ChartDataSample::class.java)

        view.hideProgressBar()
        return result
    }

    fun onSpinnerItemClicked(item: Int) {
        when (item) {
            SpinnerClickItem.CLEAR.value -> clearScreen()
            SpinnerClickItem.BAR_CHART.value -> onBarChartSelected()
            SpinnerClickItem.BUBBLE_CHART.value -> onBubbleChartSelected()
            SpinnerClickItem.COMBINED_CHART.value -> onCombinedChartSelected()
            SpinnerClickItem.HORIZONTAL_BAR_CHART.value -> onHorizontalBarChartSelected()
            SpinnerClickItem.LINE_CHART.value -> onLineChartSelected()
            SpinnerClickItem.PIE_CHART.value -> onPieChartSelected()
            SpinnerClickItem.RADAR_CHART.value -> onRadarChartSelected()
            SpinnerClickItem.CANDLE_STICK_CHART.value -> onCandleStickChartSelected()
            SpinnerClickItem.SCATTER_CHART.value -> onScatterChartSelected()
            else -> clearScreen()
        }
    }

    private fun clearScreen() {
        view.hideGraphs()
    }

    /**
     * BarChart - Is a chart or graph that presents categorical data with rectangular bars with
     * heights or lengths proportional to the values that they represent. Bars plotted vertically.
     * 1. Entry Parameters defined at top of class
     * 2. DataSet Parameters:
     *  i) label: String = label to display above/in dataSet and the notations in bottom of graph
     *  ii) color: Int = color of the dataSet (use "colors" for multiple color over the same dataSet)
     * 3. Data:
     *  i) barWidth: Float = sets the width each bar should have on the x-axis
     */
    private fun onBarChartSelected() {
        view.hideGraphs()

        val yBar1 = ArrayList<BarEntry>()
        val yBar2 = ArrayList<BarEntry>()
        val yBar3 = ArrayList<BarEntry>()

        var label1 = LABEL_DEFAULT
        var label2 = LABEL_DEFAULT
        var label3 = LABEL_DEFAULT

        /** See "Notes" at top */
        dataSample?.let {
            label1 = it.barLabel1
            for (element in it.barData1) {
                yBar1.add(BarEntry(element.xVal, element.yVal, element.data))
            }

            label2 = it.barLabel2
            for (element in it.barData2) {
                yBar2.add(BarEntry(element.xVal, element.yVal, element.data))
            }

            label3 = it.barLabel3
            for (element in it.barData3) {
                yBar3.add(BarEntry(element.xVal, element.yVal, element.data))
            }
        }

        val barDataSet1 = BarDataSet(yBar1, label1)
        barDataSet1.color = Color.BLACK

        val barDataSet2 = BarDataSet(yBar2, label2)
        barDataSet2.color = Color.MAGENTA

        val barDataSet3 = BarDataSet(yBar3, label3)
        barDataSet3.color = Color.RED

        val data = BarData(barDataSet1, barDataSet2, barDataSet3)
        data.barWidth = BAR_WIDTH
        view.showBarChart(data)
    }

    /**
     * BubbleChart - Is a type of chart that displays three dimensions of data ("X", "Y" and "Size").
     * 1. Entry Parameters defined at top of class
     * 2. DataSet Parameters:
     *  i) label: String = label to display above/in dataSet and the notations in bottom of graph
     *  ii) colors: List<Int> = list of colors for the same DataSet
     *  iii) valueTextSize: Float = sets the text-size of the value-labels of this DataSet in dp
     */
    private fun onBubbleChartSelected() {
        view.hideGraphs()

        val yBubble = ArrayList<BubbleEntry>()
        var label = LABEL_DEFAULT

        /** See "Notes" at top */
        dataSample?.let {
            label = it.bubbleLabel
            for (element in it.bubbleData) {
                yBubble.add(BubbleEntry(element.xVal, element.yVal, element.size, element.data))
            }
        }

        val dataSet = BubbleDataSet(yBubble, label)
        val colorsArray = java.util.ArrayList<Int>()
        colorsArray.addAll(ColorTemplate.COLORFUL_COLORS.toList())

        dataSet.apply {
            colors = colorsArray
            valueTextSize = TEXT_SIZE_DEFAULT
        }

        val data = BubbleData(dataSet)

        view.showBubbleChart(data)
    }

    /**
     * CombinedChart - Let combine different chart in the same graph
     * 1. Entry Parameters defined at top of class
     * 2. DataSet: Same properties of each chart type
     * 3. Data:
     *  i) setData: can add data from other charts (BarData, BubbleData, CandleData and ScatterData)
     */
    private fun onCombinedChartSelected() {
        view.hideGraphs()

        val yBar = ArrayList<BarEntry>()
        val yLine = ArrayList<Entry>()

        var labelBar = LABEL_DEFAULT
        var labelLine = LABEL_DEFAULT

        /** See "Notes" at top */
        dataSample?.let {
            labelBar = it.combinedLabel1
            for (element in it.combinedData1) {
                yBar.add(BarEntry(element.xVal, element.yVal))
            }

            labelLine = it.combinedLabel2
            for (element in it.combinedData2) {
                yLine.add(Entry(element.xVal, element.yVal, element.data))
            }
        }

        val barDataSet = BarDataSet(yBar, labelBar)
        barDataSet.color = Color.GREEN

        val barData = BarData(barDataSet)
        barData.barWidth = BAR_WIDTH

        val lineDataSet = LineDataSet(yLine, labelLine)
        lineDataSet.apply {
            color = Color.BLUE
            setCircleColor(Color.BLUE)
            lineWidth = LINE_WIDTH
            circleRadius = RADIUS
            setDrawCircleHole(false)
            valueTextSize = TEXT_SIZE_MIN
            setDrawFilled(false)
        }

        val lineData = LineData(lineDataSet)

        val data = CombinedData()
        data.apply {
            setData(barData)
            setData(lineData)
        }

        view.showCombinedChart(data)
    }

    /**
     * HorizontalBarChart - Is a chart or graph that presents categorical data with rectangular bars with
     * heights or lengths proportional to the values that they represent. Bars plotted horizontally.
     * 1. Entry Parameters defined at top of class
     * 2. DataSet Parameters:
     *  i) label: String = label to display above/in dataSet and the notations in bottom of graph
     *  ii) color: Int = color of the dataSet (use "colors" for multiple color over the same dataSet)
     * 3. Data:
     *  i) barWidth: Float = sets the width each bar should have on the x-axis
     */
    private fun onHorizontalBarChartSelected() {
        view.hideGraphs()

        val yBar = ArrayList<BarEntry>()
        var label = LABEL_DEFAULT

        /** See "Notes" at top */
        dataSample?.let {
            label = it.hBarLabel
            for (element in it.hBarData) {
                yBar.add(BarEntry(element.xVal, element.yVal, element.data))
            }
        }

        val hLineDataSet = BarDataSet(yBar, label)
        hLineDataSet.color = Color.RED

        val data = BarData(hLineDataSet)
        data.barWidth = BAR_WIDTH

        view.showHorizontalBarChart(data)
    }

    /**
     * LineChart -  is a type of chart which displays information as a series of data points called
     * 'markers' connected by straight line segments.
     * 1. Entry Parameters defined at top of class
     * 2. DataSet Parameters:
     *  i) label: String = label to display above/in dataSet and the notations in bottom of graph
     *  ii) color: Int = color of the dataSet (use "colors" for multiple color over the same dataSet)
     *  iii) setCircleColor: Int - set the one and only color for the dataSet
     *  iv) lineWidth: Float - set the line width of the chart
     *  v) setDrawCircleHole: sets the radius of the drawn circles
     *  vi) valueTextSize: Float = sets the text-size of the value-labels of this DataSet in dp
     *  vii) setDrawFilled: Boolean = sets if the DataSet should be drawn filled (surface), and not
     *  just as a line, disabling this will give great performance boost
     * 3. Data: -
     */
    private fun onLineChartSelected() {
        view.hideGraphs()

        val yLine = ArrayList<Entry>()
        var label = LABEL_DEFAULT

        /** See "Notes" at top */
        dataSample?.let {
            label = it.lineLabel
            for (element in it.lineData) {
                yLine.add(Entry(element.xVal, element.yVal, application.applicationContext.getDrawable(R.drawable.ic_pokeball_small), element.data))
            }
        }

        val lineDataSet = LineDataSet(yLine, label)
        lineDataSet.apply {
            setCircleColor(Color.BLUE)
            lineWidth = LINE_WIDTH
            circleRadius = RADIUS
            setDrawCircleHole(false)
            valueTextSize = TEXT_SIZE_MIN
            setDrawFilled(false)
        }

        val dataSets = ArrayList<ILineDataSet>()
        dataSets.add(lineDataSet)

        val data = LineData(dataSets)

        view.showLineChart(data)
    }

    /**
     * PieChart - is a circular statistical graphic, which is divided into slices to illustrate
     * numerical proportion (is proportional to the quantity it represents). The sum of all values
     * in the DataSet represents the 100% (the total).
     * 1. Entry Parameters defined at top of class
     * 2. DataSet Parameters:
     *  i) label: String = label to display above/in dataSet and the notations in bottom of graph
     *  ii) colors: List<Int> = list of colors for the same DataSet
     *  iii) valueTextSize: Float = sets the text-size of the value-labels of this DataSet in dp
     * 3. Data: -
     */
    private fun onPieChartSelected() {
        view.hideGraphs()

        val yPie = ArrayList<PieEntry>()
        var label = LABEL_DEFAULT

        /** See "Notes" at top */
        dataSample?.let {
            label = it.pieLabel
            for (element in it.pieData) {
                yPie.add(PieEntry(element.value, element.label))
            }
        }

        val dataSet = PieDataSet(yPie, label)
        val colorsArray = java.util.ArrayList<Int>()
        colorsArray.addAll(ColorTemplate.COLORFUL_COLORS.toList())
        dataSet.apply {
            valueTextSize = TEXT_SIZE_MIN
            colors = colorsArray
        }

        val data = PieData(dataSet)

        view.showPieChart(data)
    }

    /**
     * RadarChart - Is a graphical method of displaying multivariate data in the form of a
     * two-dimensional chart of three or more quantitative variables represented on axes
     * starting from the same point.
     * 1. Entry Parameters defined at top of class. The max number in the dataSets sample determinate
     * the shape of the graph.
     * 2. DataSet Parameters:
     *  i) label: String = label to display above/in dataSet and the notations in bottom of graph
     *  ii) color: Int = color of the dataSet (use "colors" for multiple color over the same dataSet)
     *  iii) valueTextSize: Float = sets the text-size of the value-labels of this DataSet in dp
     *
     *  -For inside filled graph-
     *  iv) fillColor: Int - sets the color that is used for filling the area below the line
     *  v) setDrawFilled: Boolean - sets if the DataSet should be drawn filled (surface), and not just
     * as a line, disabling this will give great performance boost
     *  vi) fillAlpha: Int - sets the alpha value (transparency) that is used for filling the line
     * 3. Data: -
     */
    private fun onRadarChartSelected() {
        view.hideGraphs()

        val yRadar = ArrayList<RadarEntry>()
        var label = LABEL_DEFAULT

        /** See "Notes" at top */
        dataSample?.let {
            label = it.radarLabel
            for (element in it.radarData) {
                yRadar.add(RadarEntry(element.value, element.data))
            }
        }

        val radarDataSet = RadarDataSet(yRadar, label)
        radarDataSet.apply {
            color = Color.BLUE
            valueTextSize = TEXT_SIZE_DEFAULT

            fillColor = Color.CYAN
            setDrawFilled(true)
            fillAlpha = ALPHA
        }

        val data = RadarData(radarDataSet)

        view.showRadarChart(data)
    }

    /**
     * CandleStickChart - is a style of financial chart used to describe price movements of a
     * security, derivative, or currency.
     * 1. Entry Parameters defined at top of class
     * 2. DataSet Parameters:
     *  i) label: String = label to display above/in dataSet and the notations in bottom of graph
     *  ii) shadowColor: Int - sets shadow color for all entries
     *  iii) shadowWidth: Float - sets the width of the candle-shadow-line in pixels
     *  iv) decreasingColor: Int - sets the one and only color that should be used for this DataSet
     *  when open > close
     *  v) decreasingPaintStyle - sets paint style when open > close
     *  vi) increasingColor: Int - sets the one and ONLY color that should be used for this DataSet
     *  when open <= close
     *  vii) increasingPaintStyle - sets paint style when open < close
     *  viii) neutralColor: Int - sets the one and ONLY color that should be used for this DataSet
     *  when open == close
     *  ix) valueTextColor: Int - sets the color the value-labels of this DataSet should have
     * 3. Data: -
     */
    private fun onCandleStickChartSelected() {
        view.hideGraphs()

        val yCandle = ArrayList<CandleEntry>()
        var label = LABEL_DEFAULT

        /** See "Notes" at top */
        dataSample?.let {
            label = it.candleLabel
            for (element in it.candleData) {
                yCandle.add(CandleEntry(element.xVal, element.high, element.low, element.open, element.close))
            }
        }

        val candleDataSet = CandleDataSet(yCandle, label)
        candleDataSet.apply {
            setColors(ContextCompat.getColor(application.applicationContext, R.color.chart_candle_set_data))
            shadowColor = Color.DKGRAY
            shadowWidth = SHADOW_WIDTH
            decreasingColor = Color.RED
            decreasingPaintStyle = Paint.Style.FILL
            increasingColor = ContextCompat.getColor(application.applicationContext, R.color.chart_candle_increase)
            increasingPaintStyle = Paint.Style.FILL
            neutralColor = Color.BLUE
            valueTextColor = Color.RED
        }

        val data = CandleData(candleDataSet)

        view.showCandleStickChart(data)
    }

    /**
     * ScatterChart - is a type of plot or mathematical diagram using Cartesian coordinates to
     * display values for typically two variables for a set of data. If the points are coded, one
     * additional variable can be displayed.
     * 1. Entry Parameters defined at top of class
     * 2. DataSet Parameters:
     *  i) label: String = label to display above/in dataSet and the notations in bottom of graph
     *  ii) colors: List<Int> = list of colors for the same DataSet
     *  iii) valueTextColor: Int - sets the color the value-labels of this DataSet should have
     *  iv) valueTextSize: Float - sets the text-size of the value-labels of this DataSet in dp
     * 3. Data: -
     */
    private fun onScatterChartSelected() {
        view.hideGraphs()

        val yScatter = ArrayList<BarEntry>()
        var label = LABEL_DEFAULT

        /** See "Notes" at top */
        dataSample?.let {
            label = it.scatterLabel
            for (element in it.scatterData) {
                yScatter.add(BarEntry(element.xVal, element.yVal, element.data))
            }
        }

        val scatterDataSet = ScatterDataSet(yScatter as List<Entry>?, label)
        scatterDataSet.apply {
            colors = ColorTemplate.COLORFUL_COLORS.toList()
            valueTextColor = Color.BLACK
            valueTextSize = TEXT_SIZE_DEFAULT
        }

        val data = ScatterData(scatterDataSet)

        view.showScatterChart(data)
    }

    companion object {
        private const val LABEL_DEFAULT = "Label"
        private const val BAR_WIDTH = 0.2f
        private const val TEXT_SIZE_DEFAULT = 18f
        private const val LINE_WIDTH = 1f
        private const val RADIUS = 3f
        private const val TEXT_SIZE_MIN = 0f
        private const val SHADOW_WIDTH = 0.7f
        private const val ALPHA = 180
    }
}