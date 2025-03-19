package com.example.infrastructure_monitoring_app.ui.page7

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
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun StatPekerjaanScreen(navController: NavController, viewModel: MonitoringViewModel) {
    val pekerjaan by viewModel.pekerjaan.collectAsState()
    val pekerjaanOptions = listOf("Pemeliharaan Jalan", "Peningkatan Mutu Jalan")

    Column(modifier = Modifier.padding(16.dp)) {
        CustomDropdown(
            value = pekerjaan,
            options = pekerjaanOptions,
            label = "Pekerjaan Road Grader, Vibratory Roller",
            onValueChange = { viewModel.pekerjaan.value = it }
        )
        CustomButton(
            onClick = { navController.navigate("page8") },
            text = "Next",
            enabled = pekerjaan.isNotEmpty()
        )
    }
}