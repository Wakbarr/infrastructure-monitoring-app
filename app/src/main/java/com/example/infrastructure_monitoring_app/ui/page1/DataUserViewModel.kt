package com.example.infrastructure_monitoring_app.ui.page1

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.text.SimpleDateFormat
import java.util.*

class DataUserViewModel : ViewModel() {
    private val _tanggal = MutableStateFlow(SimpleDateFormat("dd/MM/yyyy").format(Date()))
    val tanggal: StateFlow<String> = _tanggal

    private val _namaOperator = MutableStateFlow("")
    val namaOperator: StateFlow<String> = _namaOperator

    private val _noHpOperator = MutableStateFlow("")
    val noHpOperator: StateFlow<String> = _noHpOperator

    private val _namaVendor = MutableStateFlow("")
    val namaVendor: StateFlow<String> = _namaVendor

    private val _regional = MutableStateFlow("")
    val regional: StateFlow<String> = _regional

    private val _isNoHpValid = MutableStateFlow(true)
    val isNoHpValid: StateFlow<Boolean> = _isNoHpValid

    fun onTanggalChange(newTanggal: String) {
        _tanggal.value = newTanggal
    }

    fun onNamaOperatorChange(newNama: String) {
        _namaOperator.value = newNama
    }

    fun onNoHpOperatorChange(newNoHp: String) {
        _noHpOperator.value = newNoHp
        _isNoHpValid.value = newNoHp.length in 10..13 && newNoHp.all { it.isDigit() }
    }

    fun onNamaVendorChange(newVendor: String) {
        _namaVendor.value = newVendor
    }

    fun onRegionalChange(newRegional: String) {
        _regional.value = newRegional
    }
}