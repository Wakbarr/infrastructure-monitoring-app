package com.example.infrastructure_monitoring_app.ui.page10

import android.Manifest
import android.graphics.Bitmap
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.infrastructure_monitoring_app.data.viewmodel.MonitoringViewModel
import com.example.infrastructure_monitoring_app.ui.CustomButton
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
        CustomButton(
            onClick = {
                permissionLauncher.launch(Manifest.permission.CAMERA)
                launcherSebelum.launch(null)
            },
            text = "Ambil Foto Sebelum"
        )
        if (dokumentasiSebelum.isNotEmpty()) {
            Image(
                painter = rememberAsyncImagePainter(dokumentasiSebelum),
                contentDescription = "Dokumentasi Sebelum",
                modifier = Modifier
                    .size(120.dp)
                    .padding(top = 8.dp)
                    .border(1.dp, MaterialTheme.colors.primary, MaterialTheme.shapes.small)
            )
            Text(
                text = "Taken: ${File(dokumentasiSebelum).name.split("_")[1].removeSuffix(".jpg")}",
                style = MaterialTheme.typography.caption,
                modifier = Modifier.padding(top = 4.dp)
            )
        }

        CustomButton(
            onClick = {
                permissionLauncher.launch(Manifest.permission.CAMERA)
                launcherSesudah.launch(null)
            },
            text = "Ambil Foto Sesudah"
        )
        if (dokumentasiSesudah.isNotEmpty()) {
            Image(
                painter = rememberAsyncImagePainter(dokumentasiSesudah),
                contentDescription = "Dokumentasi Sesudah",
                modifier = Modifier
                    .size(120.dp)
                    .padding(top = 8.dp)
                    .border(1.dp, MaterialTheme.colors.primary, MaterialTheme.shapes.small)
            )
            Text(
                text = "Taken: ${File(dokumentasiSesudah).name.split("_")[1].removeSuffix(".jpg")}",
                style = MaterialTheme.typography.caption,
                modifier = Modifier.padding(top = 4.dp)
            )
        }

        CustomButton(
            onClick = {
                viewModel.saveData()
                navController.popBackStack("home", inclusive = false)
            },
            text = "Kirim",
            enabled = dokumentasiSebelum.isNotEmpty() && dokumentasiSesudah.isNotEmpty()
        )
    }
}