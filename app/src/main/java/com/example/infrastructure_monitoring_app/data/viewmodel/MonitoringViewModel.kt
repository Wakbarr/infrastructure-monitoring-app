package com.example.infrastructure_monitoring_app.data.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.infrastructure_monitoring_app.data.AppDatabase
import com.example.infrastructure_monitoring_app.data.entities.MonitoringData
import com.example.infrastructure_monitoring_app.repository.MonitoringRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class MonitoringViewModel(private val repository: MonitoringRepository) : ViewModel() {
    // Page 1
    val tanggal = MutableStateFlow("")
    val namaOperator = MutableStateFlow("")
    val noHpOperator = MutableStateFlow("")
    val namaVendor = MutableStateFlow("")
    val regionalPage1 = MutableStateFlow("")
    val isNoHpValid = MutableStateFlow(true)

    // Page 2
    val regionalPage2 = MutableStateFlow("")

    // Page 3
    val distrik = MutableStateFlow("")
    val jenisAlatBerat = MutableStateFlow("")
    val merkTypeAlat = MutableStateFlow("")
    val status = MutableStateFlow("")
    val kondisi = MutableStateFlow("")

    // Page 4
    val afdeling = MutableStateFlow("")

    // Page 5
    val blok = MutableStateFlow("")

    // Page 6
    val objekKerja = MutableStateFlow("")

    // Page 7
    val pekerjaan = MutableStateFlow("")

    // Page 8
    val jamKerja = MutableStateFlow("")
    val fotoJktAwal = MutableStateFlow("")
    val bbm = MutableStateFlow("")
    val fotoBonBbm = MutableStateFlow("")
    val realisasiKerja = MutableStateFlow("")
    val isJamKerjaValid = MutableStateFlow(true)
    val isBbmValid = MutableStateFlow(true)

    // Page 9
    val kendalaOperasional = MutableStateFlow("")

    // Page 10
    val dokumentasiSebelum = MutableStateFlow("")
    val dokumentasiSesudah = MutableStateFlow("")

    // Validasi Input
    fun onNoHpOperatorChange(newNoHp: String) {
        noHpOperator.value = newNoHp
        isNoHpValid.value = newNoHp.length in 10..13 && newNoHp.all { it.isDigit() }
    }

    fun onJamKerjaChange(newJamKerja: String) {
        jamKerja.value = newJamKerja
        isJamKerjaValid.value = newJamKerja.toFloatOrNull()?.let { it > 0 } ?: false
    }

    fun onBbmChange(newBbm: String) {
        bbm.value = newBbm
        isBbmValid.value = newBbm.toFloatOrNull()?.let { it > 0 } ?: false
    }

    private fun isDataValid(): Boolean {
        return isNoHpValid.value && isJamKerjaValid.value && isBbmValid.value
    }

    // Simpan ke database
    fun saveData() {
        if (!isDataValid()) return

        viewModelScope.launch {
            val data = MonitoringData(
                tanggal = tanggal.value,
                namaOperator = namaOperator.value,
                noHpOperator = noHpOperator.value,
                namaVendor = namaVendor.value,
                regionalPage1 = regionalPage1.value,
                regionalPage2 = regionalPage2.value,
                distrik = distrik.value,
                jenisAlatBerat = jenisAlatBerat.value,
                merkTypeAlat = merkTypeAlat.value,
                status = status.value,
                kondisi = kondisi.value,
                afdeling = afdeling.value,
                blok = blok.value,
                objekKerja = objekKerja.value,
                pekerjaan = pekerjaan.value,
                jamKerja = jamKerja.value,
                fotoJktAwal = fotoJktAwal.value,
                bbm = bbm.value,
                fotoBonBbm = fotoBonBbm.value,
                realisasiKerja = realisasiKerja.value,
                kendalaOperasional = kendalaOperasional.value,
                dokumentasiSebelum = dokumentasiSebelum.value,
                dokumentasiSesudah = dokumentasiSesudah.value
            )
            repository.insert(data)
        }
    }

    companion object {
        fun create(context: Context): MonitoringViewModel {
            val database = AppDatabase.getDatabase(context)
            val repository = MonitoringRepository(database.monitoringDao())
            return MonitoringViewModel(repository)
        }
    }
}
