package com.example.sanbox.modules.others.presenter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sanbox.modules.others.data.OthersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OthersViewModel @Inject constructor(
    private val repository: OthersRepository,
) : ViewModel() {

    data class UiState(
        val isLoading: Boolean = false,
        val data: List<String> = emptyList(),
    )

    private val _state = MutableStateFlow<UiState>(UiState())
    val state = _state.asStateFlow()

    private fun setLoading(isShown: Boolean) {
        _state.update {
            it.copy(
                isLoading = isShown
            )
        }
    }

    fun fetchData(q: String) {
        viewModelScope.launch {
            setLoading(true)
            val data = repository.getProducts(q)
            _state.update {
                it.copy(
                    data = data
                )
            }
            setLoading(false)
        }
    }
}