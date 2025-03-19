package com.example.infrastructure_monitoring_app.ui.page4

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AmvAfdelingViewModel : ViewModel() {
    private val _afdeling = MutableStateFlow("")
    val afdeling: StateFlow<String> = _afdeling

    fun onAfdelingChange(newAfdeling: String) {
        _afdeling.value = newAfdeling
    }
}