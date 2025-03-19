package com.example.infrastructure_monitoring_app.ui.page5

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
fun AmvBlokScreen(navController: NavController, viewModel: MonitoringViewModel) {
    val blok by viewModel.blok.collectAsState()
    val blokOptions = listOf("Blok A", "Blok B", "Blok C")

    Column(modifier = Modifier.padding(16.dp)) {
        CustomDropdown(
            value = blok,
            options = blokOptions,
            label = "BLOK",
            onValueChange = { viewModel.blok.value = it }
        )
        CustomButton(
            onClick = { navController.navigate("page6") },
            text = "Next",
            enabled = blok.isNotEmpty()
        )
    }
}