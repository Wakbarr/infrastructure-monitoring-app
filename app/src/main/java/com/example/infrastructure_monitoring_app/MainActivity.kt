package com.example.infrastructure_monitoring_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.infrastructure_monitoring_app.data.viewmodel.MonitoringViewModel
import com.example.infrastructure_monitoring_app.navigation.AppNavHost


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel = MonitoringViewModel.create(this)
        setContent {
            App(viewModel)
        }
    }
}

@Composable
fun App(viewModel: MonitoringViewModel) {
    val navController = rememberNavController()
    AppNavHost(navController = navController, viewModel = viewModel)
}