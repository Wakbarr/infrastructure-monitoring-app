package com.example.infrastructure_monitoring_app.ui.page1

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.infrastructure_monitoring_app.data.viewmodel.MonitoringViewModel
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun DataUserScreen(navController: NavController, viewModel: MonitoringViewModel) {
    val tanggal by viewModel.tanggal.collectAsState()
    val namaOperator by viewModel.namaOperator.collectAsState()
    val noHpOperator by viewModel.noHpOperator.collectAsState()
    val namaVendor by viewModel.namaVendor.collectAsState()
    val regional by viewModel.regionalPage1.collectAsState()
    val isNoHpValid by viewModel.isNoHpValid.collectAsState()
    val regionalOptions = listOf("Regional 1", "Regional 2", "Regional 3")

    var showDatePicker by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState()

    // Tampilkan DatePickerDialog bila showDatePicker true
    if (showDatePicker) {
        DatePickerDialog(
            onDismissRequest = { showDatePicker = false },
            confirmButton = {
                TextButton(onClick = {
                    datePickerState.selectedDateMillis?.let {
                        val formattedDate = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date(it))
                        viewModel.tanggal.value = formattedDate
                    }
                    showDatePicker = false
                }) { Text("OK") }
            },
            dismissButton = {
                TextButton(onClick = { showDatePicker = false }) { Text("Cancel") }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }

    Column(modifier = Modifier.padding(16.dp)) {
        // Kolom Tanggal dengan trailing icon kalender
        OutlinedTextField(
            value = tanggal,
            onValueChange = {},
            label = { Text("Tanggal") },
            readOnly = true,
            trailingIcon = {
                IconButton(onClick = { showDatePicker = true }) {
                    Icon(Icons.Default.DateRange, contentDescription = "Pilih Tanggal")
                }
            },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = namaOperator,
            onValueChange = { viewModel.namaOperator.value = it },
            label = { Text("Nama Operator Alat Berat") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = noHpOperator,
            onValueChange = { viewModel.onNoHpOperatorChange(it) },
            label = { Text("No HP Operator") },
            isError = !isNoHpValid,
            modifier = Modifier.fillMaxWidth()
        )
        if (!isNoHpValid) {
            Text("No HP harus 10-13 digit", color = MaterialTheme.colors.error)
        }

        OutlinedTextField(
            value = namaVendor,
            onValueChange = { viewModel.namaVendor.value = it },
            label = { Text("Nama Vendor (PT/CV)") },
            modifier = Modifier.fillMaxWidth()
        )

        var expanded by remember { mutableStateOf(false) }
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            OutlinedTextField(
                value = regional,
                onValueChange = {},
                label = { Text("Regional") },
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                modifier = Modifier.fillMaxWidth()
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                regionalOptions.forEach { option ->
                    DropdownMenuItem(
                        onClick = {
                            viewModel.regionalPage1.value = option
                            expanded = false
                        }
                    ) { Text(option) }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { navController.navigate("page2") },
            enabled = tanggal.isNotEmpty() && namaOperator.isNotEmpty() && noHpOperator.isNotEmpty() &&
                    namaVendor.isNotEmpty() && regional.isNotEmpty() && isNoHpValid,
            modifier = Modifier.fillMaxWidth()
        ) { Text("Next") }
    }
}
