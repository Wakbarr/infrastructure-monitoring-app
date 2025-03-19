package com.example.infrastructure_monitoring_app.ui.page2

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class UnitKerjaViewModel : ViewModel() {
    private val _regional = MutableStateFlow("")
    val regional: StateFlow<String> = _regional

    fun onRegionalChange(newRegional: String) {
        _regional.value = newRegional
    }
}