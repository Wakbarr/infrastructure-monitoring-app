package com.example.infrastructure_monitoring_app.ui.page8

import android.Manifest
import android.graphics.Bitmap
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
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
fun JamKerjaAlatScreen(navController: NavController, viewModel: MonitoringViewModel) {
    val jamKerja by viewModel.jamKerja.collectAsState()
    val fotoJktAwal by viewModel.fotoJktAwal.collectAsState()
    val bbm by viewModel.bbm.collectAsState()
    val fotoBonBbm by viewModel.fotoBonBbm.collectAsState()
    val realisasiKerja by viewModel.realisasiKerja.collectAsState()
    val isJamKerjaValid by viewModel.isJamKerjaValid.collectAsState()
    val isBbmValid by viewModel.isBbmValid.collectAsState()
    val context = LocalContext.current

    val launcherJkt = rememberLauncherForActivityResult(ActivityResultContracts.TakePicturePreview()) { bitmap ->
        bitmap?.let {
            val timestamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
            val file = File(context.cacheDir, "jkt_$timestamp.jpg")
            file.outputStream().use { out ->
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out)
            }
            viewModel.fotoJktAwal.value = file.absolutePath
        }
    }

    val launcherBon = rememberLauncherForActivityResult(ActivityResultContracts.TakePicturePreview()) { bitmap ->
        bitmap?.let {
            val timestamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
            val file = File(context.cacheDir, "bon_$timestamp.jpg")
            file.outputStream().use { out ->
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out)
            }
            viewModel.fotoBonBbm.value = file.absolutePath
        }
    }

    // Request camera permission
    val permissionLauncher = rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
        if (isGranted) {
            // Permission granted, proceed with camera
        } else {
            // Handle permission denied
        }
    }

    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(
            value = jamKerja,
            onValueChange = { viewModel.onJamKerjaChange(it) },
            label = { Text("Jam Kerja Tracktor") },
            isError = !isJamKerjaValid,
            modifier = Modifier.fillMaxWidth()
        )
        if (!isJamKerjaValid) Text("Harus angka positif", color = MaterialTheme.colors.error)

        Button(onClick = {
            permissionLauncher.launch(Manifest.permission.CAMERA)
            launcherJkt.launch(null)
        }) { Text("Ambil Foto JKT Awal") }
        if (fotoJktAwal.isNotEmpty()) {
            Image(
                painter = rememberAsyncImagePainter(fotoJktAwal),
                contentDescription = "Foto JKT",
                modifier = Modifier.size(100.dp)
            )
            Text("Taken: ${File(fotoJktAwal).name.split("_")[1].removeSuffix(".jpg")}")
        }

        OutlinedTextField(
            value = bbm,
            onValueChange = { viewModel.onBbmChange(it) },
            label = { Text("BBM (Liter)") },
            isError = !isBbmValid,
            modifier = Modifier.fillMaxWidth()
        )
        if (!isBbmValid) Text("Harus angka positif", color = MaterialTheme.colors.error)

        Button(onClick = {
            permissionLauncher.launch(Manifest.permission.CAMERA)
            launcherBon.launch(null)
        }) { Text("Ambil Foto Bon BBM") }
        if (fotoBonBbm.isNotEmpty()) {
            Image(
                painter = rememberAsyncImagePainter(fotoBonBbm),
                contentDescription = "Foto Bon",
                modifier = Modifier.size(100.dp)
            )
            Text("Taken: ${File(fotoBonBbm).name.split("_")[1].removeSuffix(".jpg")}")
        }

        OutlinedTextField(
            value = realisasiKerja,
            onValueChange = { viewModel.realisasiKerja.value = it },
            label = { Text("Realisasi Kerja Alat (Mtr)") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { navController.navigate("page9") },
            enabled = jamKerja.isNotEmpty() && fotoJktAwal.isNotEmpty() && bbm.isNotEmpty() && fotoBonBbm.isNotEmpty() && realisasiKerja.isNotEmpty() && isJamKerjaValid && isBbmValid
        ) { Text("Next") }
    }
}