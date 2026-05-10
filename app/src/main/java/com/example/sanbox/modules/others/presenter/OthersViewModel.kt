package com.example.sanbox.modules.others.presenter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sanbox.modules.others.data.OthersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OthersViewModel @Inject constructor(
    private val repository: OthersRepository,
) : ViewModel() {

    data class UiState(
        val q: String = "",
        val isLoading: Boolean = false,
        val data: List<String> = emptyList(),
    )

    sealed interface Event{
        data object ShowToast : Event
    }

    private val _state = MutableStateFlow<UiState>(UiState())
    val state = _state.asStateFlow()

    private val _event = MutableSharedFlow<Event>()
    val event = _event.asSharedFlow()

    init {
        observeQuery()
    }

    fun emit(event: Event){
        viewModelScope.launch {
            _event.emit(event)
        }
    }
    private fun setLoading(isShown: Boolean) {
        _state.update {
            it.copy(
                isLoading = isShown
            )
        }
    }

    fun updateQuery(q: String) {
        _state.update {
            it.copy(
                q = q
            )
        }
    }

    @OptIn(FlowPreview::class)
    private fun observeQuery() {
        viewModelScope.launch {
            state.map { it.q }
                .debounce(1000)
                .distinctUntilChanged()
                .collectLatest {
                    if (it.isBlank()) {
                        _state.update {
                            it.copy(
                                data = emptyList()
                            )
                        }
                        return@collectLatest
                    }
                    fetchData(it)
                }
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