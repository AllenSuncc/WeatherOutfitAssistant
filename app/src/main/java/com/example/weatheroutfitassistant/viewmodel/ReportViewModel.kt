package com.example.myapplication.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.myapplication.data.database.AppDatabase
import com.example.myapplication.data.model.StyleCount
import kotlinx.coroutines.flow.Flow

class ReportViewModel(application: Application) : AndroidViewModel(application) {

    // Get DAO instance
    private val outfitDao = AppDatabase.getDatabase(application).outfitDAO()

    /**
     * Get the count distribution of all styles (style name + count)
     * Used for bar chart / pie chart display
     */
    val styleDistribution: Flow<List<StyleCount>> = outfitDao.getStyleDistribution()

    /**
     * Get the total number of outfit records
     * Used to display "Total Records" at the top of the report
     */
    val totalCount: Flow<Int> = outfitDao.getTotalOutfitCount()

    /**
     * Get the most frequently appearing outfit style
     * Used to display "Most Frequent Style" in the statistics section
     */
    val mostFrequentStyle: Flow<String?> = outfitDao.getMostFrequentStyle()

    /**
     * Get style distribution within a specified date range (optional feature)
     * Can be used when adding date filter controls
     */
    fun getStyleDistributionByDate(startDate: String, endDate: String): Flow<List<StyleCount>> {
        return outfitDao.getStyleDistributionBetween(startDate, endDate)
    }
}
