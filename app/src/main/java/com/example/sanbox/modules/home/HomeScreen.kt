package com.example.sanbox.modules.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.sanbox.modules.navigation.Modules

@Composable
fun HomeScreen(
    onNavigateToDetail: (Int) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Modules.entries.forEach {
            Button(
                onClick = { onNavigateToDetail(it.id) },
                modifier = Modifier.padding(horizontal = 12.dp).fillMaxWidth()
            ) {
                Text(it.moduleName)
            }
        }
    }
}