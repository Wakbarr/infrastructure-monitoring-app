package com.example.infrastructure_monitoring_app.ui.page2

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
fun UnitKerjaScreen(navController: NavController, viewModel: MonitoringViewModel) {
    val regional by viewModel.regionalPage2.collectAsState()
    val regionalOptions = listOf("Kebun Bahjambi", "Kebun Mep", "Kebun Gub")

    Column(modifier = Modifier.padding(16.dp)) {
        CustomDropdown(
            value = regional,
            options = regionalOptions,
            label = "Regional",
            onValueChange = { viewModel.regionalPage2.value = it }
        )
        CustomButton(
            onClick = { navController.navigate("page3") },
            text = "Next",
            enabled = regional.isNotEmpty()
        )
    }
}