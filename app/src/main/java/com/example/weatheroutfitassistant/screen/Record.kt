package com.example.weatheroutfitassistant.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.weatheroutfitassistant.viewmodel.OutfitViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Record(viewModel: OutfitViewModel = viewModel()) {
    // Initialize basic form states
    var selectedDate by remember { mutableStateOf("") }
    val styles = listOf("Casual", "Formal", "Sporty", "Street", "Business")
    var expanded by remember { mutableStateOf(false) }
    var selectedStyle by remember { mutableStateOf(styles.first()) }
    var description by remember { mutableStateOf("") }

    // Layout container
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Text("Record Your Outfit", style = MaterialTheme.typography.headlineSmall)
    }
}