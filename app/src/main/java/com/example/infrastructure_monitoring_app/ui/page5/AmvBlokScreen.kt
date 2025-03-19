package com.example.infrastructure_monitoring_app.ui.page5

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.infrastructure_monitoring_app.data.viewmodel.MonitoringViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AmvBlokScreen(navController: NavController, viewModel: MonitoringViewModel) {
    val blok by viewModel.blok.collectAsState()
    val blokOptions = listOf("Blok A", "Blok B", "Blok C")

    Column(modifier = Modifier.padding(16.dp)) {
        var expanded by remember { mutableStateOf(false) }
        ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = { expanded = !expanded }) {
            OutlinedTextField(value = blok, onValueChange = {}, label = { Text("BLOK") }, readOnly = true, trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) }, modifier = Modifier.fillMaxWidth())
            ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                blokOptions.forEach { option -> DropdownMenuItem(onClick = { viewModel.blok.value = option; expanded = false }) { Text(option) } }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { navController.navigate("page6") }, enabled = blok.isNotEmpty()) { Text("Next") }
    }
}