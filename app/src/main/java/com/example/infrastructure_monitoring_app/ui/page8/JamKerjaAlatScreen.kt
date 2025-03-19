package com.example.infrastructure_monitoring_app.ui.page8

import android.Manifest
import android.app.TimePickerDialog
import android.graphics.Bitmap
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
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
    val jamStart by viewModel.jamStart.collectAsState()
    val jamMulaiBekerja by viewModel.jamMulaiBekerja.collectAsState()
    val jamSelesaiBekerja by viewModel.jamSelesaiBekerja.collectAsState()
    val jamStop by viewModel.jamStop.collectAsState()
    val jamKerja by viewModel.jamKerja.collectAsState()
    val fotoJktAwal by viewModel.fotoJktAwal.collectAsState()
    val bbm by viewModel.bbm.collectAsState()
    val fotoBonBbm by viewModel.fotoBonBbm.collectAsState()
    val realisasiKerja by viewModel.realisasiKerja.collectAsState()
    val isJamKerjaValid by viewModel.isJamKerjaValid.collectAsState()
    val isBbmValid by viewModel.isBbmValid.collectAsState()
    val context = LocalContext.current

    // Launcher untuk mengambil foto
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
            // Izin kamera diberikan
        }
    }

    // Fungsi untuk menampilkan TimePickerDialog
    fun showTimePicker(onTimeSelected: (String) -> Unit) {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)
        TimePickerDialog(context, { _, selectedHour, selectedMinute ->
            val time = String.format("%02d:%02d", selectedHour, selectedMinute)
            onTimeSelected(time)
        }, hour, minute, true).show()
    }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        // Jam Start/Hidup Tracktor
        CustomTextField(
            value = jamStart,
            onValueChange = {},
            label = "Jam Start/Hidup Tracktor",
            readOnly = true,
            trailingIcon = {
                IconButton(onClick = {
                    showTimePicker { time -> viewModel.jamStart.value = time }
                }) {
                    Icon(Icons.Default.AddCircle, contentDescription = "Pilih waktu")
                }
            }
        )

        // Jam Mulai Bekerja
        CustomTextField(
            value = jamMulaiBekerja,
            onValueChange = {},
            label = "Jam Mulai Bekerja",
            readOnly = true,
            trailingIcon = {
                IconButton(onClick = {
                    showTimePicker { time -> viewModel.jamMulaiBekerja.value = time }
                }) {
                    Icon(Icons.Default.AddCircle, contentDescription = "Pilih waktu")
                }
            }
        )

        // Jam Selesai Bekerja
        CustomTextField(
            value = jamSelesaiBekerja,
            onValueChange = {},
            label = "Jam Selesai Bekerja",
            readOnly = true,
            trailingIcon = {
                IconButton(onClick = {
                    showTimePicker { time -> viewModel.jamSelesaiBekerja.value = time }
                }) {
                    Icon(Icons.Default.AddCircle, contentDescription = "Pilih waktu")
                }
            }
        )

        // Jam Stop/Mati Mesin
        CustomTextField(
            value = jamStop,
            onValueChange = {},
            label = "Jam Stop/Mati Mesin",
            readOnly = true,
            trailingIcon = {
                IconButton(onClick = {
                    showTimePicker { time -> viewModel.jamStop.value = time }
                }) {
                    Icon(Icons.Default.AddCircle, contentDescription = "Pilih waktu")
                }
            }
        )

        // Jam Kerja Efektif Alat Berat
        CustomTextField(
            value = jamKerja,
            onValueChange = {},
            label = "Jam Kerja Efektif Alat Berat (HH:mm)",
            readOnly = true,
            isError = !isJamKerjaValid,
            errorMessage = if (!isJamKerjaValid) "Harus lebih dari 00:00" else null,
            trailingIcon = {
                IconButton(onClick = {
                    showTimePicker { time -> viewModel.setJamKerja(time) }
                }) {
                    Icon(Icons.Default.AddCircle, contentDescription = "Pilih waktu")
                }
            }
        )

        // Foto JKT Awal
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

        // BBM (Liter)
        CustomTextField(
            value = bbm,
            onValueChange = { viewModel.onBbmChange(it) },
            label = "BBM (Liter)",
            isError = !isBbmValid,
            errorMessage = if (!isBbmValid) "Harus angka positif" else null,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
        )

        // Foto Bon BBM
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

        // Realisasi Kerja Alat (Mtr)
        CustomTextField(
            value = realisasiKerja,
            onValueChange = { viewModel.realisasiKerja.value = it },
            label = "Realisasi Kerja Alat (Mtr)"
        )

        // Tombol Next
        CustomButton(
            onClick = { navController.navigate("page9") },
            text = "Next",
            enabled = jamStart.isNotEmpty() && jamMulaiBekerja.isNotEmpty() &&
                    jamSelesaiBekerja.isNotEmpty() && jamStop.isNotEmpty() &&
                    jamKerja.isNotEmpty() && fotoJktAwal.isNotEmpty() && bbm.isNotEmpty() &&
                    fotoBonBbm.isNotEmpty() && realisasiKerja.isNotEmpty() &&
                    isJamKerjaValid && isBbmValid
        )
    }
}