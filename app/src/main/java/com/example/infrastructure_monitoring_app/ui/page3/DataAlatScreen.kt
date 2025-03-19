package com.example.infrastructure_monitoring_app.ui.page3

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.infrastructure_monitoring_app.data.viewmodel.MonitoringViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DataAlatScreen(navController: NavController, viewModel: MonitoringViewModel) {
    val distrik by viewModel.distrik.collectAsState()
    val jenisAlatBerat by viewModel.jenisAlatBerat.collectAsState()
    val merkTypeAlat by viewModel.merkTypeAlat.collectAsState()
    val status by viewModel.status.collectAsState()
    val kondisi by viewModel.kondisi.collectAsState()
    val distrikOptions = listOf("Distrik 1", "Distrik 2", "Distrik 3")
    val jenisAlatBeratOptions = listOf("Road Grader 1", "Road Grader 2", "Road Grader 3")
    val statusOptions = listOf("Inventaris", "Sewa")
    val kondisiOptions = listOf("Baik", "Rusak", "Perbaikan", "Hujan")

    Column(modifier = Modifier.padding(16.dp)) {
        var expandedDistrik by remember { mutableStateOf(false) }
        ExposedDropdownMenuBox(expanded = expandedDistrik, onExpandedChange = { expandedDistrik = !expandedDistrik }) {
            OutlinedTextField(value = distrik, onValueChange = {}, label = { Text("DISTRIK") }, readOnly = true, trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedDistrik) }, modifier = Modifier.fillMaxWidth())
            ExposedDropdownMenu(expanded = expandedDistrik, onDismissRequest = { expandedDistrik = false }) {
                distrikOptions.forEach { option -> DropdownMenuItem(onClick = { viewModel.distrik.value = option; expandedDistrik = false }) { Text(option) } }
            }
        }
        var expandedJenis by remember { mutableStateOf(false) }
        ExposedDropdownMenuBox(expanded = expandedJenis, onExpandedChange = { expandedJenis = !expandedJenis }) {
            OutlinedTextField(value = jenisAlatBerat, onValueChange = {}, label = { Text("Jenis Alat Berat") }, readOnly = true, trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedJenis) }, modifier = Modifier.fillMaxWidth())
            ExposedDropdownMenu(expanded = expandedJenis, onDismissRequest = { expandedJenis = false }) {
                jenisAlatBeratOptions.forEach { option -> DropdownMenuItem(onClick = { viewModel.jenisAlatBerat.value = option; expandedJenis = false }) { Text(option) } }
            }
        }
        OutlinedTextField(value = merkTypeAlat, onValueChange = { viewModel.merkTypeAlat.value = it }, label = { Text("Merk/Type Alat Berat") }, modifier = Modifier.fillMaxWidth())
        var expandedStatus by remember { mutableStateOf(false) }
        ExposedDropdownMenuBox(expanded = expandedStatus, onExpandedChange = { expandedStatus = !expandedStatus }) {
            OutlinedTextField(value = status, onValueChange = {}, label = { Text("STATUS") }, readOnly = true, trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedStatus) }, modifier = Modifier.fillMaxWidth())
            ExposedDropdownMenu(expanded = expandedStatus, onDismissRequest = { expandedStatus = false }) {
                statusOptions.forEach { option -> DropdownMenuItem(onClick = { viewModel.status.value = option; expandedStatus = false }) { Text(option) } }
            }
        }
        var expandedKondisi by remember { mutableStateOf(false) }
        ExposedDropdownMenuBox(expanded = expandedKondisi, onExpandedChange = { expandedKondisi = !expandedKondisi }) {
            OutlinedTextField(value = kondisi, onValueChange = {}, label = { Text("KONDISI") }, readOnly = true, trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedKondisi) }, modifier = Modifier.fillMaxWidth())
            ExposedDropdownMenu(expanded = expandedKondisi, onDismissRequest = { expandedKondisi = false }) {
                kondisiOptions.forEach { option -> DropdownMenuItem(onClick = { viewModel.kondisi.value = option; expandedKondisi = false }) { Text(option) } }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { navController.navigate("page4") }, enabled = distrik.isNotEmpty() && jenisAlatBerat.isNotEmpty() && merkTypeAlat.isNotEmpty() && status.isNotEmpty() && kondisi.isNotEmpty()) { Text("Next") }
    }
}