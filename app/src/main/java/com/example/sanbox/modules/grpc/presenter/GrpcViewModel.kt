package com.example.sanbox.modules.grpc.presenter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sanbox.modules.grpc.data.PubSubRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import pubsub.Pubsub

class GrpcViewModel : ViewModel() {

    private val repo = PubSubRepository()

    private val _events = MutableStateFlow<List<Pubsub.Event>>(emptyList())
    val events = _events.asStateFlow()

    fun subscribeTo(topic: String) {
        viewModelScope.launch {
            repo.subscribe(topic).collect { event ->
                _events.value = listOf(event) + _events.value
            }
        }
    }

    fun publish(topic: String, message: String) {
        viewModelScope.launch {
            repo.publish(topic, message)
        }
    }

    override fun onCleared() {
        repo.shutdown()
        super.onCleared()
    }
}