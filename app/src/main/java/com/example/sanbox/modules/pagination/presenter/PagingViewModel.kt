package com.example.sanbox.modules.pagination.presenter

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.network.data.pagination.repo.PagingRepository
import com.example.sanbox.modules.pagination.paging.BreedPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.asFlow
import javax.inject.Inject

@HiltViewModel
class PagingViewModel@Inject constructor(
    private val repository: PagingRepository
) : ViewModel(){

    val pagingData = Pager(
        config = PagingConfig(
            pageSize = 10,
        ),
        pagingSourceFactory = {
            BreedPagingSource(repository)
        }
    ).flow
}