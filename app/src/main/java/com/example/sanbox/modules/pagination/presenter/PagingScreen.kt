package com.example.sanbox.modules.pagination.presenter

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems

@Composable
fun PagingScreen(viewModel: PagingViewModel = hiltViewModel()) {
    val list = viewModel.pagingData.collectAsLazyPagingItems()

    when (list.loadState.refresh) {
        is LoadState.Error -> {
            Text("Error :p")
        }
        is LoadState.NotLoading -> {
            LazyColumn(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(list.itemCount) {
                    list[it]?.let {
                        Text(it.breed)
                    }
                }
            }
        }

        is LoadState.Loading -> {
            Text("Loading")
        }
    }
}