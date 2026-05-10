package com.example.sanbox.modules.patterns.presenter

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
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
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.sanbox.modules.patterns.cor.Manager
import com.example.sanbox.modules.patterns.facade.Greeting
import com.example.sanbox.modules.patterns.strategy.PaymentContext
import kotlinx.coroutines.delay


@Composable
fun PatternsScreen(
    manager: Manager = Manager(),
    viewModel: PatternsViewModel = hiltViewModel()
) {

    var text by remember { mutableStateOf("") }
    var output by remember { mutableStateOf("") }

    var greetings by remember { mutableStateOf("") }

    var amountText by remember { mutableStateOf("") }


    LaunchedEffect(text) {
        output = manager.process(text)
    }

    LaunchedEffect(Unit) {
        greetings = viewModel.sayGreetings("Monica")
        amountText = viewModel.pay(100.00)
        delay(2000)
        viewModel.changeToEWallet()
        amountText = viewModel.pay(100.00)
    }

    Column(
        modifier = Modifier.padding(horizontal = 24.dp, vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            style = MaterialTheme.typography.headlineMedium,
            text = "Chain of Responsibility"
        )
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
        HorizontalDivider()
        Text(
            style = MaterialTheme.typography.headlineMedium,
            text = "Facade"
        )
        Text(
            text = greetings
        )
        HorizontalDivider()
        Text(
            style = MaterialTheme.typography.headlineMedium,
            text = "Strategy"
        )
        Text(
            text = amountText
        )
    }
}