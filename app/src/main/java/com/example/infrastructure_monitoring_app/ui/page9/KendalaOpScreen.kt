package com.example.infrastructure_monitoring_app.ui.page9

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
fun KendalaOpScreen(navController: NavController, viewModel: MonitoringViewModel) {
    val kendalaOperasional by viewModel.kendalaOperasional.collectAsState()
    val kendalaOptions = listOf("Tidak ada kendala", "Tidak tersedia bahan bakar", "Lainnya")

    Column(modifier = Modifier.padding(16.dp)) {
        CustomDropdown(
            value = kendalaOperasional,
            options = kendalaOptions,
            label = "KENDALA OPERASIONAL",
            onValueChange = { viewModel.kendalaOperasional.value = it }
        )
        CustomButton(
            onClick = { navController.navigate("page10") },
            text = "Next",
            enabled = kendalaOperasional.isNotEmpty()
        )
    }
}