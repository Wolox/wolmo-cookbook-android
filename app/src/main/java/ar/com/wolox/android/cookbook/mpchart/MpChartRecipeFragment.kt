package ar.com.wolox.android.cookbook.mpchart

import android.util.Log
import android.view.View
import android.widget.AdapterView
import ar.com.wolox.android.cookbook.R
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
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

    override fun showBarChart() {
        vBarChart.visibility = View.VISIBLE
    }

    override fun showBubbleChart() {
        vBubbleChart.visibility = View.VISIBLE
    }

    override fun showCombinedChart() {
        vCombinedChart.visibility = View.VISIBLE
    }

    override fun showHorizontalBarChart() {
        vHorizontalBarChart.visibility = View.VISIBLE
    }

    override fun showLineChart() {
        vLineChart.visibility = View.VISIBLE
    }

    override fun showPieChart() {
        vPieChart.visibility = View.VISIBLE
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
}