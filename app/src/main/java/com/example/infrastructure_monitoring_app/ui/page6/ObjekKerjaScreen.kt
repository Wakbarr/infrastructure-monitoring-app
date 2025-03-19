package com.example.infrastructure_monitoring_app.ui.page6

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.infrastructure_monitoring_app.data.viewmodel.MonitoringViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ObjekKerjaScreen(navController: NavController, viewModel: MonitoringViewModel) {
    val objekKerja by viewModel.objekKerja.collectAsState()
    val objekKerjaOptions = listOf("Acces Road", "Quarry")

    Column(modifier = Modifier.padding(16.dp)) {
        var expanded by remember { mutableStateOf(false) }
        ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = { expanded = !expanded }) {
            OutlinedTextField(value = objekKerja, onValueChange = {}, label = { Text("OBJEK KERJA ALAT BERAT") }, readOnly = true, trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) }, modifier = Modifier.fillMaxWidth())
            ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                objekKerjaOptions.forEach { option -> DropdownMenuItem(onClick = { viewModel.objekKerja.value = option; expanded = false }) { Text(option) } }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { navController.navigate("page7") }, enabled = objekKerja.isNotEmpty()) { Text("Next") }
    }
}