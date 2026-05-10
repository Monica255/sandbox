package com.example.sanbox.modules.others.presenter

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun OthersScreen(viewModel: OthersViewModel = hiltViewModel()) {
    Content4()
}

@Composable
fun Content4() {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val enabled by remember {
        derivedStateOf {
            username.isNotEmpty() && password.isNotEmpty()
        }
    }
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = username,
            placeholder = {
                Text("Username")
            },
            onValueChange = {
                username = it
            }
        )
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = password,
            placeholder = {
                Text("Password")
            },
            onValueChange = {
                password = it
            }
        )
        Button(enabled)
    }
}

@Composable
fun Button(
    enabled: Boolean,
) {
    println(enabled)
    Button(
        modifier = Modifier.fillMaxWidth(),
        onClick = {

        },
        enabled = enabled
    ) {
        Text("Click")
    }
}

@Composable
fun Content3() {
    val listState = rememberLazyListState()
    val showButton by remember { derivedStateOf { listState.firstVisibleItemIndex > 0 } }
    Box(
        modifier = Modifier.padding(16.dp)
    ) {
        LazyColumn(
            state = listState,
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(100) {
                Text(
                    text = "Item $it",
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
            }
        }
        println(showButton)
        if (showButton) {
            Button(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(8.dp),
                onClick = {}
            ) {
                Text("Top")
            }
        }
    }
}

@Composable
fun Content2() {
    var counter by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 8.dp, horizontal = 24.dp)
    ) {
        Text("Counter $counter")
        Button(
            onClick = {
                counter++
            }
        ) {
            Text("Click")
        }
        Timer(counter)
    }
}

@Composable
fun Timer(counter: Int) {
    val updatedCounter by rememberUpdatedState(counter)
    var text by remember { mutableStateOf("") }
    LaunchedEffect(Unit) {
        delay(5000)
        text = updatedCounter.toString()
    }
    Text(text)
}

@Composable
fun Content(viewModel: OthersViewModel) {
    val lifecycleOwner = LocalLifecycleOwner.current

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_RESUME -> println("resumed")
                Lifecycle.Event.ON_PAUSE -> println("paused")
                else -> {}
            }
        }

        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    val state by viewModel.state.collectAsState()
    var q by remember { mutableStateOf("") }

    LaunchedEffect(q) {
        val cleaned = q.trim()
        if (cleaned.isBlank()) return@LaunchedEffect
        delay(500)
        viewModel.fetchData(cleaned)
    }

    val snackbarHostState = remember {
        SnackbarHostState()
    }
    val scope = rememberCoroutineScope()
    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(vertical = 8.dp, horizontal = 24.dp)
                .padding(paddingValues),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    scope.launch {
                        snackbarHostState.showSnackbar("yow")
                    }
                }
            ) {
                Text("Click")
            }
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = q,
                onValueChange = {
                    q = it
                }
            )
            if (state.isLoading) {
                Text("Loading data...")
                return@Column
            }

            state.data.forEach {
                Text(it)
            }
        }
    }
}