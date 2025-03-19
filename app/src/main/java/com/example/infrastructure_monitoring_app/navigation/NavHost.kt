package com.example.infrastructure_monitoring_app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.infrastructure_monitoring_app.data.viewmodel.MonitoringViewModel
import com.example.infrastructure_monitoring_app.ui.home.HomeScreen
import com.example.infrastructure_monitoring_app.ui.login.LoginScreen
import com.example.infrastructure_monitoring_app.ui.page1.DataUserScreen
import com.example.infrastructure_monitoring_app.ui.page10.DokumentasiAlatScreen
import com.example.infrastructure_monitoring_app.ui.page2.UnitKerjaScreen
import com.example.infrastructure_monitoring_app.ui.page3.DataAlatScreen
import com.example.infrastructure_monitoring_app.ui.page4.AmvAfdelingScreen
import com.example.infrastructure_monitoring_app.ui.page5.AmvBlokScreen
import com.example.infrastructure_monitoring_app.ui.page6.ObjekKerjaScreen
import com.example.infrastructure_monitoring_app.ui.page7.StatPekerjaanScreen
import com.example.infrastructure_monitoring_app.ui.page8.JamKerjaAlatScreen
import com.example.infrastructure_monitoring_app.ui.page9.KendalaOpScreen

@Composable
fun AppNavHost(navController: NavHostController, viewModel: MonitoringViewModel) {
    NavHost(navController = navController, startDestination = "login") {
        composable("login") { LoginScreen(navController) }
        composable("home") { HomeScreen(navController) }
        composable("page1") { DataUserScreen(navController, viewModel) }
        composable("page2") { UnitKerjaScreen(navController, viewModel) }
        composable("page3") { DataAlatScreen(navController, viewModel) }
        composable("page4") { AmvAfdelingScreen(navController, viewModel) }
        composable("page5") { AmvBlokScreen(navController, viewModel) }
        composable("page6") { ObjekKerjaScreen(navController, viewModel) }
        composable("page7") { StatPekerjaanScreen(navController, viewModel) }
        composable("page8") { JamKerjaAlatScreen(navController, viewModel) }
        composable("page9") { KendalaOpScreen(navController, viewModel) }
        composable("page10") { DokumentasiAlatScreen(navController, viewModel) }
    }
}