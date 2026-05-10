package com.example.sanbox.modules.grpc.presenter

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.sanbox.modules.navigation.Modules

@Composable
fun GrpcScreen(
    module: Modules?,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = module?.moduleName ?: "",
            modifier = Modifier.padding(24.dp),
            style = MaterialTheme.typography.headlineSmall
        )
        GrpcContent()
    }
}

@Composable
fun GrpcContent(viewModel: GrpcViewModel = hiltViewModel()) {
    val events by viewModel.events.collectAsStateWithLifecycle()
    var message by remember { mutableStateOf("") }
    val topic = "news"

    LaunchedEffect(Unit) {
        viewModel.subscribeTo(topic)
    }

    Column(modifier = Modifier.padding(horizontal = 24.dp)) {
        Row {
            TextField(
                value = message,
                onValueChange = { message = it },
                modifier = Modifier.weight(1f),
                placeholder = { Text("Type a message") }
            )
            Button(onClick = {
                viewModel.publish(topic, message)
                message = ""
            }) {
                Text("Send")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(items = events) { event ->
                Card(modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text("#${event.topic}", style = MaterialTheme.typography.labelSmall)
                        Text(event.data, style = MaterialTheme.typography.bodyLarge)
                        Text(
                            event.id,
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.Gray
                        )
                    }
                }
            }
        }
    }
}