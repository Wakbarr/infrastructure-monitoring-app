package com.example.infrastructure_monitoring_app.ui.page9

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.infrastructure_monitoring_app.data.viewmodel.MonitoringViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun KendalaOpScreen(navController: NavController, viewModel: MonitoringViewModel) {
    val kendalaOperasional by viewModel.kendalaOperasional.collectAsState()
    val kendalaOptions = listOf("Tidak ada kendala", "Tidak tersedia bahan bakar", "Lainnya")

    Column(modifier = Modifier.padding(16.dp)) {
        var expanded by remember { mutableStateOf(false) }
        ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = { expanded = !expanded }) {
            OutlinedTextField(value = kendalaOperasional, onValueChange = {}, label = { Text("KENDALA OPERASIONAL") }, readOnly = true, trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) }, modifier = Modifier.fillMaxWidth())
            ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                kendalaOptions.forEach { option -> DropdownMenuItem(onClick = { viewModel.kendalaOperasional.value = option; expanded = false }) { Text(option) } }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { navController.navigate("page10") }, enabled = kendalaOperasional.isNotEmpty()) { Text("Next") }
    }
}