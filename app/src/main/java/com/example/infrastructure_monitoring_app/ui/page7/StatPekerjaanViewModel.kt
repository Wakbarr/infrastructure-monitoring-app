package com.example.infrastructure_monitoring_app.ui.page7

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class StatPekerjaanViewModel : ViewModel() {
    private val _pekerjaan = MutableStateFlow("")
    val pekerjaan: StateFlow<String> = _pekerjaan

    fun onPekerjaanChange(newPekerjaan: String) {
        _pekerjaan.value = newPekerjaan
    }
}