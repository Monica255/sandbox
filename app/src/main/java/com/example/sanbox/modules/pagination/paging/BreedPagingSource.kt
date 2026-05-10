package com.example.sanbox.modules.pagination.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.network.data.pagination.repo.PagingRepository
import com.example.sanbox.modules.pagination.model.Breed
import com.example.sanbox.modules.pagination.utils.PagingMapper.mapToDomain

class BreedPagingSource(private val repository: PagingRepository) : PagingSource<Int, Breed>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Breed> {
        val page = params.key ?: 1
        return try {
            val data = repository.getBreed(page = page, limit = 10)
            val list = data.data?.map { it.mapToDomain() } ?: emptyList()
            val nextKey = if (page != data.lastPage) page + 1 else null
            val prevKey = if (page == 1) null else page - 1
            LoadResult.Page(
                data = list,
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (e: Exception) {
            Log.d("breed", e.message.toString())
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Breed>): Int? {
        return state.anchorPosition?.let {
            val page = state.closestPageToPosition(it)
            page?.prevKey?.plus(1) ?: page?.nextKey?.minus(1)
        }
    }
}