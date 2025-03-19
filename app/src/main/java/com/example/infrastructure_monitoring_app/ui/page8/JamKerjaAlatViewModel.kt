package com.example.infrastructure_monitoring_app.ui.page8

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class JamKerjaAlatViewModel : ViewModel() {
    private val _jamKerja = MutableStateFlow("")
    val jamKerja: StateFlow<String> = _jamKerja

    private val _fotoJktAwal = MutableStateFlow("")
    val fotoJktAwal: StateFlow<String> = _fotoJktAwal

    private val _bbm = MutableStateFlow("")
    val bbm: StateFlow<String> = _bbm

    private val _fotoBonBbm = MutableStateFlow("")
    val fotoBonBbm: StateFlow<String> = _fotoBonBbm

    private val _realisasiKerja = MutableStateFlow("")
    val realisasiKerja: StateFlow<String> = _realisasiKerja

    private val _isJamKerjaValid = MutableStateFlow(true)
    val isJamKerjaValid: StateFlow<Boolean> = _isJamKerjaValid

    private val _isBbmValid = MutableStateFlow(true)
    val isBbmValid: StateFlow<Boolean> = _isBbmValid

    fun onJamKerjaChange(newJamKerja: String) {
        _jamKerja.value = newJamKerja
        _isJamKerjaValid.value = newJamKerja.toFloatOrNull()?.let { it > 0 } ?: false
    }

    fun onFotoJktAwalChange(newFoto: String) {
        _fotoJktAwal.value = newFoto
    }

    fun onBbmChange(newBbm: String) {
        _bbm.value = newBbm
        _isBbmValid.value = newBbm.toFloatOrNull()?.let { it > 0 } ?: false
    }

    fun onFotoBonBbmChange(newFoto: String) {
        _fotoBonBbm.value = newFoto
    }

    fun onRealisasiKerjaChange(newRealisasi: String) {
        _realisasiKerja.value = newRealisasi
    }
}