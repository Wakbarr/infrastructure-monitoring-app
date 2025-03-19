package com.example.infrastructure_monitoring_app.ui.page10

import android.Manifest
import android.graphics.Bitmap
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.infrastructure_monitoring_app.data.viewmodel.MonitoringViewModel
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun DokumentasiAlatScreen(navController: NavController, viewModel: MonitoringViewModel) {
    val dokumentasiSebelum by viewModel.dokumentasiSebelum.collectAsState()
    val dokumentasiSesudah by viewModel.dokumentasiSesudah.collectAsState()
    val context = LocalContext.current

    val launcherSebelum = rememberLauncherForActivityResult(ActivityResultContracts.TakePicturePreview()) { bitmap ->
        bitmap?.let {
            val timestamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
            val file = File(context.cacheDir, "sebelum_$timestamp.jpg")
            file.outputStream().use { out ->
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out)
            }
            viewModel.dokumentasiSebelum.value = file.absolutePath
        }
    }

    val launcherSesudah = rememberLauncherForActivityResult(ActivityResultContracts.TakePicturePreview()) { bitmap ->
        bitmap?.let {
            val timestamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
            val file = File(context.cacheDir, "sesudah_$timestamp.jpg")
            file.outputStream().use { out ->
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out)
            }
            viewModel.dokumentasiSesudah.value = file.absolutePath
        }
    }

    val permissionLauncher = rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
        if (!isGranted) {
            // Handle permission denied
        }
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Button(onClick = {
            permissionLauncher.launch(Manifest.permission.CAMERA)
            launcherSebelum.launch(null)
        }) { Text("Ambil Foto Sebelum") }
        if (dokumentasiSebelum.isNotEmpty()) {
            Image(
                painter = rememberAsyncImagePainter(dokumentasiSebelum),
                contentDescription = "Dokumentasi Sebelum",
                modifier = Modifier.size(100.dp)
            )
            Text("Taken: ${File(dokumentasiSebelum).name.split("_")[1].removeSuffix(".jpg")}")
        }

        Button(onClick = {
            permissionLauncher.launch(Manifest.permission.CAMERA)
            launcherSesudah.launch(null)
        }) { Text("Ambil Foto Sesudah") }
        if (dokumentasiSesudah.isNotEmpty()) {
            Image(
                painter = rememberAsyncImagePainter(dokumentasiSesudah),
                contentDescription = "Dokumentasi Sesudah",
                modifier = Modifier.size(100.dp)
            )
            Text("Taken: ${File(dokumentasiSesudah).name.split("_")[1].removeSuffix(".jpg")}")
        }

        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                viewModel.saveData()
                navController.popBackStack("home", inclusive = false)
            },
            enabled = dokumentasiSebelum.isNotEmpty() && dokumentasiSesudah.isNotEmpty()
        ) { Text("Kirim") }
    }
}