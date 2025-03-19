package com.example.infrastructure_monitoring_app.ui.page1

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.infrastructure_monitoring_app.data.viewmodel.MonitoringViewModel
import com.example.infrastructure_monitoring_app.ui.CustomButton
import com.example.infrastructure_monitoring_app.ui.CustomDropdown
import com.example.infrastructure_monitoring_app.ui.CustomTextField
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
        CustomTextField(
            value = tanggal,
            onValueChange = {},
            label = "Tanggal",
            readOnly = true,
            trailingIcon = {
                IconButton(onClick = { showDatePicker = true }) {
                    Icon(Icons.Default.DateRange, contentDescription = "Pilih Tanggal")
                }
            }
        )

        CustomTextField(
            value = namaOperator,
            onValueChange = { viewModel.namaOperator.value = it },
            label = "Nama Operator Alat Berat"
        )

        CustomTextField(
            value = noHpOperator,
            onValueChange = { viewModel.onNoHpOperatorChange(it) },
            label = "No HP Operator",
            isError = !isNoHpValid,
            errorMessage = if (!isNoHpValid) "No HP harus 10-13 digit" else null
        )

        CustomTextField(
            value = namaVendor,
            onValueChange = { viewModel.namaVendor.value = it },
            label = "Nama Vendor (PT/CV)"
        )

        CustomDropdown(
            value = regional,
            options = regionalOptions,
            label = "Regional",
            onValueChange = { viewModel.regionalPage1.value = it }
        )

        CustomButton(
            onClick = { navController.navigate("page2") },
            text = "Next",
            enabled = tanggal.isNotEmpty() && namaOperator.isNotEmpty() && noHpOperator.isNotEmpty() &&
                    namaVendor.isNotEmpty() && regional.isNotEmpty() && isNoHpValid
        )
    }
}