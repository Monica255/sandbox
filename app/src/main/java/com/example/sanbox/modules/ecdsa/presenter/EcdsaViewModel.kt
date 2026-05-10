package com.example.sanbox.modules.ecdsa.presenter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sanbox.modules.ecdsa.ecdsa.EcdsaSigner
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class EcdsaViewModel: ViewModel() {

    private val _signature = MutableStateFlow("")
    val signature = _signature.asStateFlow()

    init {
        EcdsaSigner.generateKeyIfNeeded()
    }
    fun sign(data: String){
        viewModelScope.launch {
            val signature = EcdsaSigner.sign(data)
            _signature.value = signature.toString()
        }
    }
}