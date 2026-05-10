package com.example.sanbox.modules.cor.presenter

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.sanbox.modules.cor.Manager
import kotlinx.coroutines.delay


@Composable
fun CorScreen(manager: Manager = Manager()) {

    var text by remember { mutableStateOf("") }
    var output by remember { mutableStateOf("") }

    LaunchedEffect(text) {
        output = manager.process(text)
    }

    Column(
        modifier = Modifier.padding(horizontal = 24.dp, vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        TextField(
            value = text,
            onValueChange = { input ->
                text = input
            },
            modifier = Modifier.fillMaxWidth(),
        )
        Text(
            text = output
        )
    }
}