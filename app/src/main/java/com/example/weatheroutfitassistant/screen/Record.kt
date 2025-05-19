package com.example.weatheroutfitassistant.screen

import android.app.DatePickerDialog
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.weatheroutfitassistant.viewmodel.OutfitViewModel
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Record(viewModel: OutfitViewModel = viewModel()) {
    val context = LocalContext.current

    // Initialize date state
    val calendar = Calendar.getInstance()
    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    var selectedDate by remember { mutableStateOf(dateFormat.format(calendar.time)) }

    // Create DatePickerDialog
    val datePickerDialog = DatePickerDialog(
        context,
        { _, year, month, day ->
            calendar.set(year, month, day)
            selectedDate = dateFormat.format(calendar.time)
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )

    // Basic layout
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Text("Record Your Outfit", style = MaterialTheme.typography.headlineSmall)

        // Button to show date picker
        OutlinedButton(onClick = { datePickerDialog.show() }) {
            Text("📅 Date: $selectedDate")
        }
    }
}