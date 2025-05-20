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
     * 向数据库插入一条穿搭记录
     * 如果存在相同主键，则替换旧记录（REPLACE 策略）
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOutfit(entry: Outfit)

    /**
     * 获取所有穿搭记录，按日期倒序排列（最新记录在最上）
     * 返回类型为 Flow，可用于 UI 实时观察数据变化
     */
    @Query("SELECT * FROM outfit_entries ORDER BY date DESC")
    fun getAllOutfits(): Flow<List<Outfit>>

    /**
     * 获取每种穿搭风格的出现次数
     * 用于生成图表（柱状图或饼图）
     * 返回数据类 StyleCount(style, count)
     */
    @Query("SELECT style, COUNT(*) AS count FROM outfit_entries GROUP BY style")
    fun getStyleDistribution(): Flow<List<StyleCount>>

    /**
     * 获取穿搭记录总数
     * 用于显示统计信息或“总记录数”汇总
     */
    @Query("SELECT COUNT(*) FROM outfit_entries")
    fun getTotalOutfitCount(): Flow<Int>

    /**
     * 获取出现次数最多的穿搭风格
     * 若无数据，则返回 null
     */
    @Query("""
        SELECT style FROM outfit_entries 
        GROUP BY style 
        ORDER BY COUNT(*) DESC 
        LIMIT 1
    """)
    fun getMostFrequentStyle(): Flow<String?>

    /**
     * 获取指定日期范围内的风格分布统计
     * 用于构建“时间筛选”图表（如本周、本月）
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
