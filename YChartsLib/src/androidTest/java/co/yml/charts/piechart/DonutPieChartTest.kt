package co.yml.charts.piechart

import androidx.compose.foundation.layout.Column
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import co.yml.charts.common.components.Legends
import co.yml.charts.common.model.PlotType
import co.yml.charts.common.utils.DataUtils
import co.yml.charts.ui.piechart.charts.DonutPieChart
import co.yml.charts.ui.piechart.models.PieChartConfig
import co.yml.charts.ui.piechart.models.PieChartData

import org.junit.Rule
import org.junit.Test


class DonutPieChartTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private val pieChartConfig = PieChartConfig(
        percentVisible = false,
        strokeWidth = 120f,
        percentColor = Color.Black
    )

    private val pieChartData = PieChartData(
        slices = listOf(
            PieChartData.Slice("A", 15f, Color(0xFF58BDFF)),
            PieChartData.Slice("B", 35f, Color(0xFF125B7F)),
            PieChartData.Slice("C", 40f, Color(0xFF092D40)),
        ),
        plotType = PlotType.Donut
    )


    @OptIn(ExperimentalMaterialApi::class)
    @Test
    fun whenIsLegendVisibleIsTrueLegendLabelsAreVisible() {
        composeTestRule.setContent {
            Column(modifier = Modifier) {
                Legends(legendsConfig = DataUtils.getLegendsConfigFromPieChartData(pieChartData, 3))
                DonutPieChart(
                    modifier = Modifier,
                    pieChartData = pieChartData,
                    pieChartConfig = pieChartConfig
                )
            }
        }
        composeTestRule.onNodeWithText("A").assertIsDisplayed()
        composeTestRule.onNodeWithText("B").assertIsDisplayed()
        composeTestRule.onNodeWithText("C").assertIsDisplayed()
    }

    @OptIn(ExperimentalMaterialApi::class)
    @Test
    fun whenIsLegendVisibleIsFalseNoLegendLabelsAreShown() {
        composeTestRule.setContent {
            DonutPieChart(
                modifier = Modifier, pieChartData = pieChartData,
                pieChartConfig = pieChartConfig
            )
        }
        composeTestRule.onNodeWithText("A").assertDoesNotExist()
        composeTestRule.onNodeWithText("B").assertDoesNotExist()
        composeTestRule.onNodeWithText("C").assertDoesNotExist()
    }
}

