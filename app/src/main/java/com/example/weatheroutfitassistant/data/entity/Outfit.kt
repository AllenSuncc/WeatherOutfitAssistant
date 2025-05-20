package com.example.myapplication.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "outfit_entries")
data class Outfit(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,                        // Primary key, auto-increment
    val date: String,                      // Record date (format: YYYY-MM-DD)
    val style: String,                     // Outfit style (e.g. Casual, Formal etc.)
    val description: String                // Outfit description
)