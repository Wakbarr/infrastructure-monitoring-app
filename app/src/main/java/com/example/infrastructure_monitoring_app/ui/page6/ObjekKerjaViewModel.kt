package com.example.infrastructure_monitoring_app.ui.page6

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ObjekKerjaViewModel : ViewModel() {
    private val _objekKerja = MutableStateFlow("")
    val objekKerja: StateFlow<String> = _objekKerja

    fun onObjekKerjaChange(newObjekKerja: String) {
        _objekKerja.value = newObjekKerja
    }
}