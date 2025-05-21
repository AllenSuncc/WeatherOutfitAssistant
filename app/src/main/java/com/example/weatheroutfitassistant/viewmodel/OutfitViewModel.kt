package com.example.myapplication.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.database.AppDatabase
import com.example.myapplication.data.entity.Outfit
import kotlinx.coroutines.launch

class OutfitViewModel(application: Application) : AndroidViewModel(application) {

    // Get the DAO instance from the database
    private val outfitDao = AppDatabase.getDatabase(application).outfitDAO()

    // Insert an outfit record (safe to run on main thread using coroutine)
    fun insertOutfit(date: String, style: String, description: String) {
        viewModelScope.launch {
            val outfit = Outfit(
                date = date,
                style = style,
                description = description
            )
            outfitDao.insertOutfit(outfit)
        }
    }

    // Expose all outfit records (for the history screen)
    val allOutfits = outfitDao.getAllOutfits()

    fun deleteOutfit(outfit: Outfit) {
        viewModelScope.launch {
            outfitDao.deleteOutfit(outfit)
        }
    }
}