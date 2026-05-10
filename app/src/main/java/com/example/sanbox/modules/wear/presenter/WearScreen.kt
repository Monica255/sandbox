package com.example.sanbox.modules.wear.presenter

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun WearScreen(viewModel: WearViewModel = hiltViewModel()) {

    var input by remember { mutableStateOf("") }
    val message by viewModel.message.collectAsStateWithLifecycle()
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        TextField(
            value = input,
            modifier = Modifier.fillMaxWidth(),
            onValueChange = {
                input = it
            }
        )
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                viewModel.sendMessageToWatch(input)
            }
        ) {
            Text(text = "Click")
        }
        Text(
            text = "Message from watch: $message"
        )
    }
}