package ar.com.wolox.android.cookbook.mpchart

import ar.com.wolox.wolmo.core.presenter.BasePresenter
import javax.inject.Inject

class MpChartRecipePresenter @Inject constructor() : BasePresenter<MpChartRecipeView>() {

    fun onSpinnerItemClicked(item: Int) {

        when (item) {
            0 -> clearScreen()
            1 -> onBarChartSelected()
            2 -> onBubbleChartSelected()
            3 -> onCombinedChartSelected()
            4 -> onHorizontalBarChartSelected()
            5 -> onLineChartSelected()
            6 -> onPieChartSelected()
            7 -> onRadarChartSelected()
            8 -> onCandleStickChartSelected()
            9 -> onScatterChartSelected()
            else -> clearScreen()
        }
    }

    private fun clearScreen() {
        view.hideGraphs()
    }

    private fun onBarChartSelected() {
        view.hideGraphs()
        view.showBarChart()
    }

    private fun onBubbleChartSelected() {
        view.hideGraphs()
        view.showBubbleChart()
    }

    private fun onCombinedChartSelected() {
        view.hideGraphs()
        view.showCombinedChart()
    }

    private fun onHorizontalBarChartSelected() {
        view.hideGraphs()
        view.showHorizontalBarChart()
    }

    private fun onLineChartSelected() {
        view.hideGraphs()
        view.showLineChart()
    }

    private fun onPieChartSelected() {
        view.hideGraphs()
        view.showPieChart()
    }

    private fun onRadarChartSelected() {
        view.hideGraphs()
        view.showRadarChart()
    }

    private fun onCandleStickChartSelected() {
        view.hideGraphs()
        view.showCandleStickChart()
    }

    private fun onScatterChartSelected() {
        view.hideGraphs()
        view.showScatterChart()
    }
}