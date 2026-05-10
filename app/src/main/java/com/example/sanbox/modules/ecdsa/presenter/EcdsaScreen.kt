package com.example.sanbox.modules.ecdsa.presenter

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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun EcdsaScreen(viewModel: EcdsaViewModel = viewModel()) {

    var input by remember { mutableStateOf("") }
    val signature by viewModel.signature.collectAsStateWithLifecycle()

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
                viewModel.sign(input)
            }
        ) {
            Text(text = "Click")
        }
        Text(
            text = signature
        )
    }

}