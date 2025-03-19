package com.example.infrastructure_monitoring_app.ui.page3

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class DataAlatViewModel : ViewModel() {
    private val _distrik = MutableStateFlow("")
    val distrik: StateFlow<String> = _distrik

    private val _jenisAlatBerat = MutableStateFlow("")
    val jenisAlatBerat: StateFlow<String> = _jenisAlatBerat

    private val _merkTypeAlat = MutableStateFlow("")
    val merkTypeAlat: StateFlow<String> = _merkTypeAlat

    private val _status = MutableStateFlow("")
    val status: StateFlow<String> = _status

    private val _kondisi = MutableStateFlow("")
    val kondisi: StateFlow<String> = _kondisi

    fun onDistrikChange(newDistrik: String) {
        _distrik.value = newDistrik
    }

    fun onJenisAlatBeratChange(newJenis: String) {
        _jenisAlatBerat.value = newJenis
    }

    fun onMerkTypeAlatChange(newMerkType: String) {
        _merkTypeAlat.value = newMerkType
    }

    fun onStatusChange(newStatus: String) {
        _status.value = newStatus
    }

    fun onKondisiChange(newKondisi: String) {
        _kondisi.value = newKondisi
    }
}