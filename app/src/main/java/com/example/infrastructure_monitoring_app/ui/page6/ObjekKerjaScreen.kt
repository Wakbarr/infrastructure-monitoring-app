package com.example.infrastructure_monitoring_app.ui.page6

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
fun ObjekKerjaScreen(navController: NavController, viewModel: MonitoringViewModel) {
    val objekKerja by viewModel.objekKerja.collectAsState()
    val objekKerjaOptions = listOf("Acces Road", "Quarry")

    Column(modifier = Modifier.padding(16.dp)) {
        CustomDropdown(
            value = objekKerja,
            options = objekKerjaOptions,
            label = "OBJEK KERJA ALAT BERAT",
            onValueChange = { viewModel.objekKerja.value = it }
        )
        CustomButton(
            onClick = { navController.navigate("page7") },
            text = "Next",
            enabled = objekKerja.isNotEmpty()
        )
    }
}