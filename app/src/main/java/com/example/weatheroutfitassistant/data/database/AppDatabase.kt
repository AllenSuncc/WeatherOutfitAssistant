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
    entities = [User::class, Outfit::class],  // Register Outfit entity
    version = 2,                              // Update version number (e.g. first time adding Outfit table)
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDAO(): UserDAO
    abstract fun outfitDAO(): OutfitDAO       // Register OutfitDAO

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
                    .fallbackToDestructiveMigration(true)  // If schema changes, clear and rebuild
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
