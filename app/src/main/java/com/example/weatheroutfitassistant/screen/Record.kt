package com.example.myapplication.screen

import android.app.DatePickerDialog
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.weatheroutfitassistant.viewmodel.OutfitViewModel
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Record(viewModel: OutfitViewModel = viewModel()) {
    val context = LocalContext.current

    // Date-related state
    val calendar = Calendar.getInstance()
    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    var selectedDate by remember { mutableStateOf(dateFormat.format(calendar.time)) }

    // Date picker dialog
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

    // Dropdown menu for outfit styles
    val styles = listOf("Casual", "Formal", "Sporty", "Street", "Business")
    var expanded by remember { mutableStateOf(false) }
    var selectedStyle by remember { mutableStateOf(styles.first()) }

    // Description input field
    var description by remember { mutableStateOf("") }

    // Submit button click handler
    fun handleSubmit() {
        if (description.isBlank()) {
            Toast.makeText(context, "Please enter a description.", Toast.LENGTH_SHORT).show()
        } else {
            viewModel.insertOutfit(selectedDate, selectedStyle, description)
            Toast.makeText(context, "Outfit saved!", Toast.LENGTH_SHORT).show()
            // Optional: clear the input
            description = ""
        }
    }

    // UI layout
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Text("Record Your Outfit", style = MaterialTheme.typography.headlineSmall)

        // Date picker button
        OutlinedButton(onClick = { datePickerDialog.show() }) {
            Text("📅 Date: $selectedDate")
        }

        // Dropdown menu for selecting style
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            OutlinedTextField(
                value = selectedStyle,
                onValueChange = {},
                readOnly = true,
                label = { Text("Style") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth()
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                styles.forEach { style ->
                    DropdownMenuItem(
                        text = { Text(style) },
                        onClick = {
                            selectedStyle = style
                            expanded = false
                        }
                    )
                }
            }
        }

        // Outfit description input field (multiline)
        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Description") },
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp),
            maxLines = 6
        )

        // Submit button
        Button(
            onClick = { handleSubmit() },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Submit")
        }
    }
}