package com.example.infrastructure_monitoring_app.ui.page4

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.infrastructure_monitoring_app.data.viewmodel.MonitoringViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AmvAfdelingScreen(navController: NavController, viewModel: MonitoringViewModel) {
    val afdeling by viewModel.afdeling.collectAsState()
    val afdelingOptions = listOf("Afd 1", "Afd 2", "Afd 3")

    Column(modifier = Modifier.padding(16.dp)) {
        var expanded by remember { mutableStateOf(false) }
        ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = { expanded = !expanded }) {
            OutlinedTextField(value = afdeling, onValueChange = {}, label = { Text("Afdeling") }, readOnly = true, trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) }, modifier = Modifier.fillMaxWidth())
            ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                afdelingOptions.forEach { option -> DropdownMenuItem(onClick = { viewModel.afdeling.value = option; expanded = false }) { Text(option) } }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { navController.navigate("page5") }, enabled = afdeling.isNotEmpty()) { Text("Next") }
    }
}