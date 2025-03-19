package com.example.infrastructure_monitoring_app.ui.page7

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.infrastructure_monitoring_app.data.viewmodel.MonitoringViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun StatPekerjaanScreen(navController: NavController, viewModel: MonitoringViewModel) {
    val pekerjaan by viewModel.pekerjaan.collectAsState()
    val pekerjaanOptions = listOf("Pemeliharaan Jalan", "Peningkatan Mutu Jalan")

    Column(modifier = Modifier.padding(16.dp)) {
        var expanded by remember { mutableStateOf(false) }
        ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = { expanded = !expanded }) {
            OutlinedTextField(value = pekerjaan, onValueChange = {}, label = { Text("Pekerjaan Road Grader, Vibratorry Roller") }, readOnly = true, trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) }, modifier = Modifier.fillMaxWidth())
            ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                pekerjaanOptions.forEach { option -> DropdownMenuItem(onClick = { viewModel.pekerjaan.value = option; expanded = false }) { Text(option) } }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { navController.navigate("page8") }, enabled = pekerjaan.isNotEmpty()) { Text("Next") }
    }
}