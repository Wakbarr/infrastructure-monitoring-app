package com.example.infrastructure_monitoring_app.ui.page4

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
fun AmvAfdelingScreen(navController: NavController, viewModel: MonitoringViewModel) {
    val afdeling by viewModel.afdeling.collectAsState()
    val afdelingOptions = listOf("Afd 1", "Afd 2", "Afd 3")

    Column(modifier = Modifier.padding(16.dp)) {
        CustomDropdown(
            value = afdeling,
            options = afdelingOptions,
            label = "Afdeling",
            onValueChange = { viewModel.afdeling.value = it }
        )
        CustomButton(
            onClick = { navController.navigate("page5") },
            text = "Next",
            enabled = afdeling.isNotEmpty()
        )
    }
}