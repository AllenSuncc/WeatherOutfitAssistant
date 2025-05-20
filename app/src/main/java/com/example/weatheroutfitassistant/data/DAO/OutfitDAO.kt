package com.example.myapplication.data.DAO

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myapplication.data.entity.Outfit
import com.example.myapplication.data.model.StyleCount
import kotlinx.coroutines.flow.Flow

@Dao
interface OutfitDAO {

    /**
     * Insert an outfit record into the database
     * If a record with the same primary key exists, replace it (REPLACE strategy)
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOutfit(entry: Outfit)

    /**
     * Get all outfit records, sorted by date in descending order (latest records at the top)
     * Returns a Flow, which can be used to observe data changes in real-time for UI updates
     */
    @Query("SELECT * FROM outfit_entries ORDER BY date DESC")
    fun getAllOutfits(): Flow<List<Outfit>>

    /**
     * Get the count of each outfit style
     * Used to generate charts (bar or pie)
     * Returns data class StyleCount(style, count)
     */
    @Query("SELECT style, COUNT(*) AS count FROM outfit_entries GROUP BY style")
    fun getStyleDistribution(): Flow<List<StyleCount>>

    /**
     * Get the total count of outfit records
     * Used to display statistical information or "total record count" summary
     */
    @Query("SELECT COUNT(*) FROM outfit_entries")
    fun getTotalOutfitCount(): Flow<Int>

    /**
     * Get the most frequent outfit style
     * Returns null if no data is available
     */
    @Query("""
        SELECT style FROM outfit_entries 
        GROUP BY style 
        ORDER BY COUNT(*) DESC 
        LIMIT 1
    """)
    fun getMostFrequentStyle(): Flow<String?>

    /**
     * Get the style distribution within a specified date range
     * Used to build "time filter" charts (e.g., this week, this month)
     */
    @Query("""
        SELECT style, COUNT(*) AS count 
        FROM outfit_entries 
        WHERE date BETWEEN :startDate AND :endDate 
        GROUP BY style
    """)
    fun getStyleDistributionBetween(
        startDate: String,
        endDate: String
    ): Flow<List<StyleCount>>

    @Delete
    suspend fun deleteOutfit(entry: Outfit)
}
