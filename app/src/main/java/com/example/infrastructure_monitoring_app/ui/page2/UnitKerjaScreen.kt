package com.example.infrastructure_monitoring_app.ui.page2

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.infrastructure_monitoring_app.data.viewmodel.MonitoringViewModel


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun UnitKerjaScreen(navController: NavController, viewModel: MonitoringViewModel) {
    val regional by viewModel.regionalPage2.collectAsState()
    val regionalOptions = listOf("Kebun Bahjambi", "Kebun Mep", "Kebun Gub")

    Column(modifier = Modifier.padding(16.dp)) {
        var expanded by remember { mutableStateOf(false) }
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            OutlinedTextField(
                value = regional,
                onValueChange = {},
                label = { Text("Regional") },
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                modifier = Modifier.fillMaxWidth()
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                regionalOptions.forEach { option ->
                    DropdownMenuItem(
                        onClick = {
                            viewModel.regionalPage2.value = option
                            expanded = false
                        }
                    ) { Text(option) }
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { navController.navigate("page3") },
            enabled = regional.isNotEmpty()
        ) { Text("Next") }
    }
}