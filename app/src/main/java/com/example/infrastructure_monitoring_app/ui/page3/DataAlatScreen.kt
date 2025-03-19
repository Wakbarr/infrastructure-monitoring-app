package com.example.infrastructure_monitoring_app.ui.page3

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.infrastructure_monitoring_app.data.viewmodel.MonitoringViewModel
import com.example.infrastructure_monitoring_app.ui.CustomButton
import com.example.infrastructure_monitoring_app.ui.CustomDropdown
import com.example.infrastructure_monitoring_app.ui.CustomTextField

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DataAlatScreen(navController: NavController, viewModel: MonitoringViewModel) {
    val distrik by viewModel.distrik.collectAsState()
    val jenisAlatBerat by viewModel.jenisAlatBerat.collectAsState()
    val merkTypeAlat by viewModel.merkTypeAlat.collectAsState()
    val status by viewModel.status.collectAsState()
    val kondisi by viewModel.kondisi.collectAsState()
    val distrikOptions = listOf("Distrik 1", "Distrik 2", "Distrik 3")
    val jenisAlatBeratOptions = listOf("Road Grader 1", "Road Grader 2", "Road Grader 3")
    val statusOptions = listOf("Inventaris", "Sewa")
    val kondisiOptions = listOf("Baik", "Rusak", "Perbaikan", "Hujan")

    Column(modifier = Modifier.padding(16.dp)) {
        CustomDropdown(
            value = distrik,
            options = distrikOptions,
            label = "DISTRIK",
            onValueChange = { viewModel.distrik.value = it }
        )
        CustomDropdown(
            value = jenisAlatBerat,
            options = jenisAlatBeratOptions,
            label = "Jenis Alat Berat",
            onValueChange = { viewModel.jenisAlatBerat.value = it }
        )
        CustomTextField(
            value = merkTypeAlat,
            onValueChange = { viewModel.merkTypeAlat.value = it },
            label = "Merk/Type Alat Berat"
        )
        CustomDropdown(
            value = status,
            options = statusOptions,
            label = "STATUS",
            onValueChange = { viewModel.status.value = it }
        )
        CustomDropdown(
            value = kondisi,
            options = kondisiOptions,
            label = "KONDISI",
            onValueChange = { viewModel.kondisi.value = it }
        )
        CustomButton(
            onClick = { navController.navigate("page4") },
            text = "Next",
            enabled = distrik.isNotEmpty() && jenisAlatBerat.isNotEmpty() && merkTypeAlat.isNotEmpty() &&
                    status.isNotEmpty() && kondisi.isNotEmpty()
        )
    }
}