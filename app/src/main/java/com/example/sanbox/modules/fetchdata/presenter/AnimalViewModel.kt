package com.example.sanbox.modules.fetchdata.presenter

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.network.data.animal.repository.AnimalRepository
import com.example.sanbox.modules.fetchdata.utils.AnimalMapper.mapToDomain
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
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

    fun setLodaing(isLoading: Boolean){
        _uiState.update { it.copy(isLoading = isLoading) }
    }

    fun fetchData(){
        viewModelScope.launch {
            try {
                setLodaing(true)
                val response = repository.getAnimal()
//                Log.d("AnimalViewModel", response.toString())
                _uiState.update { it ->
                    it.copy(
                        list = response.data.map { it.mapToDomain() }
                    )
                }
            }catch (e: CancellationException){
                throw  e
            } catch (e: Exception){
                // TODO
//                Log.d("AnimalViewModel", e.message.toString())
            }finally {
                setLodaing(false)
            }
        }
    }
}