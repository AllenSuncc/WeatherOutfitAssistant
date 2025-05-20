package com.example.myapplication.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myapplication.data.DAO.UserDAO
import com.example.myapplication.data.DAO.OutfitDAO
import com.example.myapplication.data.entity.User
import com.example.myapplication.data.entity.Outfit

@Database(
    entities = [User::class, Outfit::class],  // 注册 Outfit 实体
    version = 2,                              // 更新版本号（如第一次添加 Outfit 表）
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDAO(): UserDAO
    abstract fun outfitDAO(): OutfitDAO       // 注册 OutfitDAO

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                )
                    .fallbackToDestructiveMigration(true)  // 如有结构变化直接清空重建
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
