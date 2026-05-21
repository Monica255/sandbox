package com.example.sanbox.modules.fetchdata.presenter

import androidx.lifecycle.ViewModel
import com.basicutil.safeLaunch
import com.example.network.data.animal.repository.AnimalRepository
import com.example.sanbox.modules.fetchdata.utils.AnimalMapper.mapToDomain
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject
@HiltViewModel
class AnimalViewModel @Inject constructor(
    private val repository: AnimalRepository
) : ViewModel(){

    data class UiState(
        val isLoading: Boolean = false,
        val list: List<Animal> = emptyList()
    )

    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    fun setLoading(isLoading: Boolean){
        _uiState.update { it.copy(isLoading = isLoading) }
    }

    fun fetchData(){
        safeLaunch(
            dispatcher = Dispatchers.IO,
            onLoading = ::setLoading,
        ){
            val response = repository.getAnimal()
            _uiState.update { it ->
                it.copy(
                    list = response.data.map { it.mapToDomain() }
                )
            }
        }
    }
}