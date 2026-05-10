package com.example.wear.presentation

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.common.constants.WearConstant
import com.google.android.gms.wearable.Wearable
import dagger.hilt.android.internal.Contexts
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    @ApplicationContext private val context: Context
) : ViewModel() {
    private val _message = MutableStateFlow<String?>(null)
    val message = _message.asStateFlow()

    init {
        listener()
    }

    fun listener() {
        Wearable.getMessageClient(context).addListener {
            if (it.path == WearConstant.MESSAGE_PATH) {
                val receivedText = String(it.data)
                Log.d("wearos", receivedText)
                _message.value = receivedText
            }
        }
    }

    fun sendMessageToPhone(text: String) {
        val nodeClient = Wearable.getNodeClient(context)
        val messageClient = Wearable.getMessageClient(context)

        nodeClient.connectedNodes.addOnSuccessListener { nodes ->
            nodes.forEach { node ->
                messageClient.sendMessage(
                    node.id,
                    WearConstant.MESSAGE_PATH,
                    text.toByteArray()
                )
            }
        }
    }
}