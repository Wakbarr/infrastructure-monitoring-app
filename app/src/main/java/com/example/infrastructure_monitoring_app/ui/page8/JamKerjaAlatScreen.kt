package com.example.infrastructure_monitoring_app.ui.page8

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
import com.example.infrastructure_monitoring_app.ui.CustomTextField
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

    val permissionLauncher = rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
        if (isGranted) {
            // Permission granted
        }
    }

    Column(modifier = Modifier.padding(16.dp)) {
        CustomTextField(
            value = jamKerja,
            onValueChange = { viewModel.onJamKerjaChange(it) },
            label = "Jam Kerja Tracktor",
            isError = !isJamKerjaValid,
            errorMessage = if (!isJamKerjaValid) "Harus angka positif" else null
        )

        CustomButton(
            onClick = {
                permissionLauncher.launch(Manifest.permission.CAMERA)
                launcherJkt.launch(null)
            },
            text = "Ambil Foto JKT Awal"
        )
        if (fotoJktAwal.isNotEmpty()) {
            Image(
                painter = rememberAsyncImagePainter(fotoJktAwal),
                contentDescription = "Foto JKT",
                modifier = Modifier
                    .size(120.dp)
                    .padding(top = 8.dp)
                    .border(1.dp, MaterialTheme.colors.primary, MaterialTheme.shapes.small)
            )
            Text(
                text = "Taken: ${File(fotoJktAwal).name.split("_")[1].removeSuffix(".jpg")}",
                style = MaterialTheme.typography.caption,
                modifier = Modifier.padding(top = 4.dp)
            )
        }

        CustomTextField(
            value = bbm,
            onValueChange = { viewModel.onBbmChange(it) },
            label = "BBM (Liter)",
            isError = !isBbmValid,
            errorMessage = if (!isBbmValid) "Harus angka positif" else null
        )

        CustomButton(
            onClick = {
                permissionLauncher.launch(Manifest.permission.CAMERA)
                launcherBon.launch(null)
            },
            text = "Ambil Foto Bon BBM"
        )
        if (fotoBonBbm.isNotEmpty()) {
            Image(
                painter = rememberAsyncImagePainter(fotoBonBbm),
                contentDescription = "Foto Bon",
                modifier = Modifier
                    .size(120.dp)
                    .padding(top = 8.dp)
                    .border(1.dp, MaterialTheme.colors.primary, MaterialTheme.shapes.small)
            )
            Text(
                text = "Taken: ${File(fotoBonBbm).name.split("_")[1].removeSuffix(".jpg")}",
                style = MaterialTheme.typography.caption,
                modifier = Modifier.padding(top = 4.dp)
            )
        }

        CustomTextField(
            value = realisasiKerja,
            onValueChange = { viewModel.realisasiKerja.value = it },
            label = "Realisasi Kerja Alat (Mtr)"
        )

        CustomButton(
            onClick = { navController.navigate("page9") },
            text = "Next",
            enabled = jamKerja.isNotEmpty() && fotoJktAwal.isNotEmpty() && bbm.isNotEmpty() &&
                    fotoBonBbm.isNotEmpty() && realisasiKerja.isNotEmpty() && isJamKerjaValid && isBbmValid
        )
    }
}