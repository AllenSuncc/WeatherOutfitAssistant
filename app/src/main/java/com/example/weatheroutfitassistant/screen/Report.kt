package com.example.myapplication.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.data.model.StyleCount
import com.example.myapplication.viewmodel.ReportViewModel
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.utils.ColorTemplate


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Report(viewModel: ReportViewModel = viewModel()) {

    // Collect real-time statistics from the database
    val styleDist by viewModel.styleDistribution.collectAsState(initial = emptyList())
    val totalCount by viewModel.totalCount.collectAsState(initial = 0)
    val topStyle by viewModel.mostFrequentStyle.collectAsState(initial = null)

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("📊 Style Report") })
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            // 🔢 Display total record count
            Text(
                text = "Total Records: $totalCount",
                style = MaterialTheme.typography.titleMedium
            )

            // 🌟 Display the most frequent style
            Text(
                text = "Most Frequent Style: ${topStyle ?: "N/A"}",
                style = MaterialTheme.typography.titleMedium
            )

            // 📊 Display style distribution using bar chart
            if (styleDist.isNotEmpty()) {
                StyleBarChart(styleDist)
            } else {
                Text("No data to display.")
            }
        }
    }
}


@Composable
fun StyleBarChart(data: List<StyleCount>) {

    // Convert style + count into BarEntry and label list
    val entries = data.mapIndexed { index, item ->
        BarEntry(index.toFloat(), item.count.toFloat())
    }
    val labels = data.map { it.style }

    // Build DataSet and BarData
    val dataSet = BarDataSet(entries, "Style Frequency").apply {
        colors = ColorTemplate.MATERIAL_COLORS.toList()
        valueTextSize = 16f
    }
    val barData = BarData(dataSet)

    // Use AndroidView to embed MPAndroidChart's BarChart
    AndroidView(
        modifier = Modifier
            .fillMaxWidth()
            .height(320.dp),
        factory = { context ->
            BarChart(context).apply {
                this.data = barData
                description.isEnabled = false
                setFitBars(true)
                animateY(1000)

                xAxis.apply {
                    position = XAxis.XAxisPosition.BOTTOM
                    valueFormatter = IndexAxisValueFormatter(labels)
                    granularity = 1f
                    setDrawGridLines(false)
                }

                axisLeft.axisMinimum = 0f
                axisRight.isEnabled = false
            }
        }
    )
}

