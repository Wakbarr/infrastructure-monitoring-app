package com.example.infrastructure_monitoring_app.ui.page5

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AmvBlokViewModel : ViewModel() {
    private val _blok = MutableStateFlow("")
    val blok: StateFlow<String> = _blok

    fun onBlokChange(newBlok: String) {
        _blok.value = newBlok
    }
}